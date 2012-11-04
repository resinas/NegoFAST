package negofast.core.interactions.requestcommitapproval;

import java.net.URI;


/**
 * This interaction is to request approval for sending a binding negotiation
 * message (sending a commit or an accept). There are two
 * participants: the CommitHandler, which implements interface {@link ICommitHandler}, and the
 * NegotiationCoordinator, which implements this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface ICommitRequester {
	/**
	 * Notifies that the approval request has been accepted for the given thread context. 
	 * @param thread
	 */
    public void approved(URI thread);
    
    /**
     * Notifies that the approval request has been rejected for the given thread context.
     * @param thread
     */
    public void rejected(URI thread);
}
