package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;

public class ProtocolNegotiatorProxy extends AbstractProxy<IProtocolNegotiator> implements IProtocolNegotiator {

	public ProtocolNegotiatorProxy(IProtocolNegotiator e) {
		super(e);
	}
	public void negotiateProtocol(final URI thread, final URI party,
			final IProtocolNegotiationRequester requester) {
		send(new Runnable() {
			public void run() {
				elem.negotiateProtocol(thread, party, 
						getInternalReference(IProtocolNegotiationRequester.class, requester));
			}
		});

	}

}
