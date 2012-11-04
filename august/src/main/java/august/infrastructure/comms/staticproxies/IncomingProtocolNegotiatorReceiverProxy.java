package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiationReceiver;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiator;

public class IncomingProtocolNegotiatorReceiverProxy extends
		AbstractProxy<IIncomingProtocolNegotiationReceiver> implements
		IIncomingProtocolNegotiationReceiver {

	public IncomingProtocolNegotiatorReceiverProxy(IIncomingProtocolNegotiationReceiver e) {
		super(e);
	}
	
	public void protocolNegotiationReceived(final URI party,
			final IIncomingProtocolNegotiator incoming) {
		send(new Runnable() {
			public void run() {
				elem.protocolNegotiationReceived(party, 
						getInternalReference(IIncomingProtocolNegotiator.class, incoming));
			}
		});
	}


}
