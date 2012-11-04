package august.protocolnegotiator.simpleprotocolnegotiation;

import java.net.URI;

import negofast.simpleprotocolnegotiation.IProtocolNegotiationInitiator;

import august.infrastructure.registry.ServiceRegistry;

public class InitiatorProxy implements IProtocolNegotiationInitiator {

	private IProtocolNegotiationInitiator party;
	
	public InitiatorProxy(ServiceRegistry registry, URI endpoint) {
		party = registry.getService(endpoint);
	}
	
	public void acceptProtocol(URI protocol, URI endpoint) {
		party.acceptProtocol(protocol, endpoint);

	}

	public void rejectProtocol() {
		party.rejectProtocol();
	}

}
