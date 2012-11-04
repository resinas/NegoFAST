package negofast.bargaining.interactions.protocolconversion;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;



/**
 * This interaction is the implementation of the above-defined abstract
 * proposal-based bilateral negotiation protocol, together with some control
 * messages. To this end, both the BargainingProtocolHandler and the
 * BilateralNegotiator have seven methods in their interfaces
 * ({@link IBargainingProtocolHandler} and {@link IBargainingNegotiator},
 * respectively), one for each negotiation performative of the abstract
 * proposal-based bilateral negotiation protocol (accept, rejectProposal, rejectNegotiation, propose,
 * commit, cfp and withdraw).
 * 
 * @author Manuel Resinas
 * 
 */
public interface IBargainingProtocolHandler extends IProtocolHandler {
	/**
	 * Through this method, the BilateralNegotiator indicates the BargainingProtocolHandler that it is ready to start the negotiation and includes a reference to it.
	 * @param context
	 * @param negotiator
	 */
    public void init(URI context, IBargainingNegotiator negotiator);
    public void cfp(URI context, INegotiationMessage<IProposal<?>> msg);
    public void propose(URI context, INegotiationMessage<IProposal<?>> msg);
    public void commit(URI context, INegotiationMessage<IProposal<?>> msg);
    public void accept(URI context, INegotiationMessage<IProposal<?>> msg);
    public void withdraw(URI context, INegotiationMessage<IProposal<?>> msg);
    public void rejectProposal(URI context, INegotiationMessage<IProposal<?>> msg);
    public void rejectNegotiation(URI context, INegotiationMessage<IProposal<?>> msg);
}
