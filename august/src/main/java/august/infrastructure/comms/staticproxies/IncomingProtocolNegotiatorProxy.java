package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;

public class IncomingProtocolNegotiatorProxy extends AbstractProxy<IIncomingProtocolNegotiator> implements
		IIncomingProtocolNegotiator {

	public IncomingProtocolNegotiatorProxy(IIncomingProtocolNegotiator e) {
		super(e);
	}
	public void acceptProtocolNegotiation(final URI thread, final URI party,
			final IProtocolNegotiationRequester requester) {
		send(new Runnable() {
			public void run() {
				elem.acceptProtocolNegotiation(thread, party, 
						getInternalReference(IProtocolNegotiationRequester.class, requester));
			}
		});
	}

	public void rejectProtocolNegotiation(final URI party) {
		send(new Runnable() {
			public void run() {
				elem.rejectProtocolNegotiation(party);
			}
		});
	}

}
