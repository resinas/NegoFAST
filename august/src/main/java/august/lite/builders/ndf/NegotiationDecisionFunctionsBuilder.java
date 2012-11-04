/*
 * SimpleStrategy.java
 *
 * Created on August 9, 2007, 11:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.lite.builders.ndf;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class NegotiationDecisionFunctionsBuilder implements IProposalBuilder<IProposalEquals> {

    private static Logger log = Logger.getLogger("august.builder.ndf");

	private IPreferencesDocLite prefs;
	private IBargainingContextData ctx;
	
	private Map<URI, StrategyExecutor> executors;
	
    /** Creates a new instance of SimpleStrategy */
    public NegotiationDecisionFunctionsBuilder(ServiceRegistry registry) {
    	IPreferencesResource prefResource = registry.getPreferences();
    	ctx = registry.getBargainingContext();
    	URI prefFormat = URI.create("negofast:preferences.simpleutility");
    	prefs = prefResource.getPreferences(prefFormat);
    	executors = new HashMap<URI, StrategyExecutor>();
    }

    public void configure(URI thread, Object strategyConfig) {
    	Properties conf = (Properties) strategyConfig;
    	String tactic = conf.getProperty("tactic");
    	StrategyExecutor e = new StrategyExecutor(prefs, ctx, thread);
    	
    	if (tactic.equals("behaviour")) {    		
    		e.setBehaviourTactic(1);
			log.fine("["+thread+"] NDF behaviour 1");
    		
    	}
    	else {
    		double eagerness = (Double) conf.get("eagerness");
    		if (eagerness > 0.6) {
    			e.setTimeTactic(2);
    			log.fine("["+thread+"] NDF time 2");
    		}
    		else {
    			e.setTimeTactic(0.5);
    			log.fine("["+thread+"] NDF time 0.5");
    		}
    	}
    	
    	executors.put(thread, e);
    }

    public void generateProposal(URI thread, INegotiationStatus<IProposalEquals> status, IProposalRequester<? super IProposalEquals> requester) {    	
    	StrategyExecutor e = executors.remove(thread);    	
    	if (e == null) e = autoconfigure(thread, status);
    	
    	IProposalEquals p = e.execute(status);
    	requester.proposal(thread, p);
    }

    public IProposalEquals cancelGeneration(URI thread) {
		return null;
    }   
    
    private StrategyExecutor autoconfigure(URI thread, INegotiationStatus<IProposalEquals> status) {
    	StrategyExecutor e = new StrategyExecutor(prefs, ctx, thread);
    	e.setTimeTactic(0.5);
    	
    	return e;
    }
    
}
