package negofast.core.interactions.requestnegotiation;

import java.net.URI;

import negofast.core.model.IAgreement;


/**
 * This interaction is the request for starting a negotiation context that
 * implements the execution of a negotiation protocol instance with one or
 * several parties. It also includes the notification of the results of this
 * negotiation and the request for cancellation of the negotiation if necessary.
 * The ThreadCoordinator must implement this interface, whereas the
 * NegotiationCoordinator must implement interface {@link INegotiator}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface INegotiationRequester {
	/**
	 * Informs that the given agreement has been reached for the specified thread context.
	 * @param thread
	 * @param a
	 */
    public void success(URI thread, IAgreement a);

    /**
     * Indicates that the negotiation has failed for the given thread context.
     * @param thread
     */
    public void fail(URI thread);
}
