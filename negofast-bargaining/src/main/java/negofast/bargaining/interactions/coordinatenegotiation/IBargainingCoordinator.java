package negofast.bargaining.interactions.coordinatenegotiation;

import java.net.URI;

import negofast.core.model.IAgreement;
import negofast.core.model.IProposal;


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
 * the BilateralNegotiator must implement interface {@link ICoordinableNegotiator}
 * 
 * @author Manuel Resinas
 * 
 */
public interface IBargainingCoordinator {
	/**
	 * Requests approval to send a negotiation message composed of the commit performative and the given proposal.
	 * @param context
	 * @param p
	 */
    public void commitApprovalRequest(URI context, IProposal<?> p);

    /**
     * Requests approval to send a negotiation  message composed of the accept performative and the given proposal.
     * @param context
     * @param p
     */
    public void acceptApprovalRequest(URI context, IProposal<?> p);

    /**
     * Notifies that the commit that has been sent was rejected by the other party.
     * @param context
     */
    public void commitRejected(URI context);

    /**
     * Notifies that the given negotiation context has finished unsuccessfully.
     * @param context
     */
    public void finishedUnsuccessfully(URI context);

    /**
     * Notifies that the given negotiation context has finished successfully and sends the resulting agreement.
     * @param context
     * @param a
     */
    public void finishedSuccessfully(URI context, IAgreement a);
}
