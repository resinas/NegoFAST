package negofast.simplebilateralprotocol;

import java.net.URI;

import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;

public interface ISimpleBilateralProtocol {
    public void commit(INegotiationMessage<IProposal<?>> msg, URI p);
    public void accept(INegotiationMessage<IProposal<?>> msg, URI p);
    public void rejectNegotiation(INegotiationMessage<IProposal<?>> msg, URI p);
}
