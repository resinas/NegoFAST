package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.incomingnegotiation.IIncomingProtocolHandler;

public class IncomingProtocolHandlerProxy extends AbstractProxy<IIncomingProtocolHandler> implements IIncomingProtocolHandler {

	public IncomingProtocolHandlerProxy(IIncomingProtocolHandler e) {
		super(e);
	}
	
	public IProtocolHandler acceptNegotiation(final URI thread, final URI party) {
		return elem.acceptNegotiation(thread, party);
	}

	public void rejectNegotiation(final URI party) {
		send(new Runnable() {
			public void run() {
				elem.rejectNegotiation(party);
			}
		});
	}

}
