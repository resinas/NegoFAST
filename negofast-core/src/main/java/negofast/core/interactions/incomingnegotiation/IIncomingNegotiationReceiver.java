package negofast.core.interactions.incomingnegotiation;

import java.net.URI;

/**
 * By means of this interaction, the ProtocolHandler notifies the automated negotiation system that another party has sent a request to start a protocol negotiation and asks for permission to proceed with it.
 * @author Manuel Resinas
 *
 */
public interface IIncomingNegotiationReceiver {
	/**
	 * Notifies that a negotiation request from the given party has been received and asks for permission to proceed with it. It also includes a reference to the element that shall process the request and a reference to the element that shall handle the protocol if the incoming request is accepted.
	 * @param party
	 * @param incoming
	 */
    public void negotiationReceived(URI party, IIncomingProtocolHandler incoming);

}
