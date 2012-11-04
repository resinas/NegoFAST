package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestnegotiation.INegotiationRequester;
import negofast.core.interactions.requestnegotiation.INegotiator;

public class NegotiatorProxy extends AbstractProxy<INegotiator> implements INegotiator {

	public NegotiatorProxy(INegotiator e) {
		super(e);
	}
	public void cancel(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.cancel(thread);
			}
		});
	}

	public void negotiate(final URI thread,
			final IProtocolHandler protocolHandler,
			final INegotiationRequester requester) {
		
		send(new Runnable() {
			public void run() {
				elem.negotiate(thread, 
						getInternalReference(IBargainingProtocolHandler.class, (IBargainingProtocolHandler)protocolHandler), 
						getInternalReference(INegotiationRequester.class, requester));
			}
		});
	}

}
