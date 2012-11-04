package august.lite.model;

import java.net.URI;

import negofast.models.lite.IAssessmentLite;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import negofast.models.lite.statements.constraint.IEquals;
import negofast.models.lite.statements.namevalue.INameValue;
import negofast.models.lite.statements.utility.IWeightedUtility;


public class AssessmentLite implements IAssessmentLite {

    private IPreferencesDocLite prefs;
    
    public AssessmentLite(IPreferencesDocLite p) {
    	prefs = p;
    }

	public int compare(IProposalEquals p1, IProposalEquals p2) {
		int result = 0;
		double diff = evaluate(p1) - evaluate(p2);
		
		if (diff > 0)
			result = 1;
		else if (diff < 0)
			result = -1;
		
		return result;
	}

	public double evaluate(IProposalEquals p) {
		double utility = 0;
		URI myself = prefs.getUser();
		
		// We assume that the constraints of terms are ConstraintEquals (Model 1)
		for (ITermEquals t : p.getTerms()) {
			IEquals c = t.getConstraint();
			URI attribute = c.getVariable();
			IWeightedUtility pref;
			
			if (t.getObligatedParty().equals(myself)) {
				pref = prefs.getFeature(attribute);
			}
			else {
				pref = prefs.getRequirement(attribute);
			}
			
			if (pref != null)			
				utility += pref.getWeightedUtility(c.getValue());
		}
		
		return utility;
	}

	public boolean satisfies(IProposalEquals p) {
		boolean satisfies = true;
		URI myself = prefs.getUser();
		
		// We assume that the constraints of terms are ConstraintEquals (Model 1)
		for (ITermEquals t : p.getTerms()) {
			IEquals c =  (IEquals) t.getConstraint();
			URI attribute = c.getVariable();
			IWeightedUtility pref;
			
			if (t.getObligatedParty().equals(myself)) {
				pref = prefs.getFeature(attribute);
			}
			else {
				pref = prefs.getRequirement(attribute);
			}
			
			if (pref != null)			
				satisfies &= pref.getDomain().contains(c.getValue());
		}
		
		// Checks the minimum value
		URI u = URI.create("negofast:preferences.guidelines.threshold");
		INameValue threshold = prefs.getNegotiationGuideline(u);
		
		double evaluation = evaluate(p);
		
		satisfies &= evaluation >= (Double) threshold.getValue();
		
		return satisfies;
	}

}
