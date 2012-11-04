package negofast.bargaining.interactions.createproposal;

import java.net.URI;

import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.IProposal;


/**
 * The goal of this interaction is to obtain a proposal that shall be sent as part of a negotiation message to the other negotiating party. 
 * @author Manuel Resinas
 *
 * @param <P>
 */
public interface IProposalBuilder<P extends IProposal<?>> {
	
	/**
	 * Configures the ProposalBuilder for the given negotiation context. The configuration is specific to each ProposalBuilder, and it is mainly intended to convert negotiation policies into builder-specific configuration parameters.
	 * @param context
	 * @param builderConfig
	 */
    public void configure(URI context, Object builderConfig);

    /**
     * Requests the generation of a new proposal for the given negotiation context. It also provides the current status of the negotiation and a reference to the requester to send the proposal back. Note that, in this case, the bargaining performatives that could be sent together with this proposal are not provided.
     * @param context
     * @param status
     * @param requester
     */
    public void generateProposal(URI context, INegotiationStatus<P> status, IProposalRequester<? super P> requester);

    /**
     * Cancels the generation of the proposal and returns either a valid proposal or a null value if it could not be generated.
     * @param context
     * @return
     */
    public P cancelGeneration(URI context);
    
}
