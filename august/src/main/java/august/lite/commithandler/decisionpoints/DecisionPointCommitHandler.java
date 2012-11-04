package august.lite.commithandler.decisionpoints;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.interactions.requestcommitapproval.ApprovalType;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestcommitapproval.ICommitRequester;
import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IProposal;
import august.infrastructure.registry.ServiceRegistry;

public class DecisionPointCommitHandler implements ICommitHandler<IProposal<?>> {

	private static Logger log = Logger.getLogger("august.commit");
	
		
	private List<Request> pendingRequests;
	private List<Request> waitingAccept;
	private IPreferencesResource prefs;
	private INegotiationContextData negotiationContext;
	
	private int availableAgreements;
	private IRequestEvaluator evaluator;
	
	private long tini;
	private long tmax;
	
	public DecisionPointCommitHandler(ServiceRegistry r) {

		pendingRequests = new LinkedList<Request>();
		waitingAccept = new ArrayList<Request>();
		
		prefs = r.getPreferences();
		negotiationContext = r.getNegotiationContext();
		
		evaluator = new DefaultEvaluator(prefs);
		
		tini = r.getSystemContext().getCreationDate().getTime();
		
		loadPreferences();
	}
	
	public synchronized void approvalRequest(URI thread, IProposal<?> p, ApprovalType t,
			ICommitRequester requester) {
		
		log.fine("Commit requested: "+thread+" ["+availableAgreements+"]");
		Request req = new Request(thread, p, t, requester, evaluator);		

		
		if (! acceptable(req)) {
			requester.rejected(thread);
		}
		else {
			pendingRequests.add(req);
			log.fine("New pending request: "+thread+" ["+pendingRequests.size()+"]["+availableAgreements+"]");
		}
		
		if (availableAgreements > 0 && isDecisionPoint()) {
			makeDecision();
		}
			
		
		log.fine("End new pending request: "+thread+" ["+pendingRequests.size()+"]");
		
	}

	public synchronized void fail(URI thread) {
		log.fine("Fail received: "+thread+" ["+availableAgreements+"]");
		
		if (waitingAccept.remove(new Request(thread))) {
			log.fine("Removed from waiting: "+thread+" ["+availableAgreements+"]");
			availableAgreements++;
		}
		else {
			log.fine("Removed from pending: "+thread+" ["+availableAgreements+"]");
			pendingRequests.remove(new Request(thread));
		}
		
		if (availableAgreements > 0 && isDecisionPoint()) {
			makeDecision();
		}		
	}

	public synchronized void success(URI thread) {
		Logger.getLogger("august.commit").fine("Success received: "+thread+" ["+availableAgreements+"]");

		waitingAccept.remove(new Request(thread));
		
		if (availableAgreements > 0 && isDecisionPoint()) {
			makeDecision();
		}

	}	
	
	private synchronized void loadPreferences() {
		IPreferencesDocument<?,?> p = prefs.getPreferences();
		
		Date dl = p.getDeadline();
		if (dl != null) {
			tmax = dl.getTime();
		}
		else tmax = Long.MAX_VALUE;

		int an = p.getAgreementsNumber();
		if (an > 0) {
			availableAgreements = an;
		}
		else availableAgreements = Integer.MAX_VALUE;		

	}
	
	private synchronized boolean isDecisionPoint() {
		boolean result = false;
		
		result |= pendingRequests.size() > 0.7 * negotiationContext.getActiveNegotiationsURI().size();
		log.fine("is decision point (1): "+result + "(" + pendingRequests.size() + 
				")("+negotiationContext.getActiveNegotiationsURI().size()+")");
		
		if (tmax != Long.MAX_VALUE) {
			long t = Calendar.getInstance().getTimeInMillis();
			result |= ((t - tini) / (tmax - tini)) > 0.90;			
		}
		
		log.fine("is decision point (2): "+result);
		
		return result;		
	}
	
	private synchronized void makeDecision() {
		Collections.sort(pendingRequests);
		int size = pendingRequests.size();
		ListIterator<Request> it = pendingRequests.listIterator(size);
		
		while (availableAgreements > 0 && it.hasPrevious()) {
			Request r = it.previous();
			it.remove();
			waitingAccept.add(r);
			availableAgreements--;

			r.sendAccept();
			log.fine("Accepted agreement: "+r.getEvaluation());
		}
		
		it = pendingRequests.listIterator();
		while (it.hasNext()) {
			Request r = it.next();
			it.remove();
			r.sendFail();
		}
		
	}
	
	private synchronized boolean acceptable(Request req) {
		return true;
	}

}
