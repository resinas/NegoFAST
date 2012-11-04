/*
 * Context.java
 *
 * Created on August 4, 2007, 1:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment.threadcontext;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import negofast.core.environment.events.IEventListener;
import negofast.core.environment.threadcontext.IThread;
import negofast.core.environment.threadcontext.IThreadContextData;
import negofast.core.environment.threadcontext.ThreadEventType;
import negofast.core.environment.threadcontext.ThreadState;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IPartyInformation;
import august.core.environment.events.EventManager;

/**
 *
 * @author resman
 */
public class ThreadContextResource implements IThreadContextData {
    
    private URI userURI;
    
    private long threadCounter = 0;
    private Map<URI, IThread> threads;
    
    private EventManager<ThreadEventType> event;
    
    /** Creates a new instance of Context */
    public ThreadContextResource(URI user) {
    	userURI = user;
    	threads = new HashMap<URI, IThread>();
    	event = new EventManager<ThreadEventType>(URI.create("negofast:thread"));
    }

    public Set<URI> getActiveThreadsURI() {
    	Set<URI> result = new HashSet<URI>();
    	
    	for (IThread t : threads.values()) {
    		ThreadState state = t.getState();
    		if (state.equals(ThreadState.gettingInformation) || 
    				state.equals(ThreadState.negotiating) || 
    				state.equals(ThreadState.prenegotiating))
    			result.add(t.getURI());
    	}

    	return result;
    }

    public Set<URI> getThreadsURIByState(ThreadState state) {
    	Set<URI> result = new HashSet<URI>();
    	
    	for (IThread t : threads.values()) {
    		if (state.equals(t.getState()))
    			result.add(t.getURI());
    	}

    	return result;
    }

    public URI getThreadParty(URI thread) {
        return getThread(thread).getParty();
    }

    public void setState(URI negotiation, ThreadState state) {
        getThread(negotiation).setState(state);
        
        event.sendEvent(ThreadEventType.stateChanged, negotiation);
        
        if (state.equals(ThreadState.finishedSuccessfully) || state.equals(ThreadState.finishedUnsuccessfully)) {
            event.sendEvent(ThreadEventType.threadFinished, negotiation);
        }
    }

    public ThreadState getThreadState(URI negotiation) {
        return getThread(negotiation).getState();
    }

    public void setNegotiationProtocol(URI thread, INegotiationProtocolInstance p) {
        getThread(thread).setNegotiationProtocol(p);
    }

    public INegotiationProtocolInstance getNegotiationProtocol(URI thread) {
        return getThread(thread).getNegotiationProtocol();
    }

    public URI getNegotiationContext(URI negotiation) {
        return threads.get(negotiation).getNegotiationContext();
    }


	public synchronized URI addThread(URI party) {
        URI threadURI = URI.create(userURI+"/negofast:threadid:"+threadCounter);
        
        IThread newThread = new ThreadContext(threadURI, party);        
        threads.put(threadURI, newThread);
        threadCounter++;
        event.sendEvent(ThreadEventType.threadCreated, threadURI);
        
        return threadURI;
	}


	public IThread getThread(URI thread) {
		return threads.get(thread);
	}


	public void setNegotiationContext(URI thread, URI negotiation) {
		getThread(thread).setNegotiationContext(negotiation);
	}

	public void setThreadState(URI thread, ThreadState state) {
		getThread(thread).setState(state);
	}

	public void subscribeEvent(ThreadEventType[] events, IEventListener listener) {
		event.subscribeEvent(events, listener);		
	}

	public void subscribeThreadEvent(URI thread, ThreadEventType[] events,
			IEventListener listener) {
		event.subscribeEvent(thread, events, listener);
	}

	public void addInformation(URI thread, IPartyInformation<?> info) {
        getThread(thread).setInformation(info);		
	}

	public IPartyInformation<?> getInformation(URI thread) {
		return getThread(thread).getInformation();
	}

	public Set<URI> getThreadsURI() {
		return threads.keySet();
	}

}
