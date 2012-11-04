/*
 * BargainingCoordinator.java
 *
 * Created on August 6, 2007, 11:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.negotiationcoordinator;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.negotiationcontext.NegotiationState;
import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestcommitapproval.ApprovalType;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestcommitapproval.ICommitRequester;
import negofast.core.interactions.requestnegotiation.INegotiationRequester;
import negofast.core.interactions.requestnegotiation.INegotiator;
import negofast.core.model.IAgreement;
import negofast.core.model.IProposal;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class BargainingCoordinator implements INegotiator, IBargainingCoordinator, ICommitRequester {
    
    private Map<URI,INegotiationRequester> requesters;
    
    private ICoordinableNegotiator bilateralNegotiator;
    private ICommitHandler<IProposal<?>> commitHandler;
    private INegotiationContextData negotiationContext;
    
    private Set<URI> waitingApproval;
    
    /** Creates a new instance of BargainingCoordinator */
    public BargainingCoordinator(ServiceRegistry registry) {
        requesters = new HashMap<URI,INegotiationRequester>();
        waitingApproval = new HashSet<URI>();
        
        bilateralNegotiator = registry.getCoordinableNegotiator();
        commitHandler = registry.getCommitHandler();
        negotiationContext = registry.getNegotiationContext();
    }

    // Negotiator
    public void negotiate(URI thread, IProtocolHandler protocolHandler, INegotiationRequester requester) {
    	negotiationContext.addNegotiationContext(thread);
        negotiationContext.setState(thread, NegotiationState.negotiating);
        requesters.put(thread,requester);
        bilateralNegotiator.init(thread, this, (IBargainingProtocolHandler)protocolHandler);        
    }

    public void cancel(URI thread) {
        if (waitingApproval.remove(thread))
            commitHandler.fail(thread);
        
        bilateralNegotiator.cancel(thread);
        negotiationContext.setState(thread, NegotiationState.finishedUnsuccessfully);
    }

    // Coordinator
    public void commitApprovalRequest(URI thread, IProposal<?> p) {
        waitingApproval.add(thread);
        commitHandler.approvalRequest(thread, p, ApprovalType.commit, this);
        negotiationContext.setState(thread, NegotiationState.askedForApproval);        
    }

    public void acceptApprovalRequest(URI thread, IProposal<?> p) {
        waitingApproval.add(thread);
        commitHandler.approvalRequest(thread, p, ApprovalType.accept, this);
        negotiationContext.setState(thread, NegotiationState.askedForApproval);        
    }

    public void commitRejected(URI thread) {
        commitHandler.fail(thread);
        negotiationContext.setState(thread, NegotiationState.negotiating);        
    }

    public void finishedUnsuccessfully(URI context) {
        if (waitingApproval.remove(context)) {
            commitHandler.fail(context);
        }
        
        requesters.get(context).fail(context);
        negotiationContext.setState(context, NegotiationState.finishedUnsuccessfully);
    }

    public void finishedSuccessfully(URI thread, IAgreement a) {
        commitHandler.success(thread);
        requesters.get(thread).success(thread, a);
        negotiationContext.setState(thread, NegotiationState.finishedSuccessfully);        
    }

    // Approval Requester
    public void approved(URI thread) {
        waitingApproval.remove(thread);
        bilateralNegotiator.accept(thread);  
        negotiationContext.setState(thread, NegotiationState.approved);        
    }

    public void rejected(URI thread) {
        waitingApproval.remove(thread);
        bilateralNegotiator.reject(thread);
        negotiationContext.setState(thread, NegotiationState.negotiating);        
    }
    
}
