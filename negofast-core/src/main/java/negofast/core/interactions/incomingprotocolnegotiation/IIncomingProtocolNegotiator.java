package negofast.core.interactions.incomingprotocolnegotiation;

import java.net.URI;

import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;

/**
 * By means of this interaction, the ProtocolNegotiator notifies the automated negotiation system that another party has sent a request to start a protocol negotiation and asks for permission to proceed with it. 
 * @author Manuel Resinas
 *
 */
public interface IIncomingProtocolNegotiator {
	/**
	 * Grants permission to start a protocol negotiation with the given party. In addition it assigns it to a new thread context and gives a reference to the element that must receive the result of the protocol negotiation. 
	 * @param thread
	 * @param party
	 * @param requester
	 */
    public void acceptProtocolNegotiation(URI thread, URI party, IProtocolNegotiationRequester requester);

    /**
     * Rejects starting a protocol negotiation with the given party.
     * @param party
     */
    public void rejectProtocolNegotiation(URI party);
 
}
