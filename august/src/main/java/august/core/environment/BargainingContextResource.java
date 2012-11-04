/*
 * CoordinationAnalyser.java
 *
 * Created on August 7, 2007, 1:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import negofast.bargaining.environment.bargainingcontext.BargainingState;
import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.core.environment.events.IEventListener;
import negofast.core.environment.negotiationcontext.INegotiationContext;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.negotiationcontext.NegotiationEventType;
import negofast.core.environment.negotiationcontext.NegotiationState;
import negofast.core.environment.threadcontext.IThreadContextData;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IProposal;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class BargainingContextResource implements IBargainingContextData {
    
    private INegotiationContextData component;
    private Map<URI,BargainingState> bindingStates;
    
    
    private ServiceRegistry registry;
    
    
    /** Creates a new instance of CoordinationAnalyser */
    public BargainingContextResource(INegotiationContextData comp, ServiceRegistry r) {
    	registry = r;
    	component = comp;
    	bindingStates = new HashMap<URI, BargainingState>();
    }

	public URI getParty(URI ctx) {
		URI result = null;
		
		IThreadContextData threadContext = registry.getThreadContext();
		Iterator<URI> threads = this.getThreads(ctx).iterator();
		
		if (threads.hasNext()) 
			result = threadContext.getThreadParty(threads.next()); 
		
		return result; 
	}
  
	
    public INegotiationMessage<IProposal<?>> getLastNegotiationMessage(URI negotiation) {
    	return (INegotiationMessage<IProposal<?>>) component.getLastNegotiationMessage(negotiation);    	
    }
    
    public INegotiationMessage<IProposal<?>> getNegotiationMessage(URI negotiation, int i) {
    	return (INegotiationMessage<IProposal<?>>) component.getNegotiationMessage(negotiation, i);
    }
    
    public List<INegotiationMessage<IProposal<?>>> getNegotiationMessages(URI negotiation) {
    	return (List<INegotiationMessage<IProposal<?>>>) component.getNegotiationMessages(negotiation);
    }
	

    
    // TODO getter and setter of bargaining state
    public BargainingState getBargainingState(URI thread) {
        return null;
    }
    public void setBargainingState(URI context, BargainingState newState) {
    }
    

	public URI addNegotiationContext(URI thread) {
		return component.addNegotiationContext(thread);
	}

	
	public void addNegotiationMessage(URI negotiation,
			INegotiationMessage<?> msg) {
		component.addNegotiationMessage(negotiation, msg);
	}

	
	public void addThread(URI negotiation, URI thread) {
		component.addThread(negotiation, thread);
	}

	
	public int countActiveNegotiations() {
		return component.countActiveNegotiations();
	}

	
	public int countNegotiationMessages(URI negotiation) {
		return component.countNegotiationMessages(negotiation);
	}

	
	public Set<URI> getActiveNegotiationsURI() {
		return component.getActiveNegotiationsURI();
	}

	
	public INegotiationContext getNegotiationContext(URI negotiation) {
		return component.getNegotiationContext(negotiation);
	}

	
	public INegotiationProtocolInstance getNegotiationProtocol(URI negotiation) {
		return component.getNegotiationProtocol(negotiation);
	}

	
	public Set<URI> getNegotiationsURI() {
		return component.getNegotiationsURI();
	}

	
	public Set<URI> getNegotiationsURIByState(NegotiationState state) {
		return component.getNegotiationsURIByState(state);
	}

	
	public Date getStartDate(URI negotiation) {
		return component.getStartDate(negotiation);
	}

	
	public NegotiationState getState(URI negotiation) {
		return component.getState(negotiation);
	}

	
	public Collection<URI> getThreads(URI negotiation) {
		return component.getThreads(negotiation);
	}

	
	public void setState(URI negotiation, NegotiationState state) {
		component.setState(negotiation, state);
	}

	
	public void subscribeNegotiationEvent(URI negotiation,
			NegotiationEventType[] events, IEventListener listener) {
		component.subscribeNegotiationEvent(negotiation, events, listener);
	}

	public void subscribeEvent(NegotiationEventType[] events, IEventListener listener) {
		component.subscribeEvent(events, listener);
	}

	
//  public double bestProposalEvaluation() {
//	return bestProposal;
//}
//    public void addNegotiationMessage(URI ctx, INegotiationMessage<? extends IProposal<?>> msg) {
//        double evaluation = prefs.evaluate(msg.getProposal());
//        if ( evaluation > bestProposal)
//            bestProposal = evaluation;
//    }
}
