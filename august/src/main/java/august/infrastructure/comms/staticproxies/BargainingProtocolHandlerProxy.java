package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class BargainingProtocolHandlerProxy extends AbstractProxy<IBargainingProtocolHandler> implements
		IBargainingProtocolHandler {

	public BargainingProtocolHandlerProxy(IBargainingProtocolHandler e) {
		super(e);
	}
	
	public void accept(final URI thread, final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.accept(thread, msg);
			}
		});
	}

	public void cfp(final URI thread, final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.cfp(thread, msg);
			}
		});
	}

	public void commit(final URI thread, final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.commit(thread, msg);
			}
		});
	}

	public void init(final URI thread, final IBargainingNegotiator negotiator) {
		send(new Runnable() {
			public void run() {
				elem.init(thread, getInternalReference(IBargainingNegotiator.class, negotiator));
			}
		});
	}

	public void propose(final URI thread, final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.propose(thread, msg);
			}
		});
	}

	public void rejectNegotiation(final URI thread,
			final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.rejectNegotiation(thread, msg);
			}
		});
	}

	public void rejectProposal(final URI thread, final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.rejectProposal(thread, msg);
			}
		});
	}

	public void withdraw(final URI thread, final INegotiationMessage<IProposal<?>> msg) {
		send(new Runnable() {
			public void run() {
				elem.withdraw(thread, msg);
			}
		});
	}

}
