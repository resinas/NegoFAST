package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.core.model.IAgreement;
import negofast.core.model.IProposal;

public class BargainingCoordinatorProxy extends AbstractProxy<IBargainingCoordinator> implements IBargainingCoordinator {

	public BargainingCoordinatorProxy(IBargainingCoordinator e) {
		super(e);
	}
	
	public void acceptApprovalRequest(final URI thread, final IProposal<?> p) {
		send(new Runnable() {
			public void run() {
				elem.acceptApprovalRequest(thread, p);
			}
		});
	}

	public void commitApprovalRequest(final URI thread, final IProposal<?> p) {
		send(new Runnable() {
			public void run() {
				elem.commitApprovalRequest(thread, p);
			}
		});
	}

	public void commitRejected(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.commitRejected(thread);
			}
		});
	}

	public void finishedSuccessfully(final URI thread, final IAgreement a) {
		send(new Runnable() {
			public void run() {
				elem.finishedSuccessfully(thread, a);
			}
		});
	}

	public void finishedUnsuccessfully(final URI context) {
		send(new Runnable() {
			public void run() {
				elem.finishedUnsuccessfully(context);
			}
		});
	}

}
