package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;

public class CoordinableNegotiatorProxy extends AbstractProxy<ICoordinableNegotiator> implements ICoordinableNegotiator {

	public CoordinableNegotiatorProxy(ICoordinableNegotiator e) {
		super(e);
	}
	
	public void accept(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.accept(thread);
			}
		});
	}

	public void cancel(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.cancel(thread);
			}
		});
	}

	public void init(final URI thread, final IBargainingCoordinator coordinator,
			final IBargainingProtocolHandler protocolHandler) {
		send(new Runnable() {
			public void run() {				
				elem.init(thread, 
						getInternalReference(IBargainingCoordinator.class, coordinator),
						getInternalReference(IBargainingProtocolHandler.class, protocolHandler));
			}
		});
	}

	public void reject(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.reject(thread);
			}
		});
	}	

}