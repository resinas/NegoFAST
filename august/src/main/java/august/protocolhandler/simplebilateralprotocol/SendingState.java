package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class SendingState extends SimpleProtocolState {

	public SendingState(BilateralHandler context) {
		super(context);
	}

	@Override
	public void commit(URI thread, INegotiationMessage<IProposal<?>> msg) {
		getContext().setState(getContext().waitingState);
		getContext().getParty().commit(msg, getContext().getMyURI());		
	}

	@Override
	public void accept(URI thread, INegotiationMessage<IProposal<?>> msg) {
		getContext().setState(getContext().finishedState);
		getContext().getParty().accept(msg, getContext().getMyURI());		
	}

	@Override
	public void rejectNegotiation(URI thread, INegotiationMessage<IProposal<?>> msg) {
		getContext().setState(getContext().finishedState);
		getContext().getParty().rejectNegotiation(msg, getContext().getMyURI());		
	}

}
