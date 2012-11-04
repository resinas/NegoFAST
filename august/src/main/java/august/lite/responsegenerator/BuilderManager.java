/*
 * StrategyBroker.java
 *
 * Created on August 9, 2007, 11:01 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.lite.responsegenerator;

import java.net.URI;
import java.util.Properties;

import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.IProposal;
import august.core.model.NegotiationPoliciesQuery;
import august.core.responsegenerator.AbstractBuilderManager;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class BuilderManager<P extends IProposal<?>> extends AbstractBuilderManager<P> implements IBuilderManager<P> {
    
    private ServiceRegistry registry;
    
    /** Creates a new instance of BuilderManager */
    public BuilderManager(ServiceRegistry r) {
    	super();
        registry = r;
    }

    @Override
	public IProposalBuilder<P> selectBuilder(URI context, INegotiationStatus<P> status) {
    	IProposalBuilder<P> builder;
    	
    	if (status.getSent() == null) {    		
    		builder = registry.getProposalBuilder(URI.create("negofast:proposalBuilders.initial"));
    		log.fine("[" + context + "] Builder: negofast:proposalBuilders.initial");
    	}
    	else {
    		NegotiationPoliciesQuery pol = new NegotiationPoliciesQuery(status.getNegotiationPolicies());
    		double eagerness = (Double) pol.getPolicy(URI.create("negofast:policies.eagerness"));
    		Object deadline = pol.getPolicy(URI.create("negofast:policies.deadline"));
    		
    		Properties p = new Properties();
    		
    		if (deadline == null) {
    			if (registry.getNegotiationContext().countNegotiationMessages(context) > 3) {
    	    		builder = registry.getProposalBuilder(URI.create("negofast:proposalBuilders.negotiationdecisionfunctions"));
        			p.put("tactic", "behaviour");            		
            		builder.configure(context, p);
            		log.fine("[" + context + "] Builder: negofast:proposalBuilders.negotiationdecisionfunctions (behaviour)");
            		
    			}
    			else {
    	    		builder = registry.getProposalBuilder(URI.create("negofast:proposalBuilders.initial"));
    	    		log.fine("[" + context + "] Builder: negofast:proposalBuilders.initial");    	    		
    			}
    		}
    		else {
	    		builder = registry.getProposalBuilder(URI.create("negofast:proposalBuilders.negotiationdecisionfunctions"));
    			p.put("tactic", "time");
    			p.put("eagerness", eagerness);
    			
        		builder.configure(context, p);    			
        		log.fine("[" + context + "] Builder: negofast:proposalBuilders.negotiationdecisionfunctions (time)");
    		}
    	}
    	
        return builder;
    }
    
}
