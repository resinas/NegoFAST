package negofast.core.interactions.incomingprotocolnegotiation;

import java.net.URI;


/**
 * By means of this interaction, the ProtocolNegotiator notifies the automated negotiation system that another party has sent a request to start a protocol negotiation and asks for permission to proceed with it. 
 * @author Manuel Resinas
 *
 */
public interface IIncomingProtocolNegotiationReceiver {
	/**
	 * Notifies that a protocol negotiation request from the given party has been received and asks for permission to proceed with it. It also includes a reference to the ProtocolNegotiator that shall process the request.
	 * @param party
	 * @param incoming
	 */
    public void protocolNegotiationReceived(URI party, IIncomingProtocolNegotiator incoming);

}
