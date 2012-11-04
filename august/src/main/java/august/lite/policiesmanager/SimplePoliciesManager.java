/*
 * PoliciesGenerator.java
 *
 * Created on August 8, 2007, 11:50 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.lite.policiesmanager;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.bargaining.interactions.submitpolicies.IPolicyReceiver;
import negofast.bargaining.model.INegotiationPolicy;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.statements.namevalue.INameValue;
import august.core.model.NegotiationPolicy;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class SimplePoliciesManager implements IPoliciesManager {
    
	Map<URI, IPolicyReceiver> receivers;
	IPreferencesResource prefs;
	IPreferencesDocLite pref;
	
	double threshold = 0.5;
	long deadline = -1;
	int agreementsNumber = -1;
	double eagerness = 0.5;
	
    /** Creates a new instance of PoliciesGenerator */
    public SimplePoliciesManager(ServiceRegistry r) {
    	prefs = r.getPreferences();
    	pref = prefs.getPreferences(URI.create("negofast:preferences.simpleutility"));
    	receivers = new HashMap<URI, IPolicyReceiver>();
    	
    	loadPreferences();
    }
    
    public synchronized Collection<INegotiationPolicy> initNegotiation(URI context, IPolicyReceiver neg) {
    	receivers.put(context, neg);
    	
    	Collection<INegotiationPolicy> result = new ArrayList<INegotiationPolicy>();
    	
    	result.add(new NegotiationPolicy(URI.create("negofast:policies.threshold"), threshold));    	
    	result.add(new NegotiationPolicy(URI.create("negofast:policies.eagerness"), eagerness));
    	if (deadline > 0)
    		result.add(new NegotiationPolicy(URI.create("negofast:policies.deadline"), deadline));
    	
    	return result;
    }
    
    
    private void loadPreferences() {
    	INameValue nv = pref.getNegotiationGuideline(URI.create("negofast:preferences.guidelines.threshold"));
    	
    	if (nv != null) {
    		threshold = (Double) nv.getValue();
    	}

    	Date dl = pref.getDeadline();
    	if (dl != null) {
    		deadline = dl.getTime();
    	}
    	
    	int an = pref.getAgreementsNumber();
    	if (an > 0) {
    		agreementsNumber = an;
    	}

    	nv = pref.getNegotiationGuideline(URI.create("negofast:preferences.guidelines.eagerness"));
    	if (nv != null) {
    		eagerness = (Double) nv.getValue();
    	}

    }

	public void endNegotiation(URI context) {
		receivers.remove(context);
	}
    
}
