package august.lite.commithandler.decisionpoints;

import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.interactions.requestcommitapproval.ApprovalType;

public class DefaultEvaluator implements IRequestEvaluator {

	IPreferencesResource prefs;
	
	public DefaultEvaluator(IPreferencesResource p) {
		prefs = p;
	}
	
	public double evaluate(Request r) {
		double result = prefs.evaluate(r.getProposal());			
		if (r.getApprovalType().equals(ApprovalType.commit)) result *= 0.5;
		
		return result;
		
	}

}
