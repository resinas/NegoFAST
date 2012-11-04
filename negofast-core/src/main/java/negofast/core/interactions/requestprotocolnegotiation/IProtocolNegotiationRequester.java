package negofast.core.interactions.requestprotocolnegotiation;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.model.INegotiationProtocolInstance;


/**
 * This interaction is a request for the ProtocolNegotiator to agree with the
 * other party the negotiation protocol that shall be used during the
 * negotiation. It is an asynchronous request. It involves the
 * ProtocolNegotiator, which implements interface {@link IProtocolNegotiator},
 * and the ThreadCoordinator, which implements this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IProtocolNegotiationRequester {
	/**
	 * Notifies that the protocol negotiation finished successfully and includes the URI of the thread context, the selected negotiation protocol instance and a reference to the ProtocolHandler that is configured to manage the interactions with the other party.
	 * @param thread
	 * @param protocol
	 * @param handler
	 */
    public void successfulProtocolNegotiation(URI thread, INegotiationProtocolInstance protocol, IProtocolHandler handler);

    /**
     * Notifies that the protocol negotiation for the given thread context failed.
     * @param thread
     */
    public void failedProtocolNegotiation(URI thread);
}
