package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public class ResponseRequesterProxy<T extends IProposal<?>> extends AbstractProxy<IResponseRequester<T>> implements IResponseRequester<T> {

	public ResponseRequesterProxy(IResponseRequester<T> e ){
		super(e);
	}
	public void negotiationMessage(final URI thread, final INegotiationMessage<T> msg) {
		send(new Runnable() {
			public void run() {
				elem.negotiationMessage(thread, msg);
			}
		});
	}

}
