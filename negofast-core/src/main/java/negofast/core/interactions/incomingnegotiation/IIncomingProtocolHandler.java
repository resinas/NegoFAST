package negofast.core.interactions.incomingnegotiation;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;

/**
 * By means of this interaction, the ProtocolHandler notifies the automated negotiation system that another party has sent a request to start a protocol negotiation and asks for permission to proceed with it.
 * @author Manuel Resinas
 *
 */

public interface IIncomingProtocolHandler {
	/**
	 * Grants permission to start a negotiation with the given party. In addition it assigns it to a new thread context and gives a reference that shall receive the result of the negotiation. 
	 * @param thread
	 * @param party
	 * @return
	 */
    public IProtocolHandler acceptNegotiation(URI thread, URI party);

    /**
     * Rejects starting a negotiation with the given party.
     * @param party
     */
    public void rejectNegotiation(URI party);
}
