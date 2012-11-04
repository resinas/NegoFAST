/*
 * SimpleProtocolHandler.java
 *
 * Created on August 6, 2007, 11:08 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import august.infrastructure.registry.ServiceRegistry;

import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.model.INegotiationProtocolInstance;

/**
 *
 * @author resman
 */
public class SimpleProtocolHandler implements IConfigurableProtocolHandler {
    
	private Map<URI, BilateralHandler> bilateralHandlers;
	private ServiceRegistry registry;
	
    public SimpleProtocolHandler(ServiceRegistry registry) {
    	bilateralHandlers = new HashMap<URI, BilateralHandler>();
    	this.registry = registry;
    }

    public IBargainingProtocolHandler configure(URI thread, INegotiationProtocolInstance config, URI party) {
    	BilateralHandler handler = new BilateralHandler(registry, thread, config, party);
    	bilateralHandlers.put(thread, handler);
   	
		return handler;
    }
}
