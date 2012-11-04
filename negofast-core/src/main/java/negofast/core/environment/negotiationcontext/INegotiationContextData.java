package negofast.core.environment.negotiationcontext;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import negofast.core.environment.events.IEventListener;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;


/**
 * Environmental resource \NegotiationContextData stores information related to
 * the negotiation contexts. A negotiation context implements a negotiation
 * protocol instance with one or more parties. These parties are referenced by
 * the thread contexts that process each of them. Interface
 * INegotiationContextData provides access to all negotiation contexts and
 * provides a façade to query and modify them
 * 
 * @author Manuel Resinas
 * 
 */
public interface INegotiationContextData {
	/**
	 * Adds a new negotiation context to negotiate with the party of the given thread following the given protocol and returns the URI that identifies it.
	 * @param thread
	 * @return
	 */
    public URI addNegotiationContext(URI thread);

    /**
     * Obtains the URIs of all negotiation contexts.
     * @return
     */
    public Set<URI> getNegotiationsURI();
    /**
     * Obtains the URIs of all active negotiation contexts (\ie those negotiation contexts whose state is not finished).
     * @return
     */
    public Set<URI> getActiveNegotiationsURI();
    /**
     * Obtains the URIs of all negotiation contexts in a given state.
     * @param state
     * @return
     */
    public Set<URI> getNegotiationsURIByState(NegotiationState state);
    public int countActiveNegotiations();
    

    /**
     * Returns a reference to the negotiation context given its URI.
     * @param negotiation
     * @return
     */
    public INegotiationContext getNegotiationContext(URI negotiation);
    /**
     * Subscribes to one or several events of the given negotiation context.
     * @param events
     * @param listener
     */
    public void subscribeEvent(NegotiationEventType[] events, IEventListener listener);
    /**
     * Subscribes to one or several events of the given negotiation context.
     * @param negotiation
     * @param events
     * @param listener
     */
    public void subscribeNegotiationEvent(URI negotiation, NegotiationEventType[] events, IEventListener listener);

    
    public INegotiationProtocolInstance getNegotiationProtocol(URI negotiation);
	public abstract Date getStartDate(URI negotiation);
	public Collection<URI> getThreads(URI negotiation);
	public abstract void addThread(URI negotiation, URI thread);
    public void setState(URI negotiation, NegotiationState state);
    public NegotiationState getState(URI negotiation);
    public void addNegotiationMessage(URI negotiation, INegotiationMessage<?> msg);
    public INegotiationMessage<?> getLastNegotiationMessage(URI negotiation);
    public INegotiationMessage<?> getNegotiationMessage(URI negotiation, int i);
    public int countNegotiationMessages(URI negotiation);
    public List<? extends INegotiationMessage<?>> getNegotiationMessages(URI negotiation);
}