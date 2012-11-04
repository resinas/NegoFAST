package august.protocolnegotiator.simpleprotocolnegotiation;

import java.net.URI;
import java.util.Set;
import java.util.logging.Logger;

import negofast.simpleprotocolnegotiation.IProtocolNegotiationResponder;

import august.infrastructure.registry.ServiceRegistry;


public class ResponderProxy implements IProtocolNegotiationResponder {

	private IProtocolNegotiationResponder party;
	
	
	public ResponderProxy(ServiceRegistry registry, URI party) {
		URI full = party.resolve("/incoming/protocolNegotiator");
		
		this.party = registry.getService(full);
		
		Logger.getLogger("august.protocolnegotiator").info("new responderproxy for " + full);
	}
	public void chooseProtocol(URI party, Set<URI> supported,
			URI initiatorEndpoint) {
		this.party.chooseProtocol(party, supported, initiatorEndpoint);

	}

}
