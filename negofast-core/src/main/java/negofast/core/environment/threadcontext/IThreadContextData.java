package negofast.core.environment.threadcontext;

import java.net.URI;
import java.util.Set;

import negofast.core.environment.events.IEventListener;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IPartyInformation;


/**
 * Environmental resource ThreadContextData stores information related to the
 * thread contexts. This includes the current state and the information
 * generated along the lifecycle of all threads. A thread context processes a
 * party reference.
 * 
 * Interface IThreadContextData provides access to all thread
 * contexts of the automated negotiation system and a façade to query
 * and modify the thread contexts. In addition, it offers a subscription mechanism for
 * events of type {@link ThreadEventType}
 * 
 * @author Manuel Resinas
 * 
 */
public interface IThreadContextData {
	/**
	 * Adds a new thread with a given party and returns the URI that identifies it.
	 * @param party
	 * @return
	 */
    public URI addThread(URI party);

    /**
     * Obtains the URIs of all threads.
     * @return
     */
    public Set<URI> getThreadsURI();
    /**
     * Obtains the URIs of all active threads (those threads whose state is not finished or decommited).
     * @return
     */
    public Set<URI> getActiveThreadsURI();
    /**
     * Obtains the URIs of all threads in a given state.
     * @param state
     * @return
     */
    public Set<URI> getThreadsURIByState(ThreadState state);

    /**
     * Returns a reference to a thread context given its URI.
     * @param thread
     * @return
     */
    public IThread getThread(URI thread);
    /**
     * Subscribes to one or several events. If the event is thread-specific, it subscribes to that event in all threads.
     * @param events
     * @param listener
     */
    public void subscribeEvent(ThreadEventType[] events, IEventListener listener);
    /**
     * Subscribes to one or several events of the given thread. Events that are not thread-specific must be subscribed using method subscribeEvent.
     * @param thread
     * @param events
     * @param listener
     */
    public void subscribeThreadEvent(URI thread, ThreadEventType[] events, IEventListener listener);
    
    
    public URI getThreadParty(URI thread);

    public void setThreadState(URI thread, ThreadState state);
    public ThreadState getThreadState(URI thread);

    public void addInformation(URI thread, IPartyInformation<?> info);
    public IPartyInformation<?> getInformation(URI thread);

    public void setNegotiationProtocol(URI thread, INegotiationProtocolInstance p);
    public INegotiationProtocolInstance getNegotiationProtocol(URI thread);

    public void setNegotiationContext(URI thread, URI negotiation);
    public URI getNegotiationContext(URI thread);
}