package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.requestthreadprocessing.IThreadProcessingRequester;

public class ThreadCoordinatorProxy extends AbstractProxy<IThreadCoordinator>
		implements IThreadCoordinator {

	public ThreadCoordinatorProxy(IThreadCoordinator e) {
		super(e);
	}

	public URI coordinateThread(final URI party, IThreadProcessingRequester req) {
		return elem.coordinateThread(party, req);
	}

	public void startFromNegotiation(URI thread, IProtocolHandler handler) {
		elem.startFromNegotiation(thread, handler);
	}

	public IProtocolNegotiationRequester startFromProtocolNegotiation(URI thread) {
		return elem.startFromProtocolNegotiation(thread);
	}

	public void startFromUser(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.startFromUser(thread);
			}
		});
	}

	public void terminate(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.terminate(thread);
			}
		});
	}

	public void terminateAll() {
		send(new Runnable() {
			public void run() {
				elem.terminateAll();
			}
		});
	}

}
