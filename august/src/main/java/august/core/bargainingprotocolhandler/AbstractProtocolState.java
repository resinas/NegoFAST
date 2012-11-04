package august.core.bargainingprotocolhandler;

import java.net.URI;

import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public abstract class AbstractProtocolState<MyInterface,PH extends AbstractBargainingProtocolHandler<?,?>> {

	private PH handler;

	public AbstractProtocolState(PH ctx) {
		super();
		handler = ctx;
	}

	public PH getContext() {
		return handler;
	}
	
	public void sendError(String cause) {
		getContext().getNegotiator().error(getContext().getNegotiationContext(), cause);
		throw new RuntimeException(cause + " in (" + getContext().getState() + ")" + getContext().getMyURI());
	}

//	public IBargainingNegotiator getNegotiator() {
//		return context.getNegotiator();
//	}
//
//	public Protocol getParty() {
//		return context.getParty();
//	}
//
//	public URI getMyURI() {
//		return context.getMyURI();
//	}
//
//	public URI getThread() {
//		return context.getNegotiationContext();
//	}
//
//	public void setState(SimpleProtocolState newState) {
//		context.setState(newState);
//	}
	
	

	public void accept(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void cfp(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void commit(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void init(URI thread, IBargainingNegotiator negotiator) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void propose(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void rejectNegotiation(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void rejectProposal(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

	public void withdraw(URI thread, INegotiationMessage<IProposal<?>> msg) {
		handler.getNegotiator().error(thread, "Operation not allowed");
		throw new RuntimeException("Operation not allowed in (" + handler.state + ")" + handler.getMyURI());
	}

}