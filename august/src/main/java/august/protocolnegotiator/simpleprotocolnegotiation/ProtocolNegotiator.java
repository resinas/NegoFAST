/*
 * ProtocolNegotiator.java
 *
 * Created on August 5, 2007, 6:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.protocolnegotiator.simpleprotocolnegotiation;

import java.net.URI;
import java.util.Set;

import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;
import negofast.simpleprotocolnegotiation.IProtocolNegotiationResponder;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class ProtocolNegotiator implements IProtocolNegotiator {

	private ServiceRegistry registry;
	
    /** Creates a new instance of ProtocolNegotiator */
    public ProtocolNegotiator(ServiceRegistry registry) {
    	this.registry = registry;
        
        // Registers a protocol negotiation responder to start receiving protocol negotiation offers
		IProtocolNegotiationResponder pnr = new ProtocolNegotiatorResponder(registry);
		registry.registerExternalService(URI.create("/incoming/protocolNegotiator"), pnr);        
    	
    }

    public void negotiateProtocol(URI thread, URI party, IProtocolNegotiationRequester requester) {
        IProtocolNegotiationResponder partyProxy = new ResponderProxy(registry, party);        
        ProtocolNegotiatorInitiator initiator = new ProtocolNegotiatorInitiator(requester, thread, registry);
        
        URI initURI = registry.registerExternalService(initiator);        
        IPreferencesResource p = registry.getPreferences();
        
        partyProxy.chooseProtocol(p.getUser(), getProtocolList(), initURI);        
    }
    
    
    private Set<URI> getProtocolList() {
        return registry.getProtocolsSupported();
    }
}
