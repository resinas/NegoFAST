package negofast.bargaining.interactions.coordinatenegotiation;

import java.net.URI;

import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;


/**
 * This interaction represents the communication between the
 * BargainingCoordinator and the BilateralNegotiator. The goal of this
 * interaction is to allow the BilateralNegotiator to send requests for
 * approval to send binding negotiation messages (i.e. commit or accept approval
 * requests) to the BargainingCoordinator that are later redirected to the
 * CommitHandler. Furthermore, it also involves the sending of initialisation
 * and finalisation messages to and from the BilateralNegotiator.
 * 
 * In this interaction, the BargainingCoordinator must implement interface {@link IBargainingCoordinator}, whereas
 * the bBilateralNegotiator must implement this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface ICoordinableNegotiator {
	/**
	 * Initialises the BilateralNegotiator with a reference to the coordinator and another reference to the BargainingProtocolHandler that manages the interaction with the other party. After receiving this message, the BilateralNegotiator shall start the negotiation.
	 * @param context
	 * @param coordinator
	 * @param protocolHandler
	 */
    public void init(URI context, IBargainingCoordinator coordinator, IBargainingProtocolHandler protocolHandler);

    /**
     * Rejects the approval request made by the bilateral negotiator.
     * @param context
     */
    public void reject(URI context);

    /**
     * Accepts the approval request sent by the bilateral negotiator.
     * @param context
     */
    public void accept(URI context);

    /**
     * Cancels the negotiation.
     * @param context
     */
    public void cancel(URI context);
}
