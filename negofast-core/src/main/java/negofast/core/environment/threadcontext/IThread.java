package negofast.core.environment.threadcontext;

import java.net.URI;

import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IPartyInformation;

/**
 * A thread context, modelled by means of interface IThread has one
 * associated party and one {@link ThreadState} and may have information about
 * the party ({@link IPartyInformation}), the negotiation protocol selected for
 * the negotiation ({@link INegotiationProtocolInstance}) and one negotiation
 * context in which the negotiation takes place
 * 
 * @author Manuel Resinas
 * 
 */
public interface IThread {
	/**
	 * Returns an identifier for the thread context.
	 * @return
	 */
	public abstract URI getURI();

	/**
	 * Returns the current state of the thread context.
	 * @return
	 */
	public abstract ThreadState getState();
	/**
	 * Sets the current state of the thread context.
	 * @param state
	 */
	public abstract void setState(ThreadState state);

	/**
	 * Returns the identifier of the party reference that is being processed by this thread.
	 * @return
	 */
	public abstract URI getParty();
	/**
	 * Returns the information gathered about the given party for this negotiation thread. This information has been gathered in the getting information state.
	 * @return
	 */
	public abstract IPartyInformation<?> getInformation();
	/**
	 * Sets the information about the party that has been gathered via role \inline{inquirer}.
	 * @param info
	 */
	public abstract void setInformation(IPartyInformation<?> info);
	
	/**
	 * Returns the negotiation protocol instance that has been selected for this thread.
	 * @return
	 */
	public abstract INegotiationProtocolInstance getNegotiationProtocol();
	/**
	 * Sets the negotiation protocol instance.
	 * @param protocol
	 */
	public abstract void setNegotiationProtocol(INegotiationProtocolInstance protocol);
	/**
	 * Gets the negotiation context that negotiates with the party of this thread. 
	 * @return
	 */
	public abstract URI getNegotiationContext();
	/**
	 * Sets the negotiation context that negotiates with the party of this thread.
	 * @return
	 */
	public abstract void setNegotiationContext(URI context);
}