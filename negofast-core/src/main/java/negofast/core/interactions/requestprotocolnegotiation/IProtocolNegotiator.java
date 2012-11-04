package negofast.core.interactions.requestprotocolnegotiation;

import java.net.URI;


/**
 * This interaction is a request for the ProtocolNegotiator to agree with the
 * other party the negotiation protocol that shall be used during the
 * negotiation. It is an asynchronous request. It involves the
 * ProtocolNegotiator, which implements this interface,
 * and the ThreadCoordinator, which implements interface
 * {@link IProtocolNegotiationRequester}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IProtocolNegotiator {
	/**
	 * Initiates a protocol negotiation and includes the URI of the thread context and an identifier of the other party, so that the ProtocolNegotiator can interact with it. It also includes the requester that receives the result of the protocol negotiation
	 * @param thread
	 * @param party
	 * @param requester
	 */
    public void negotiateProtocol(URI thread, URI party, IProtocolNegotiationRequester requester);
}
