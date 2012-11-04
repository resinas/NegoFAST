package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.core.model.IProposal;

public class ProposalRequesterProxy<T extends IProposal<?>> extends AbstractProxy<IProposalRequester<T>> implements IProposalRequester<T> {

	public ProposalRequesterProxy(IProposalRequester<T> e ){
		super(e);
	}
	public void proposal(final URI context, final T msg) {
		send(new Runnable() {
			public void run() {
				elem.proposal(context, msg);
			}
		});
	}

}
