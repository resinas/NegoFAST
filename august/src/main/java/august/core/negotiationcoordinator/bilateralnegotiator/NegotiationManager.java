package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;
import java.util.Collection;
import java.util.logging.Logger;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.bargaining.interactions.requestresponse.IResponseGenerator;
import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.bargaining.interactions.submitpolicies.IPolicyReceiver;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationPolicy;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import negofast.core.model.Performative;
import august.core.model.Agreement;
import august.core.model.BargainingNegotiationStatus;
import august.core.model.NegotiationMessage;
import august.infrastructure.registry.ServiceRegistry;


public class NegotiationManager implements ICoordinableNegotiator, 
        IBargainingNegotiator, IResponseRequester<IProposal<?>>, IPolicyReceiver {
    
	private BilateralNegotiator parent;
	
	protected URI context;
	
    protected IBargainingCoordinator coordinator;
    protected IResponseGenerator<IProposal<?>> generator;
    protected IBargainingProtocolHandler protocol;
    
    protected BargainingNegotiationStatus<IProposal<?>> status;
    protected Collection<BargainingPerformative> allowedPerformatives;

    protected INegotiationContextData negotiationContext;    
    protected ServiceRegistry registry;
    
    protected NegotiationManagerState state;
    protected NegotiationManagerState waitingInitialState, waitingState, waitingAcceptedState;
    protected NegotiationManagerState generatingState, approvalState;
    protected NegotiationManagerState cancellingState, finishedState;
    
    
    public NegotiationManager(ServiceRegistry registry, BilateralNegotiator parent) {        
        this.registry = registry;
        this.negotiationContext = registry.getNegotiationContext();
        this.parent = parent;
        
        setUpStates();
    }
    
    private void setUpStates() {
    	waitingInitialState = new WaitingInitialState(this);
    	generatingState = new GeneratingState(this);
    	waitingState = new WaitingState(this);
    	waitingAcceptedState = new WaitingAcceptedState(this);
    	approvalState = new ApprovalState(this);
    	cancellingState = new CancellingState(this);
    	finishedState = new FinishedState(this);
    }
    
    protected void setState(NegotiationManagerState state) {
    	Logger.getLogger("august.bilateral").fine("[" + context + "] Change state from: " + this.state + " to: " + state);
    	this.state = state;
    }
    
    
    public synchronized void init(URI context, IBargainingCoordinator coordinator, IBargainingProtocolHandler protocolHandler) {
        this.generator = registry.getPerformativeSelector();
        this.protocol = protocolHandler;
        this.coordinator = coordinator;
        this.status = new BargainingNegotiationStatus<IProposal<?>>();   
        this.allowedPerformatives = null;
        this.context = context;
        
        IPoliciesManager policiesGen = registry.getPoliciesGenerator();        
        this.status.setPolicies(policiesGen.initNegotiation(context, this));
        
        
        setState(waitingInitialState);        
        
        protocolHandler.init(context, this);
    }    

    public synchronized void reject(URI context) {
    	state.reject(context);
    }

    public synchronized void accept(URI context) {
    	state.accept(context);
    }

    public synchronized void cancel(URI context) {
    	state.cancel(context);
    }

    
    
    public synchronized void setNegotiationPolicies(URI context, Collection<INegotiationPolicy> policies) {
    	state.setNegotiationPolicies(context, policies);
    }
    
    
    public synchronized void startNegotiation(URI context, Collection<BargainingPerformative> allowed) {
    	state.startNegotiation(context, allowed);
    }

    public synchronized void error(URI context, String reason) {
        state.error(context, reason);
    }

    public synchronized void cfp(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.cfp(context, msg, allowed);
    }

    public synchronized void propose(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.propose(context, msg, allowed);
    }

    public synchronized void commit(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.commit(context, msg, allowed);
    }

    public synchronized void withdraw(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.withdraw(context, msg, allowed);
    }

    public synchronized void rejectProposal(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.rejectProposal(context, msg, allowed);
    }

    public synchronized void accept(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.accept(context, msg, allowed);
    }

    public synchronized void rejectNegotiation(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	state.rejectNegotiation(context, msg, allowed);
    }

    public synchronized void negotiationMessage(URI context, INegotiationMessage<IProposal<?>> msg) {
        state.negotiationMessage(context, msg);        
    }
    

    protected void receivedMessage(URI context, INegotiationMessage<IProposal<?>> msg) {
        status.setReceived(msg);
        negotiationContext.addNegotiationMessage(context, msg);
    }
    

    protected void genMessage(URI context, Collection<BargainingPerformative> allowed) {
    	allowedPerformatives = allowed;
        generator.generateResponse(context, allowed, status, this);        
    }
    
    protected void sendMessage(URI context, INegotiationMessage<IProposal<?>> msg) {
        Performative perf = msg.getPerformative();
        
        IPreferencesResource p = registry.getPreferences();
        msg.setSender(p.getUser());
        negotiationContext.addNegotiationMessage(context, msg);
        status.setSent(msg);
        
        if (perf.equals(BargainingPerformative.accept)) {
        	protocol.accept(context, msg);
        }
        else if (perf.equals(BargainingPerformative.cfp)) {
        	protocol.cfp(context, msg);
        }
        else if (perf.equals(BargainingPerformative.commit)) {
        	protocol.commit(context, msg);
        }
        else if (perf.equals(BargainingPerformative.propose)) {
        	protocol.propose(context, msg);
        }
        else if (perf.equals(BargainingPerformative.rejectNegotiation)) {
        	protocol.rejectNegotiation(context, msg);
        }
        else if (perf.equals(BargainingPerformative.rejectProposal)) {
        	protocol.rejectProposal(context, msg);
        }
        else if (perf.equals(BargainingPerformative.withdraw)) {
        	protocol.withdraw(context, msg);
        }

        Logger.getLogger("august.bilateral").info(msg.toString());
    }
    
    protected void finishedSuccessfully(URI context, INegotiationMessage<IProposal<?>> msg) {
        Agreement a = messageToAgreement(msg);
        coordinator.finishedSuccessfully(context, a);
        parent.unregister(context);        
    }
    
    private Agreement messageToAgreement(INegotiationMessage<IProposal<?>> msg) {
    	Agreement a = new Agreement(msg.getContent());
        return a;
    }
    
    protected void finishedUnsuccessfully(URI context) {
        coordinator.finishedUnsuccessfully(context);
        parent.unregister(context);        
    }
    
    protected void cancelling(URI context) {
    }
    
    protected void cancelled(URI context) {
        NegotiationMessage<IProposal<?>> nm = NegotiationMessage.create();
        nm.setPerformative(BargainingPerformative.rejectNegotiation);
        sendMessage(context, nm);
    	parent.unregister(context);
    }
}