package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;

import negofast.bargaining.model.BargainingPerformative;

public class ApprovalState extends NegotiationManagerState {

	public ApprovalState(NegotiationManager c) {
		super(c);
	}
	
    public void cancel(URI thread) {
    	nego.setState(nego.finishedState);
    	nego.cancelled(thread);
    }

    public void reject(URI thread) {
    	nego.setState(nego.generatingState);
    	nego.status.setApprovalRejected(true);
        nego.genMessage(thread, nego.allowedPerformatives);
    }

    public void accept(URI thread) {
    	if (nego.status.getGenerated().getPerformative().equals(BargainingPerformative.accept)) {
        	nego.setState(nego.finishedState);
        	nego.finishedSuccessfully(thread, nego.status.getGenerated());
    	}
    	else { // BargainingPerformative.commit
    		nego.setState(nego.waitingAcceptedState);
    	}
    	nego.status.setApprovalRejected(false);
    	
        nego.sendMessage(thread, nego.status.getGenerated());
    }
}
