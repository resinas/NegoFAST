/*
 * PreferencesResource.java
 *
 * Created on August 4, 2007, 5:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment.preferences;

import java.net.URI;

import negofast.core.environment.preferences.IAssessmentMechanism;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IProposal;

/**
 *
 * @author resman
 */
public class PreferencesResource implements IPreferencesResource {
    
    private IPreferencesDocument<?, ?> prefs;
    private URI prefsFormat;
    
    /** Creates a new instance of PreferencesResource */
    public PreferencesResource(IPreferencesDocument<?,?> p) {
        prefs = p;
        prefsFormat = prefs.getFormat();
    }

	public IPreferencesDocument<?, ?> getPreferences() {
		return prefs;
	}

	public <Pref extends IPreferencesDocument<?, ?>> Pref getPreferences(URI format) {
		if (format.equals(prefsFormat))
			return (Pref) prefs;
		else
			throw new PreferencesFormatNotSupportedException();
	}
	
	public URI getUser() {
		return prefs.getUser();
	}

	public IAssessmentMechanism<?,?> getAssessmentMechanism() {
    	// TOIMPROVE It is necessary to choose the assessment mechanism
        return null;
    }

	public <P extends IProposal<?>> int compare(P p1, P p2) {
		IAssessmentMechanism<?,P> assess = AssessmentFactory.createAssessment(prefs, p1);		
		return assess.compare(p1,p2);
	}

	public <P extends IProposal<?>> double evaluate(P p) {
		IAssessmentMechanism<?,P> assess = AssessmentFactory.createAssessment(prefs, p);		
		return assess.evaluate(p);
	}

	public <P extends IProposal<?>> boolean satisfies(P p) {
		IAssessmentMechanism<?,P> assess = AssessmentFactory.createAssessment(prefs, p);		
		return assess.satisfies(p);
	}
}
