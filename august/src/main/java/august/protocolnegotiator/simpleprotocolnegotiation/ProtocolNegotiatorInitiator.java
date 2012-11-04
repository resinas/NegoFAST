/*
 * ProtocolNegotiatorInit.java
 *
 * Created on August 5, 2007, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.protocolnegotiator.simpleprotocolnegotiation;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.simpleprotocolnegotiation.IProtocolNegotiationInitiator;
import august.core.model.NegotiationProtocol;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class ProtocolNegotiatorInitiator implements IProtocolNegotiationInitiator{
    
    IProtocolNegotiationRequester req;
    URI thread;
    ServiceRegistry registry;
    
    /** Creates a new instance of ProtocolNegotiatorInitiator */
    public ProtocolNegotiatorInitiator(IProtocolNegotiationRequester req, URI thread, ServiceRegistry registry) {
        this.req = req;
        this.thread = thread;
        this.registry = registry;
    }

    public void acceptProtocol(URI protocol, URI endpoint) {
        IConfigurableProtocolHandler ph = registry.getProtocolHandler(protocol);
        
        INegotiationProtocolInstance prot = new NegotiationProtocol(protocol, true, null);
        IProtocolHandler handler = ph.configure(thread, prot, endpoint);        
        req.successfulProtocolNegotiation(thread, prot, handler);
        
        registry.unregisterService(this);
    }

    public void rejectProtocol() {
        req.failedProtocolNegotiation(thread);
        
        registry.unregisterService(this);
    }
    
}
