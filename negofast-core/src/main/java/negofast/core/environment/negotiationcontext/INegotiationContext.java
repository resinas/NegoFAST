package negofast.core.environment.negotiationcontext;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;

/**
 * A negotiation context has one negotiation protocol, one state and several thread contexts. In addition, it stores all negotiation messages that have been exchanged as part of the negotiation context.
 * @author Manuel Resinas
 *
 */
public interface INegotiationContext {
	/**
	 * Returns an identifier for the negotiation context.
	 * @return
	 */
	public abstract URI getURI();
	/**
	 * Returns the date when the negotiation context was started.
	 * @return
	 */
	/**
	 * Gets the negotiation protocol that has been selected for the negotiation context.
	 */
	public abstract INegotiationProtocolInstance getProtocol();
	/**
	 * 
	 * @return
	 */
	public abstract Date getStartDate();

	/**
	 * Gets the URIs of the threads that process the parties that are being negotiated in this negotiation context.
	 * @return
	 */
	public abstract Collection<URI> getThreads();
	/**
	 * Adds a new thread of a party that is going to participate in the negotiation of this negotiation context.
	 * @param thread
	 */
	public abstract void addThread(URI thread);
	
	/**
	 * Gets the current state of a negotiation context.
	 * @return
	 */
	public abstract NegotiationState getState();
	/**
	 * Changes the state of a negotiation context.
	 * @param state
	 */
	public abstract void setState(NegotiationState state);

	/**
	 * Adds the negotiation messages that have been received and the negotiation messages that have been sent.
	 * @param msg
	 */	
	public abstract void addNegotiationMessage(INegotiationMessage<?> msg);
	/**
	 * Queries the last negotiation message that has been received.
	 * @return
	 */
	public abstract INegotiationMessage<?> getLastNegotiationMessage();
	/**
	 * Gets the i-th zero-based negotiation message that has been sent or received.
	 * @param i
	 * @return
	 */
	public abstract INegotiationMessage<?> getNegotiationMessage(long i);
	/**
	 * Gets the number of negotiation messages that have been sent or received. 
	 * @return
	 */
	public abstract long getNumberNegotiationMessages(); 
	/**
	 * Gets all negotiation messages that have been sent or received since the negotiation started. 
	 * @return
	 */
	public abstract List<? extends INegotiationMessage<?>> getNegotiationMessages();

}