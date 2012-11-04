package august.lite.commithandler.decisionpoints;

import java.net.URI;
import java.util.logging.Logger;

import negofast.core.interactions.requestcommitapproval.ApprovalType;
import negofast.core.interactions.requestcommitapproval.ICommitRequester;
import negofast.core.model.IProposal;

public class Request implements Comparable<Request> {

	private URI thread;
	private IProposal<?> proposal;
	private ApprovalType t;
	private ICommitRequester requester;
	private double evaluation;

	public Request(URI thread) {
		this.thread = thread;
	}
	
	public Request(URI thread, IProposal<?> p, ApprovalType t, ICommitRequester r, IRequestEvaluator ev) {
		this.thread = thread;
		this.proposal = p;
		this.t = t;
		this.requester = r;
		this.evaluation = ev.evaluate(this);
	}
	
	public void sendAccept() {
		Logger.getLogger("august.commit").fine("Accepted commit: " + thread);
		requester.approved(thread);
	}
	
	public void sendFail() {
		Logger.getLogger("august.commit").fine("Rejected commit: " + thread);
		requester.rejected(thread);		
	}	

	public boolean equals(Object o) {
		boolean result = false;
		
		if (o instanceof Request) {
			Request r = (Request) o;				
			result = (thread == r.thread);
		}
		else if (o instanceof URI) {
			result = (thread.equals(o));
		}
		
		return result;
	}
	
	public int compareTo(Request o) {
		return ((Double)evaluation).compareTo(o.evaluation);
	}

	public URI getThread() {
		return thread;
	}

	public IProposal<?> getProposal() {
		return proposal;
	}

	public ApprovalType getApprovalType() {
		return t;
	}

	public ICommitRequester getRequester() {
		return requester;
	}

	public double getEvaluation() {
		return evaluation;
	}
}
