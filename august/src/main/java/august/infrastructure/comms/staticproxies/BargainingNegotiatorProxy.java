package august.infrastructure.comms.staticproxies;

import java.net.URI;
import java.util.Collection;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.model.BargainingPerformative;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class BargainingNegotiatorProxy extends AbstractProxy<IBargainingNegotiator> implements IBargainingNegotiator {

	public BargainingNegotiatorProxy(IBargainingNegotiator e) {
		super(e);
	}
	
	public void accept(final URI thread, final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.accept(thread, msg, allowed);
			}
		});
	}

	public void cfp(final URI thread, final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.cfp(thread, msg, allowed);
			}
		});
	}

	public void commit(final URI thread, final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.commit(thread, msg, allowed);
			}
		});
	}

	public void error(final URI thread, final String reason) {
		send(new Runnable() {
			public void run() {
				elem.error(thread, reason);
			}
		});
	}

	public void propose(final URI thread, final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.propose(thread, msg, allowed);
			}
		});
	}

	public void rejectNegotiation(final URI thread,
			final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.rejectNegotiation(thread, msg, allowed);
			}
		});
	}

	public void rejectProposal(final URI thread,
			final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.rejectProposal(thread, msg, allowed);
			}
		});
	}

	public void startNegotiation(final URI thread,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.startNegotiation(thread, allowed);
			}
		});
	}

	public void withdraw(final URI thread, final INegotiationMessage<IProposal<?>> msg,
			final Collection<BargainingPerformative> allowed) {
		send(new Runnable() {
			public void run() {
				elem.withdraw(thread, msg, allowed);
			}
		});
	}

}
