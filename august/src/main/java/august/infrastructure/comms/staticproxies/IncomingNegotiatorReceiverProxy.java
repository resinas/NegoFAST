package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.incomingnegotiation.IIncomingNegotiationReceiver;
import negofast.core.interactions.incomingnegotiation.IIncomingProtocolHandler;

public class IncomingNegotiatorReceiverProxy extends
		AbstractProxy<IIncomingNegotiationReceiver> implements
		IIncomingNegotiationReceiver {

	public IncomingNegotiatorReceiverProxy(IIncomingNegotiationReceiver e) {
		super(e);
	}
	
	public void negotiationReceived(final URI party, final IIncomingProtocolHandler incoming) {
		send(new Runnable() {
			public void run() {
				elem.negotiationReceived(party, 
						getInternalReference(IIncomingProtocolHandler.class, incoming));
			}
		});
	}

}
