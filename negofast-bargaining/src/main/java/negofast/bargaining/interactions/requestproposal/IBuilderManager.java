package negofast.bargaining.interactions.requestproposal;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.IProposal;

/**
 * The goal of this interaction is to obtain a proposal that shall be sent as
 * part of a negotiation message to the other negotiating party.
 * 
 * The interaction has two participants: the BuilderManager, which provides the
 * proposal and implements this interface, and the
 * PerformativeSelector, which requests the proposal and implements interface
 * {@link IProposalRequester}.
 * 
 * @author Manuel Resinas
 * 
 * @param
 * <P>
 */
public interface IBuilderManager<P extends IProposal<?>> extends IProposalRequester<P> {

	/**
	 * Requests the generation of a new proposal for the given negotiation context. It also provides the bargaining performatives that may be sent together with this proposal, the current status of the negotiation and a reference to the requester to send the proposal back.
	 * @param context
	 * @param selected
	 * @param status
	 * @param requester
	 */
    public void generateProposal(URI context, Collection<BargainingPerformative> selected, 
    		INegotiationStatus<P> status, IProposalRequester<? super P> requester);

    /**
     * Cancels the generation of the proposal and returns either a valid proposal or a null value if it could not be generated.
     * @param context
     * @return
     */
    public P cancelGeneration(URI context);

}
