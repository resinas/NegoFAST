package august.protocolnegotiator.simpleprotocolnegotiation;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiationReceiver;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.simpleprotocolnegotiation.IProtocolNegotiationInitiator;
import negofast.simpleprotocolnegotiation.IProtocolNegotiationResponder;
import august.core.model.NegotiationProtocol;
import august.infrastructure.registry.ServiceRegistry;

public class ProtocolNegotiatorResponder implements IProtocolNegotiationResponder, IIncomingProtocolNegotiator {

	private Map<URI,RequestInformation> pendingParties;
	private ServiceRegistry registry;
	
	private class RequestInformation {
    	private URI initiatorEndpoint;
    	private Set<URI> supported;
    	private IProtocolNegotiationInitiator initiator;
    	
    	public RequestInformation(Set<URI> supported, URI init) {
        	initiatorEndpoint = init;
        	this.supported = supported;
        	initiator = new InitiatorProxy(registry, init);
        }
    }

	public ProtocolNegotiatorResponder(ServiceRegistry registry) {
		pendingParties = new HashMap<URI,RequestInformation>();
		this.registry = registry;
	}

	public void chooseProtocol(URI party, Set<URI> supported, URI initiatorEndpoint) {
		pendingParties.put(party, new RequestInformation(supported, initiatorEndpoint));

		IIncomingProtocolNegotiationReceiver coord = registry.getIncomingProtocolNegotiationReceiver();
		coord.protocolNegotiationReceived(party, this);
	}

	public void acceptProtocolNegotiation(URI thread, URI party, IProtocolNegotiationRequester requester) {
		RequestInformation req = pendingParties.get(party);
		
		if (req != null) {
			IProtocolNegotiationInitiator initiator = req.initiator;
			URI protocol = getProtocol(req.supported);
			
	        IConfigurableProtocolHandler ph = registry.getProtocolHandler(protocol);
	        
	        // The initiator does not provide the negotiator endpoint
	        INegotiationProtocolInstance prot = new NegotiationProtocol(protocol, false, null);
	        IProtocolHandler handler = ph.configure(thread, prot, null);        
	        requester.successfulProtocolNegotiation(thread, prot, handler);

			initiator.acceptProtocol(protocol, registry.getURI(handler));
		}		
	}

	public void rejectProtocolNegotiation(URI party) {
		RequestInformation req = pendingParties.get(party);
		
		if (req != null) {
			req.initiator.rejectProtocol();
		}
	}    
	
    private URI getProtocol(Set<URI> supported) {
    	// TOIMPROVE Improve protocol selection mechanism
        return supported.iterator().next();
    }
}
