package august.infrastructure.comms.staticproxies;

import java.net.URI;
import java.util.Collection;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.IProposal;

public class BuilderManagerProxy<T extends IProposal<?>> extends AbstractProxy<IBuilderManager<T>> implements IBuilderManager<T> {

	public BuilderManagerProxy(IBuilderManager<T> e) {
		super(e);
	}
	
	public T cancelGeneration(URI thread) {
		return elem.cancelGeneration(thread);
	}

	public void generateProposal(final URI thread, final Collection<BargainingPerformative> selected,
			final INegotiationStatus<T> status, final IProposalRequester<? super T> requester) {
		
		send(new Runnable() {
			public void run() {				
				elem.generateProposal(thread, selected, status, 
						getInternalReference(IProposalRequester.class, requester));
			}
		});
		
	}

	public void proposal(final URI context, final T msg) {
		
		send(new Runnable() {
			public void run() {
				elem.proposal(context, msg);
			}
		});
	}

}
