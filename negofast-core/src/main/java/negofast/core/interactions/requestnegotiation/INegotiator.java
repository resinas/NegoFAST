package negofast.core.interactions.requestnegotiation;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;


/**
 * This interaction is the request for starting a negotiation context that
 * implements the execution of a negotiation protocol instance with one or
 * several parties. It also includes the notification of the results of this
 * negotiation and the request for cancellation of the negotiation if necessary.
 * The ThreadCoordinator must implement {@link INegotiationRequester} interface, whereas the
 * NegotiationCoordinator must implement this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface INegotiator {
	/**
	 * Requests the NegotiationCoordinator to start a negotiation and includes the URI of the thread context, the reference to the ProtocolHandler that shall manage the interaction with the other party and the reference to the requester to notify it about the results of the negotiation. It returns an URI to identify the negotiation context that has been created. 
	 * @param thread
	 * @param protocolHandler
	 * @param requester
	 */
    public void negotiate(URI thread, IProtocolHandler protocolHandler, INegotiationRequester requester);
    /**
     * Requests the cancellation of the negotiation in the given negotiation context. 
     * @param thread
     */
    public void cancel(URI thread);
}
