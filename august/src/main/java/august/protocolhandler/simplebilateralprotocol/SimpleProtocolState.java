package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import negofast.simplebilateralprotocol.ISimpleBilateralProtocol;
import august.core.bargainingprotocolhandler.AbstractProtocolState;

public abstract class SimpleProtocolState extends AbstractProtocolState<ISimpleBilateralProtocol,BilateralHandler> implements ISimpleBilateralProtocol {

	public SimpleProtocolState(BilateralHandler context) {
		super(context);
	}
	
	public void accept(INegotiationMessage<IProposal<?>> msg, URI p) {
		sendError("Operation not allowed");
	}

	public void commit(INegotiationMessage<IProposal<?>> msg, URI p) {
		sendError("Operation not allowed");
	}

	public void rejectNegotiation(INegotiationMessage<IProposal<?>> msg, URI p) {
		sendError("Operation not allowed");
	}

	
}
