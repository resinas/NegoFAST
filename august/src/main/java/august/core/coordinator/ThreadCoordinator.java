/*
 * ThreadManager.java
 *
 * Created on August 5, 2007, 5:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.coordinator;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.requestthreadprocessing.IThreadProcessingRequester;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class ThreadCoordinator implements IThreadCoordinator {

    private ServiceRegistry registry;
    
    private Map<URI, ThreadHandler> threads;
    
    /** Creates a new instance of ThreadManager */
    public ThreadCoordinator(ServiceRegistry registry) {
        this.registry = registry;
        threads = new HashMap<URI, ThreadHandler>();
    }

	public URI coordinateThread(URI party, IThreadProcessingRequester req) {
		ThreadHandler thread = new ThreadHandler(registry, party, req, this);
		URI threadURI = thread.getURI();
		
		threads.put(threadURI, thread);

		return threadURI;
	}


	public void startFromNegotiation(URI thread, IProtocolHandler protocolHandler) {
		threads.get(thread).startFromNegotiation(protocolHandler);
	}


	public IProtocolNegotiationRequester startFromProtocolNegotiation(URI thread) {
		return threads.get(thread).startFromProtocolNegotiation();
	}


	public void startFromUser(URI thread) {
		threads.get(thread).startFromUser();
	}


	public void terminate(URI thread) {
		threads.get(thread).terminate();
	}


	public void terminateAll() {
		for(ThreadHandler th : threads.values()) {
			th.terminate();
		}		
	}
	
	public void finished(URI thread) {
		threads.remove(thread);
	}

}
