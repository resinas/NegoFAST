package august.core.environment.preferences;

import java.net.URI;

import negofast.core.environment.preferences.IAssessmentMechanism;
import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IProposal;
import negofast.models.lite.IPreferencesDocLite;
import august.lite.model.AssessmentLite;

public class AssessmentFactory {

	public static <Pref extends IPreferencesDocument<?, ?>, P extends IProposal<?>> 
	IAssessmentMechanism<Pref, P>  createAssessment(Pref pr, P prop) {
		IAssessmentMechanism<Pref, P> result = null;
		
		if (pr.getFormat().equals(URI.create("negofast:preferences.simpleutility")) && 
				prop.getFormat().equals(URI.create("negofast:proposals.equals"))) {
			result = (IAssessmentMechanism<Pref, P>) new AssessmentLite((IPreferencesDocLite) pr);
		}
		else
			throw new AssessmentNotSupportedException();

		return result;		
	}
}
