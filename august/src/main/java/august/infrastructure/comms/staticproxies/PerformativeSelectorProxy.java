package august.infrastructure.comms.staticproxies;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import august.core.responsegenerator.IPerformativeSelector;
import august.infrastructure.comms.AbstractProxy;

public class PerformativeSelectorProxy<T extends IProposal<?>> extends AbstractProxy<IPerformativeSelector<T>> implements IPerformativeSelector<T> {

	public PerformativeSelectorProxy(IPerformativeSelector<T> e) {
		super(e);
	}
	
	public INegotiationMessage<T> cancelGeneration(URI thread) {
		return elem.cancelGeneration(thread);
	}

	public void generateResponse(final URI thread,
			final Collection<BargainingPerformative> allowed,
			final INegotiationStatus<T> status, 
			final IResponseRequester<T> requester) {
		
		send(new Runnable() {
			public void run() {
				elem.generateResponse(thread, 
						allowed, 
						status, 
						getInternalReference(IResponseRequester.class, requester));
			}
		});
	}

	public void proposal(final URI thread, final T msg) {
		send(new Runnable() {
			public void run() {
				elem.proposal(thread, msg);
			}
		});
	}

}
