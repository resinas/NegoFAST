package negofast.simpleprotocolnegotiation;

import java.net.URI;


public interface IProtocolNegotiationInitiator {
    public void acceptProtocol(URI protocol, URI endpoint);

    public void rejectProtocol();
}
