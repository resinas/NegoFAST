/*
 * NegotiationHistoryResource.java
 *
 * Created on August 5, 2007, 1:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import negofast.core.environment.agreements.IAgreementsResource;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.negotiationhistory.INegotiationHistory;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.environment.systemcontext.ISystemContextData;
import negofast.core.environment.threadcontext.IThreadContextData;

/**
 *
 * @author resman
 */
public class NegotiationHistoryResource implements INegotiationHistory {
    
    private Map<URI,History> history;
    private long counter = 0;
    private static INegotiationHistory instance = null;
    
    private class History {
    	private IAgreementsResource agreementsResource;
    	private IPreferencesResource preferencesResource;
    	private ISystemContextData systemContext;
    	private IThreadContextData threadContext;
    	private INegotiationContextData negotiationContext;
    }
    
    /** Creates a new instance of NegotiationHistoryResource */
    private NegotiationHistoryResource() {
        history = new HashMap<URI,History>();
    }
    
    public static INegotiationHistory getInstance() {
        if (instance == null)
            instance = new NegotiationHistoryResource();
        
        return instance;
    }

	public URI archive(IAgreementsResource agreements,
			IPreferencesResource prefs, ISystemContextData system,
			IThreadContextData thread, INegotiationContextData nego) {
		
        URI newRef = URI.create("negofast:contextid:"+counter);
        counter++;
        
        History h = new History();
        h.agreementsResource = agreements;
        h.preferencesResource = prefs;
        h.systemContext = system;
        h.threadContext = thread;
        h.negotiationContext = nego;
        
        history.put(newRef,h);
        
        return newRef;        
	}
  

    public Set<URI> getArchiveURIs() {
        return history.keySet();
    }

	
	public IAgreementsResource getAgreementsResource(URI ref) {
		return history.get(ref).agreementsResource;
	}

	
	public INegotiationContextData getNegotiationContextData(URI ref) {
		return history.get(ref).negotiationContext;
	}

	
	public IPreferencesResource getPreferencesResource(URI ref) {
		return history.get(ref).preferencesResource;
	}

	
	public ISystemContextData getSystemContextData(URI ref) {
		return history.get(ref).systemContext;
	}

	
	public IThreadContextData getThreadContextData(URI ref) {
		return history.get(ref).threadContext;
	}
}
