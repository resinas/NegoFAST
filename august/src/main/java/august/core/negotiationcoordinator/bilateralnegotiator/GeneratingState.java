package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;

import negofast.bargaining.model.BargainingPerformative;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import negofast.core.model.Performative;

public class GeneratingState extends NegotiationManagerState {

	public GeneratingState(NegotiationManager c) {
		super(c);
	}
	
    public void cancel(URI thread) {
    	nego.setState(nego.finishedState);
    	nego.cancelled(thread);
    }

    public void negotiationMessage(URI thread, INegotiationMessage<IProposal<?>> msg) {
        Performative perf = msg.getPerformative();
        
        nego.status.setGenerated(msg);
        
        if (perf.equals(BargainingPerformative.commit)) {
        	nego.setState(nego.approvalState);
        	
            IProposal<?> p = msg.getContent();
            nego.coordinator.commitApprovalRequest(thread, p);
        }
        else if (perf.equals(BargainingPerformative.accept)) {
            nego.setState(nego.approvalState);
            
            IProposal<?> p = msg.getContent();
            nego.coordinator.acceptApprovalRequest(thread, p);                        
        }        
        else if (perf.equals(BargainingPerformative.rejectNegotiation)) {
        	nego.setState(nego.finishedState);
        	nego.status.setApprovalRejected(false);
            nego.finishedUnsuccessfully(thread);        	
            nego.sendMessage(thread, msg);
        }
        else {
        	nego.setState(nego.waitingState);
        	nego.status.setApprovalRejected(false);
            nego.sendMessage(thread, msg);
        }
    }
}
