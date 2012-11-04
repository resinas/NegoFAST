package negofast.bargaining.interactions.requestresponse;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

/**
 * The goal of this interaction is to obtain a negotiation message that shall be
 * sent as a response to the other negotiating party. The interaction is
 * asynchronous and there are two participants in it: the BilateralNegotiator,
 * which requests the negotiation message and implements interface
 * {@link IResponseRequester}, and the PerformativeSelector, which returns the
 * generated negotiation message and implements this interface.
 * 
 * @author Manuel Resinas
 * 
 * @param
 * <P>
 */
public interface IResponseGenerator<P extends IProposal<?>> {
	/**
	 * Requests the generation of a new negotiation message for the given negotiation context. It also provides the bargaining performatives that can be used in the negotiation message, the current status of the negotiation and a reference to the requester that shall receive the generated negotiation message.
	 * @param context
	 * @param allowed
	 * @param status
	 * @param requester
	 */
    public void generateResponse(URI context, Collection<BargainingPerformative> allowed, 
            INegotiationStatus<P> status, IResponseRequester<P> requester);

    /**
     * Cancels the generation of the message and returns either a valid negotiation message or a null value if no valid negotiation message could be generated.
     * @param context
     * @return
     */
    public INegotiationMessage<P> cancelGeneration(URI context);
}
