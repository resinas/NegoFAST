package negofast.core.model;

import java.net.URI;
import java.util.Collection;

/**
 * This element models the negotiation protocol instance that is performed by a negotiation thread. 
 * @author Manuel Resinas
 *
 */
public interface INegotiationProtocolInstance {

	/**
	 * Returns an identifier of the negotiation protocol. This identifier may have no semantics associated to it or it may be a concept in an ontology.
	 * @return
	 */
	public abstract URI getProtocol();

	/**
	 * Returns whether the automated negotiation system is the initiator of the negotiation protocol or not, \ie who sends the first message in the protocol.
	 * @return
	 */
	public abstract boolean isInitiator(); 

	/**
	 * Returns the configuration of the negotiation protocol, which may specify some specific characteristics of the negotiation protocol such as a timeout.
	 * @return
	 */
	public abstract Collection<? extends IStatement> getConfiguration();

}