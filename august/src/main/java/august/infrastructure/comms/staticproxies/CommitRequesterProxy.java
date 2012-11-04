package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.requestcommitapproval.ICommitRequester;

public class CommitRequesterProxy extends AbstractProxy<ICommitRequester> implements ICommitRequester {

	public CommitRequesterProxy(ICommitRequester e) {
		super(e);
	}
	
	public void approved(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.approved(thread);
			}
		});
	}

	public void rejected(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.rejected(thread);
			}
		});
	}

}
