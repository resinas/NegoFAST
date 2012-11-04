package august.protocolhandler.simplebilateralprotocol;

import java.net.URI;
import java.util.Arrays;

import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.model.BargainingPerformative;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IProposal;

public class InitialState extends SimpleProtocolState {
	private INegotiationMessage<IProposal<?>> msg;
	private URI p;
	private BargainingPerformative perf = null;
	
	public InitialState(BilateralHandler h) {
		super(h);
	}
	
	public void init(URI thread, IBargainingNegotiator negotiator) {
		getContext().setNegotiator(negotiator);
		
		INegotiationProtocolInstance p = getContext().getConfig();
		
		if (p.isInitiator()) {
			getContext().setState(getContext().firstSendingState);
			negotiator.startNegotiation(thread, 
					Arrays.asList(BargainingPerformative.commit, 
							BargainingPerformative.rejectNegotiation));
		}
		else {
			getContext().setState(getContext().waitingState);
			if (this.perf != null) {
				if (this.perf.equals(BargainingPerformative.accept)) {
					getContext().accept(this.msg, this.p);
				}
				else if (this.perf.equals(BargainingPerformative.commit)) {
					getContext().commit(this.msg, this.p);
				}
				if (this.perf.equals(BargainingPerformative.rejectNegotiation)) {
					getContext().rejectNegotiation(this.msg, this.p);
				}
			}
		}
	}
	
	@Override
	public void accept(INegotiationMessage<IProposal<?>> msg, URI p) {
		this.msg = msg;
		this.p = p;
		this.perf = BargainingPerformative.accept;
	}

	@Override
	public void commit(INegotiationMessage<IProposal<?>> msg, URI p) {
		this.msg = msg;
		this.p = p;
		this.perf = BargainingPerformative.commit;
	}

	@Override
	public void rejectNegotiation(INegotiationMessage<IProposal<?>> msg, URI p) {
		this.msg = msg;
		this.p = p;
		this.perf = BargainingPerformative.rejectNegotiation;
	}
	
}
