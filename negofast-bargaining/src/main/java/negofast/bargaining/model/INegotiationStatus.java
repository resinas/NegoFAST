/*
 * INegotiationStatus.java
 *
 * Created on August 8, 2007, 2:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package negofast.bargaining.model;

import java.util.Collection;
import java.util.Date;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

/**
 * Interface INegotiationStatus represents the status of the
 * negotiation in terms of the last negotiation messages that have been sent or
 * received. This status constitutes the basic information used to build
 * negotiation messages.
 * 
 * @author resman
 */
public interface INegotiationStatus<P extends IProposal<?>> {
	/**
	 * Returns the time when the negotiation protocol started.
	 * @return
	 */
	public Date getInitialTime();
	
	/**
	 * Returns the last negotiation message that has been generated. Note that this does not mean that this negotiation message was sent because it might have not been approved by the CommitHandler.
	 * @return
	 */
    public INegotiationMessage<P> getGenerated();
    /**
     * Returns the last negotiation message that was received.
     * @return
     */
    public INegotiationMessage<P> getReceived();
    /**
     * Returns the last negotiation message that was sent to the other party.
     * @return
     */
    public INegotiationMessage<P> getSent();

    /**
     * Returns the set of negotiation policies that have been set for this negotiation.
     * @return
     */
    public Collection<INegotiationPolicy> getNegotiationPolicies();

    /**
     * Returns whether the CommitHandler did not approve the last negotiation message that was generated for this negotiation. This information can be used to avoid entering in a loop in which the same binding negotiation message is generated and not approved by the CommitHandler.
     * @return
     */
    public boolean isApprovalRejected();
    
}
