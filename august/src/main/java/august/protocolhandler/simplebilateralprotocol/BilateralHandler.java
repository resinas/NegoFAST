package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IProposal;
import negofast.simplebilateralprotocol.ISimpleBilateralProtocol;
import august.core.bargainingprotocolhandler.AbstractBargainingProtocolHandler;
import august.infrastructure.registry.ServiceRegistry;

public class BilateralHandler extends AbstractBargainingProtocolHandler<ISimpleBilateralProtocol, SimpleProtocolState> implements ISimpleBilateralProtocol {

	protected SimpleProtocolState initialState;
	protected SimpleProtocolState firstSendingState, sendingState, waitingState;
	protected SimpleProtocolState finishedState;
	
	public BilateralHandler(ServiceRegistry registry, URI negotiation, INegotiationProtocolInstance config, URI party) {
		super(registry, negotiation, config, party);
		this.myURI = registry.registerExternalService(this);
	}
	
	public void setUpStates() {
		initialState = new InitialState(this);
		firstSendingState = new FirstSendingState(this);
		sendingState = new SendingState(this);
		waitingState = new WaitingState(this);
		finishedState = new FinishedState(this);
		
		setState(initialState);		
	}
	
	public ISimpleBilateralProtocol createPartyProxy(ServiceRegistry registry, URI party) {
		return new PartyProxy(registry, party);			
	}
	

	public void accept(INegotiationMessage<IProposal<?>> msg, URI p) {
		state.accept(msg,p);
	}

	public void commit(INegotiationMessage<IProposal<?>> msg, URI p) {
		state.commit(msg,p);
	}

	public void rejectNegotiation(INegotiationMessage<IProposal<?>> msg, URI p) {
		state.rejectNegotiation(msg,p);
	}
}
