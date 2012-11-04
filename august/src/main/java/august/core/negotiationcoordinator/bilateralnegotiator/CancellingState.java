package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.BargainingPerformative;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class CancellingState extends NegotiationManagerState {

	public CancellingState(NegotiationManager c) {
		super(c);
	}

    public void startNegotiation(URI thread, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.cancelled(thread);
    }

    public void error(URI thread, String reason) {
    	nego.setState(nego.finishedState);
        nego.cancelled(thread);
    }

    public void cfp(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.cancelled(thread);
    }

    public void propose(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.cancelled(thread);
    }

    public void commit(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.cancelled(thread);
    }

    public void withdraw(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.cancelled(thread);
    }

    public void rejectProposal(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.cancelled(thread);
    }

    public void accept(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.finishedSuccessfully(thread, msg);
    }

    public void rejectNegotiation(URI thread, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed) {
    	nego.setState(nego.finishedState);
        nego.receivedMessage(thread, msg);
        nego.cancelled(thread);
    }

}
