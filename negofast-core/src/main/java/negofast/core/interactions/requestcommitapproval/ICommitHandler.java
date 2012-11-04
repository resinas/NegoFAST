package negofast.core.interactions.requestcommitapproval;

import java.net.URI;

import negofast.core.interactions.requestcommitapproval.ApprovalType;
import negofast.core.model.IProposal;


/**
 * This interaction is to request approval for sending a binding negotiation
 * message (sending a commit or an accept). There are two
 * participants: the CommitHandler, which implements this interface, and the
 * NegotiationCoordinator, which implements interface {@link ICommitRequester}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface ICommitHandler<P extends IProposal<?>> {
	/**
	 * Requests approval to send a binding negotiation message to the CommitHandler and includes the thread context URI; the proposed agreement; the type of approval represented by enumeration {@link ApprovalType}; and a reference to the requester. 
	 * @param thread
	 * @param p
	 * @param t
	 * @param requester
	 */
    public void approvalRequest(URI thread, P p, ApprovalType t, ICommitRequester requester);

    /**
     * Notifies that an approval previously requested has failed. 
     * @param thread
     */
    public void fail(URI thread);

    /**
     * Notifies that the given thread context, which received a previous approval, has successfully reached an agreement.
     * @param thread
     */
    public void success(URI thread);
}
