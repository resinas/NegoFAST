package august.core.environment.events;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negofast.core.environment.events.EventType;
import negofast.core.environment.events.IEventListener;

public class EventManager<T extends EventType> {
	private Map<T, Map<URI, List<IEventListener>>> contextListeners;
    private Map<T, List<IEventListener>> globalListeners;	
    private URI source;

    public EventManager(URI src) {
    	contextListeners = new HashMap<T, Map<URI, List<IEventListener>>>();
    	globalListeners = new HashMap<T, List<IEventListener>>();

    	source = src;
    }
    
	public void subscribeEvent(T[] events, IEventListener callback) {
        for (T event : events) {
        	if (! globalListeners.containsKey(event)) {
        		globalListeners.put(event, new ArrayList<IEventListener>());
        	}
            List<IEventListener> l = globalListeners.get(event);
            l.add(callback);
        }		
	}
    
    
	public void subscribeEvent(URI ctx, T[] events, IEventListener callback) {
        for (T event : events ) {
            Map<URI,List<IEventListener>> m = contextListeners.get(event);
            
            // Adds a new EventType if it hasn't been created previously
            if (m == null) {
            	m = new HashMap<URI,List<IEventListener>>();
            	contextListeners.put(event, m);
            }
            
            // Adds the new listener to the list of listeners
            List<IEventListener> l;
            if (m.containsKey(ctx)) {
                l = m.get(ctx); 
            }
            else {
                l = new ArrayList<IEventListener>();
                m.put(ctx, l);
            }
            l.add(callback);
        }		
	}
	
    public void sendEvent(T event, URI ctx) {
    	sendEvent(event, ctx, null);
    }
    
    public void sendEvent(T event, URI ctx, Object info) {
    	if (ctx == null) ctx = source;
    	
    	// Send context events
        Map<URI,List<IEventListener>> eventListeners = contextListeners.get(event);
        if (eventListeners != null) {
        	List<IEventListener> l = eventListeners.get(ctx);
        	if (l != null) {
	            for (IEventListener el : l) {
	                el.notify(ctx, event, info);
	            }        		
        	}        	
        }        
    	
        // Send global events
        List<IEventListener> l = globalListeners.get(event);
        if (l != null) {
            for (IEventListener el : l) {
                el.notify(ctx, event, info);
            }        	
        }
    }

}
