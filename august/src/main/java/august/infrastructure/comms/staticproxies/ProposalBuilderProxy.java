package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.IProposal;

public class ProposalBuilderProxy<T extends IProposal<?>> extends AbstractProxy<IProposalBuilder<T>> implements IProposalBuilder<T> {

	public ProposalBuilderProxy(IProposalBuilder<T> e) {
		super(e);
	}
	
	public T cancelGeneration(URI thread) {
		return elem.cancelGeneration(thread);
	}

	public void configure(final URI thread, final Object builderConfig) {
		send(new Runnable() {
			public void run() {
				elem.configure(thread, builderConfig);
			}
		});
	}

	public void generateProposal(final URI thread, final INegotiationStatus<T> status,
			final IProposalRequester<? super T> requester) {
		send(new Runnable() {
			public void run() {
				elem.generateProposal(thread, status, 
						getInternalReference(IProposalRequester.class, requester));
			}
		});
	}

}
