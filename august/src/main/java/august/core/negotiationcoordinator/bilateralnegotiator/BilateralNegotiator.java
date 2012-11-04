package august.core.negotiationcoordinator.bilateralnegotiator;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import august.infrastructure.registry.ServiceRegistry;


public class BilateralNegotiator implements ICoordinableNegotiator {
    
    private Map<URI,NegotiationManager> negotiators;
    private ServiceRegistry registry;
    
    public BilateralNegotiator(ServiceRegistry registry) {
        this.negotiators = new HashMap<URI,NegotiationManager>();
        this.registry = registry;
    }
    
    
    public void init(URI thread, IBargainingCoordinator coordinator, IBargainingProtocolHandler protocolHandler) {
    	if (negotiators.get(thread) != null) throw new RuntimeException("Repitiendo thread: " + thread);
    	NegotiationManager neg = new NegotiationManager(registry, this);
    	negotiators.put(thread, neg);
    	
    	neg.init(thread, coordinator, protocolHandler);
    }    

    public void reject(URI thread) {
    	NegotiationManager neg = negotiators.get(thread);
    	if (neg == null) throw new RuntimeException("NegotiationManager does not exist for thread: " + thread);
    	neg.reject(thread);
    }

    public void accept(URI thread) {
    	NegotiationManager neg = negotiators.get(thread);
    	if (neg == null) throw new RuntimeException("NegotiationManager does not exist for thread: " + thread);
    	neg.accept(thread);
    }

    public void cancel(URI thread) {
    	NegotiationManager neg = negotiators.get(thread);
    	if (neg == null) throw new RuntimeException("NegotiationManager does not exist for thread: " + thread);
    	neg.cancel(thread);
    }
    
    public void unregister(URI thread) {
    	negotiators.remove(thread);
    }
}