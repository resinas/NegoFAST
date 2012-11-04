/*
 * Context.java
 *
 * Created on August 4, 2007, 1:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment.negotiationcontext;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import negofast.core.environment.events.IEventListener;
import negofast.core.environment.negotiationcontext.INegotiationContext;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.negotiationcontext.NegotiationEventType;
import negofast.core.environment.negotiationcontext.NegotiationState;
import negofast.core.environment.threadcontext.IThreadContextData;
import negofast.core.environment.threadcontext.ThreadState;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;
import august.core.environment.events.EventManager;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class NegotiationContextResource implements INegotiationContextData {

    private URI userURI;

	private Map<URI,INegotiationContext> negotiations;    
    private long contextCounter = 0;
    private ServiceRegistry registry;
    
    private EventManager<NegotiationEventType> eventManager;
    
    
    /** Creates a new instance of Context */
    public NegotiationContextResource(URI user, ServiceRegistry r) {
    	userURI = user;
    	registry = r;
    	eventManager = new EventManager<NegotiationEventType>(URI.create("negofast:negotiation"));
    	negotiations = new HashMap<URI, INegotiationContext>();
    }

    public synchronized URI addNegotiationContext(URI thread) {
        //URI contextURI = URI.create("negofast:threadid:"+contextCounter);
		IThreadContextData threadContext = registry.getThreadContext();
    	INegotiationProtocolInstance protocol = threadContext.getNegotiationProtocol(thread); 
        
        NegotiationContext newContext = new NegotiationContext(thread, thread, protocol);
        negotiations.put(thread, newContext);
        contextCounter++;

        eventManager.sendEvent(NegotiationEventType.negotiationCreated, thread);
        
        return thread;
    }

    public Set<URI> getNegotiationsURI() {
        return negotiations.keySet();
    }

    public Set<URI> getActiveNegotiationsURI() {
    	Set<URI> result = new HashSet<URI>();
    	
    	for (INegotiationContext t : negotiations.values()) {
    		NegotiationState state = t.getState();
    		if (! state.equals(NegotiationState.finishedSuccessfully) && 
    				! state.equals(ThreadState.finishedUnsuccessfully))
    			result.add(t.getURI());
    	}

    	return result;
    }

    public Set<URI> getNegotiationsURIByState(NegotiationState state) {
    	Set<URI> result = new HashSet<URI>();
    	
    	for (INegotiationContext t : negotiations.values()) {
    		if (state.equals(t.getState()))
    			result.add(t.getURI());
    	}

    	return result;
    }

    public synchronized void setState(URI negotiation, NegotiationState state) {
        getNegotiationContext(negotiation).setState(state);
        eventManager.sendEvent(NegotiationEventType.stateChanged, negotiation);
        
		if (state.equals(NegotiationState.finishedSuccessfully) || 
				 state.equals(ThreadState.finishedUnsuccessfully)) {
			eventManager.sendEvent(NegotiationEventType.negotiationFinished, negotiation);
		}

    }

    public NegotiationState getState(URI negotiation) {
        return getNegotiationContext(negotiation).getState();
    }

    public INegotiationProtocolInstance getNegotiationProtocol(URI ctx) {
        return getNegotiationContext(ctx).getProtocol();
    }

    public synchronized void addNegotiationMessage(URI ctx, INegotiationMessage<?> msg) {
        getNegotiationContext(ctx).addNegotiationMessage(msg);

        URI sender = msg.getSender();
        if (sender.equals(userURI)) {
        	eventManager.sendEvent(NegotiationEventType.messageSent, ctx, (Integer) countNegotiationMessages(ctx) - 1);
        }
        else {
        	eventManager.sendEvent(NegotiationEventType.messageReceived, ctx, (Integer) countNegotiationMessages(ctx) - 1);
        }
    }

    public INegotiationMessage<?> getLastNegotiationMessage(URI negotiation) {
        return getNegotiationContext(negotiation).getLastNegotiationMessage();
    }

    public INegotiationMessage<?> getNegotiationMessage(URI negotiation, int i) {
        return getNegotiationContext(negotiation).getNegotiationMessage(i);
    }

    public int countNegotiationMessages(URI negotiation) {
        return (int) getNegotiationContext(negotiation).getNumberNegotiationMessages();
    }

    public List<? extends INegotiationMessage<?>> getNegotiationMessages(URI negotiation) {
        return getNegotiationContext(negotiation).getNegotiationMessages();
    }

    public INegotiationContext getNegotiationContext(URI negotiation) {
        return negotiations.get(negotiation);
    }

    public void subscribeEvent(NegotiationEventType[] events, IEventListener listener) {
    	eventManager.subscribeEvent(events, listener);
    }
    
    public void subscribeNegotiationEvent(URI negotiation, NegotiationEventType[] events, IEventListener listener) {
    	eventManager.subscribeEvent(negotiation, events, listener);
    }


	public void addThread(URI negotiation, URI thread) {
		getNegotiationContext(negotiation).addThread(thread);
	}

	public Date getStartDate(URI negotiation) {
		return getNegotiationContext(negotiation).getStartDate();
	}

	public Collection<URI> getThreads(URI negotiation) {
		return getNegotiationContext(negotiation).getThreads();
	}

	public int countActiveNegotiations() {
		return getActiveNegotiationsURI().size();
	}
    
}
