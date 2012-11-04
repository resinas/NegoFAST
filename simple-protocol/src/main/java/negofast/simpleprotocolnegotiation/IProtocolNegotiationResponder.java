package negofast.simpleprotocolnegotiation;

import java.util.Set;
import java.net.URI;

public interface IProtocolNegotiationResponder {
    public void chooseProtocol(URI partyId, Set<URI> supported, URI initiatorEndpoint);
}
