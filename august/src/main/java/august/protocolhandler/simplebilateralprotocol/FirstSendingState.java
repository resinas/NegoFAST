package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class FirstSendingState extends SimpleProtocolState {

	public FirstSendingState(BilateralHandler context) {
		super(context);
	}

	@Override
	public void commit(URI thread, INegotiationMessage<IProposal<?>> msg) {
		getContext().setState(getContext().waitingState);
		getContext().getParty().commit(msg, getContext().getMyURI());		
	}

	@Override
	public void rejectNegotiation(URI thread, INegotiationMessage<IProposal<?>> msg) {
		getContext().setState(new FinishedState(getContext()));
		getContext().getParty().rejectNegotiation(msg, getContext().getMyURI());		
	}

}
