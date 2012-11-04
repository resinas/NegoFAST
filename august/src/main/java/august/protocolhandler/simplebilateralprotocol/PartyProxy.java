package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;

import august.infrastructure.registry.ServiceRegistry;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import negofast.simplebilateralprotocol.ISimpleBilateralProtocol;

public class PartyProxy implements ISimpleBilateralProtocol {

	private ISimpleBilateralProtocol party;
	
	public PartyProxy(ServiceRegistry registry, URI p) {
		if (p != null)
			party = registry.getService(p);	
	}
	
	public void accept(INegotiationMessage<IProposal<?>> msg, URI p) {
		party.accept(msg, p);
	}

	public void commit(INegotiationMessage<IProposal<?>> msg, URI p) {
		party.commit(msg, p);
	}

	public void rejectNegotiation(INegotiationMessage<IProposal<?>> msg, URI p) {
		party.rejectNegotiation(msg, p);
	}

}
