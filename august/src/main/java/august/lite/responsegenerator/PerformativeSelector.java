/*
 * MessageComposer.java
 *
 * Created on August 8, 2007, 1:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.lite.responsegenerator;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import august.core.responsegenerator.AbstractPerformativeSelector;
import august.core.responsegenerator.IPerformativeSelector;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class PerformativeSelector<P extends IProposal<?>> extends AbstractPerformativeSelector<P> implements IPerformativeSelector<P> {
    
	private IPreferencesResource prefs;
    
    /** Creates a new instance of MessageComposer */
    public PerformativeSelector(ServiceRegistry registry) {
    	super(registry);
        prefs = registry.getPreferences();
    }

    public BargainingPerformative obtainPerformative(URI context, Collection<BargainingPerformative> allowed, 
            INegotiationStatus<P> st) {
        
    	BargainingPerformative result = null;
    	
    	P proposal = null;
    	
    	INegotiationMessage<P> msgRx = st.getReceived();
    	if (msgRx != null)
    		proposal = msgRx.getContent();
    	
    	if (allowed.contains(BargainingPerformative.accept)) {
    		if (proposal == null) 
    			PerformativeSelector.log.fine("["+context+"][Allowed Accept] received null");
    		else
    			PerformativeSelector.log.fine("["+context+"][Allowed Accept] received satisfies: " + prefs.satisfies(proposal) + ", "+prefs.evaluate(proposal));

    		if (proposal != null && prefs.satisfies(proposal)) {
    			result = BargainingPerformative.accept;
    		}
    	}
    	
    	if (result == null && allowed.contains(BargainingPerformative.commit)) {
    		result = BargainingPerformative.commit;
    	}
    	
    	if (result == null) {
    		result = BargainingPerformative.rejectNegotiation;
    	}
    	
    	PerformativeSelector.log.fine("["+context+"]Performative selected: " + result);
    	
        return result;        
    }

	@Override
	public BargainingPerformative obtainPerformative(URI context,
			Collection<BargainingPerformative> allowed,
			INegotiationStatus<P> st, P generatedProposal) {
		// Do nothing. This performative selector does not need a second obtain performative
		return null;
	}

    
}
