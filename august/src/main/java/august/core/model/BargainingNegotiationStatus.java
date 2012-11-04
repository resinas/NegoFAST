/*
 * BargainingNegotiationStatus.java
 *
 * Created on August 7, 2007, 1:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import negofast.bargaining.model.INegotiationPolicy;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

/**
 *
 * @author resman
 */
public class BargainingNegotiationStatus<P extends IProposal<?>> implements INegotiationStatus<P> {
    
	private Date initialTime;
    private INegotiationMessage<P> sent;
    private INegotiationMessage<P> received;
    private INegotiationMessage<P> generated;
    private boolean approvalRejected;
    private Collection<INegotiationPolicy> policies;
    
    /**
     * Creates a new instance of BargainingNegotiationStatus
     */
    public BargainingNegotiationStatus() {
    	initialTime = Calendar.getInstance().getTime();
    }

    public INegotiationMessage<P> getSent() {
        return sent;
    }

    public void setSent(INegotiationMessage<P> sent) {
        this.sent = sent;
    }

    public INegotiationMessage<P> getReceived() {
        return received;
    }

    public void setReceived(INegotiationMessage<P> received) {
        this.received = received;
    }

    public INegotiationMessage<P> getGenerated() {
        return generated;
    }

    public void setGenerated(INegotiationMessage<P> generated) {
        this.generated = generated;
    }

    public boolean isApprovalRejected() {
        return approvalRejected;
    }

    public void setApprovalRejected(boolean approvalRejected) {
        this.approvalRejected = approvalRejected;
    }

    public Collection<INegotiationPolicy> getNegotiationPolicies() {
        return policies;
    }

    public void setPolicies(Collection<INegotiationPolicy> policies) {
        this.policies = policies;
    }

	public Date getInitialTime() {
		return initialTime;
	}
    
    
}
