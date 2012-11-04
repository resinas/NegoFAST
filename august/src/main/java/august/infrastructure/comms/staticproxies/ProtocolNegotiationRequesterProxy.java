package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.model.INegotiationProtocolInstance;

public class ProtocolNegotiationRequesterProxy extends AbstractProxy<IProtocolNegotiationRequester> implements
		IProtocolNegotiationRequester {

	public ProtocolNegotiationRequesterProxy(IProtocolNegotiationRequester e) {
		super(e);
	}
	public void failedProtocolNegotiation(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.failedProtocolNegotiation(thread);
			}
		});
	}

	public void successfulProtocolNegotiation(final URI thread,
			final INegotiationProtocolInstance protocol, final IProtocolHandler handler) {
		send(new Runnable() {
			public void run() {
				elem.successfulProtocolNegotiation(thread, protocol, 
						getInternalReference(IBargainingProtocolHandler.class, (IBargainingProtocolHandler)handler));
			}
		});
	}

}
