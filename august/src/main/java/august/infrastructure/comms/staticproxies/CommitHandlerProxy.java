package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.requestcommitapproval.ApprovalType;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestcommitapproval.ICommitRequester;
import negofast.core.model.IProposal;

public class CommitHandlerProxy<T extends IProposal<?>> extends AbstractProxy<ICommitHandler<T>> implements ICommitHandler<T> {

	public CommitHandlerProxy(ICommitHandler<T> e) {
		super(e);
	}
	public void approvalRequest(final URI thread, final T p, final ApprovalType t,
			final ICommitRequester requester) {
		send(new Runnable() {
			public void run() {
				elem.approvalRequest(thread, p, t, getInternalReference(ICommitRequester.class, requester));
			}
		});
	}

	public void fail(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.fail(thread);
			}
		});
	}

	public void success(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.success(thread);
			}
		});
	}

}
