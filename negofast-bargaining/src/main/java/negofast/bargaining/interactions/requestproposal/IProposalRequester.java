package negofast.bargaining.interactions.requestproposal;

import java.net.URI;

import negofast.core.model.IProposal;


/**
 * The goal of this interaction is to obtain a proposal that shall be sent as
 * part of a negotiation message to the other negotiating party.
 * 
 * The interaction has two participants: the BuilderManager, which provides the
 * proposal and implements interface {@link IBuilderManager}, and the
 * PerformativeSelector, which requests the proposal and implements this interface.
 * 
 * @author Manuel Resinas
 * 
 * @param
 * <P>
 */
public interface IProposalRequester<P extends IProposal<?>> {
	/**
	 * Receives the proposal from the BuilderManager.
	 * @param context
	 * @param msg
	 */
    public void proposal(URI context, P msg);
}
