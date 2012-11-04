package august.core.bargainingprotocolhandler;

import java.net.URI;

import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IProposal;
import august.infrastructure.registry.ServiceRegistry;

public abstract class AbstractBargainingProtocolHandler<PartyInterface,State extends AbstractProtocolState<?,?>> implements IBargainingProtocolHandler {

	protected URI negotiation;
	protected INegotiationProtocolInstance config;
	protected URI partyURI;
	protected URI myURI;
	protected PartyInterface party;
	private IBargainingNegotiator negotiator;
	protected State state;
	protected ServiceRegistry registry;

	public AbstractBargainingProtocolHandler(ServiceRegistry registry, URI negotiation, INegotiationProtocolInstance config, URI party) {
		super();
		this.negotiation = negotiation;
		this.config = config;
		this.registry = registry;
		
		if (party != null) {
			this.partyURI = party;		
			this.party = createPartyProxy(registry, partyURI);
		}		
		
		setUpStates();		
	}
	
	public abstract void setUpStates();

	public void setState(State newState) {
		this.state = newState;
	}
	
	public State getState() {
		return state;
	}

	public IBargainingNegotiator getNegotiator() {
		return negotiator;
	}

	public void setNegotiator(IBargainingNegotiator negotiator) {
		this.negotiator = negotiator;
	}
	
	public void unregister() {
		registry.unregisterService(this);
	}


	public URI getMyURI() {
		return myURI;
	}

	public PartyInterface getParty() {
		return party;
	}
	
	public URI getPartyURI() {
		return partyURI;
	}

	public void setPartyURI(URI party) {
		if (partyURI == null || ! partyURI.equals(party)) {
			this.partyURI = party;
			this.party = createPartyProxy(registry, party);
		}
	}
	
	public abstract PartyInterface createPartyProxy(ServiceRegistry reg, URI party);

	public URI getNegotiationContext() {
		return negotiation;
	}

	public INegotiationProtocolInstance getConfig() {
		return config;
	}

	public void init(URI negotiation, IBargainingNegotiator negotiator) {
		state.init(negotiation, negotiator);
	}

	public void accept(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.accept(negotiation, msg);
	}

	public void cfp(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.cfp(negotiation, msg);
	}

	public void commit(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.commit(negotiation, msg);
	}

	public void propose(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.propose(negotiation, msg);
	}

	public void rejectNegotiation(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.rejectNegotiation(negotiation, msg);
	}

	public void rejectProposal(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.rejectProposal(negotiation, msg);
	}

	public void withdraw(URI negotiation, INegotiationMessage<IProposal<?>> msg) {
		state.withdraw(negotiation, msg);
	}

}