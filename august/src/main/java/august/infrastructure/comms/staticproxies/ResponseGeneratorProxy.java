package august.infrastructure.comms.staticproxies;

import java.net.URI;
import java.util.Collection;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.requestresponse.IResponseGenerator;
import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class ResponseGeneratorProxy<T extends IProposal<?>> extends AbstractProxy<IResponseGenerator<T>> implements IResponseGenerator<T> {

	public ResponseGeneratorProxy(IResponseGenerator<T> e) {
		super(e);
	}
	public INegotiationMessage<T> cancelGeneration(URI thread) {
		return elem.cancelGeneration(thread);
	}

	public void generateResponse(final URI thread, final Collection<BargainingPerformative> allowed,
			final INegotiationStatus<T> status, final IResponseRequester<T> requester) {
		send(new Runnable() {
			public void run() {
				elem.generateResponse(thread, allowed, status, 
						getInternalReference(IResponseRequester.class, requester));
			}
		});
	}

}
