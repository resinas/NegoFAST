package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.BargainingPerformative;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class WaitingAcceptedState extends NegotiationManagerState {

	public WaitingAcceptedState(NegotiationManager c) {
		super(c);
	}
	
	
    public void cancel(URI thread) {
    	nego.setState(nego.cancellingState);
    	nego.cancelling(thread);
    }

	
    public void cfp(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.generatingState);
        nego.receivedMessage(thread, msg);
	    nego.coordinator.commitRejected(thread);
	    
        nego.genMessage(thread, allowed);
    }

    public void propose(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.generatingState);
        nego.receivedMessage(thread, msg);
	    nego.coordinator.commitRejected(thread);
	    
        nego.genMessage(thread, allowed);
    }

    public void commit(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.generatingState);
        nego.receivedMessage(thread, msg);
	    nego.coordinator.commitRejected(thread);
	    
        nego.genMessage(thread, allowed);
    }

    public void withdraw(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.generatingState);
        nego.receivedMessage(thread, msg);
	    nego.coordinator.commitRejected(thread);
	    
        nego.genMessage(thread, allowed);
    }

    public void rejectProposal(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.generatingState);
        nego.receivedMessage(thread, msg);
	    nego.coordinator.commitRejected(thread);
	    
        nego.genMessage(thread, allowed);
    }

    public void accept(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.finishedSuccessfully(thread, msg);
    }

    public void rejectNegotiation(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.finishedUnsuccessfully(thread);
    }
}
