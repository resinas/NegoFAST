package negofast.bargaining.interactions.requestresponse;

import java.net.URI;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;



/**
 * The goal of this interaction is to obtain a negotiation message that shall be
 * sent as a response to the other negotiating party. The interaction is
 * asynchronous and there are two participants in it: the BilateralNegotiator,
 * which requests the negotiation message and implements this interface, and the PerformativeSelector, which returns the
 * generated negotiation message and implements interface
 * {@link IResponseGenerator}.
 * 
 * @author Manuel Resinas
 * 
 * @param
 * <P>
 */
public interface IResponseRequester<P extends IProposal<?>> {
	/**
	 * Receives the generated negotiation message from the PerformativeSelector.
	 * @param context
	 * @param msg
	 */
    public void negotiationMessage(URI context, INegotiationMessage<P> msg);
}
