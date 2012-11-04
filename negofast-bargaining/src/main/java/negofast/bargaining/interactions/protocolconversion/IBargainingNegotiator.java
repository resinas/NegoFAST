package negofast.bargaining.interactions.protocolconversion;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.BargainingPerformative;
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
public interface IBargainingNegotiator {
	/**
	 * By means of this method, the BargainingProtocolHandler indicates the BilateralNegotiator that it must start the negotiation by sending a negotiation message with one of the allowed performatives.
	 * @param context
	 * @param allowed
	 */
    public void startNegotiation(URI context, Collection<BargainingPerformative> allowed);

    /**
     * Notifies that an error has happened during the negotiation (e.g. a time-out or an unexpected message) and, hence, the negotiation must end.
     * @param context
     * @param reason
     */
    public void error(URI context, String reason);
    
    public void cfp(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
    public void propose(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
    public void commit(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
    public void accept(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
    public void withdraw(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
    public void rejectProposal(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
    public void rejectNegotiation(URI context, INegotiationMessage<IProposal<?>> msg, Collection<BargainingPerformative> allowed);
}
