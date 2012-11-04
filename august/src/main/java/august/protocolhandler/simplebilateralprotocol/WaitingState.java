package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import negofast.bargaining.model.BargainingPerformative;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class WaitingState extends SimpleProtocolState {

	public WaitingState(BilateralHandler context) {
		super(context);
	}

	@Override
	public void accept(INegotiationMessage<IProposal<?>> msg, URI p) {
		getContext().setPartyURI(p);
		getContext().setState(getContext().finishedState);
		
		msg.setSender(p);
		getContext().getNegotiator().accept(
				getContext().getNegotiationContext(), 
				msg, 
				new ArrayList<BargainingPerformative>());
	}

	@Override
	public void commit(INegotiationMessage<IProposal<?>> msg, URI p) {
		getContext().setPartyURI(p);		
		getContext().setState(getContext().sendingState);
		
		msg.setSender(p);
		getContext().getNegotiator().commit(
				getContext().getNegotiationContext(), 
				msg, 
				Arrays.asList(
						BargainingPerformative.commit, 
						BargainingPerformative.accept, 
						BargainingPerformative.rejectNegotiation));		
	}

	@Override
	public void rejectNegotiation(INegotiationMessage<IProposal<?>> msg, URI p) {
		getContext().setPartyURI(p);
		getContext().setState(getContext().finishedState);

		msg.setSender(p);
		getContext().getNegotiator().rejectNegotiation(
				getContext().getNegotiationContext(), 
				msg, 
				new ArrayList<BargainingPerformative>());
	}

}
