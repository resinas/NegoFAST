package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.bargaining.interactions.submitpolicies.IPolicyReceiver;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationPolicy;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;


public abstract class NegotiationManagerState implements ICoordinableNegotiator, 
        IBargainingNegotiator, IResponseRequester<IProposal<?>>, IPolicyReceiver {

	protected NegotiationManager nego;
    
    public NegotiationManagerState(NegotiationManager c) {
    	this.nego = c;
    }
    
    
    public void init(URI thread, IBargainingCoordinator coordinator, IBargainingProtocolHandler protocolHandler) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }    

    public void reject(URI thread) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void accept(URI thread) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void cancel(URI thread) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }
    
    public void setNegotiationPolicies(URI thread, Collection<INegotiationPolicy> policies) {
    	nego.status.setPolicies(policies);
    }
    
    public void startNegotiation(URI thread, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in bilateral negotiator" + thread);
    }

    public void error(URI thread, String reason) {
    	nego.setState(nego.finishedState);
        nego.finishedUnsuccessfully(thread);
    }

    public void cfp(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void propose(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void commit(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void withdraw(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void rejectProposal(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void accept(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }

    public void rejectNegotiation(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }
    
    public void negotiationMessage(URI thread, INegotiationMessage<IProposal<?>> msg) {
    	nego.coordinator.finishedUnsuccessfully(thread);
		throw new RuntimeException("Operation not allowed in state " + nego.state + ") " + thread);
    }
}