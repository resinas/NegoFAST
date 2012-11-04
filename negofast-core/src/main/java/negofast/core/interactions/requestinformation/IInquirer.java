package negofast.core.interactions.requestinformation;

import java.net.URI;


/**
 * This interaction is a request for the Inquirer to obtain the information
 * from other parties that is necessary to start the negotiation. It is an
 * asynchronous request. The
 * participants of this interaction are the Inquirer, which must implement
 * this interface, and the ThreadCoordinator, which must
 * implement interface {@link IInformationRequester}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IInquirer {
	/**
	 * Requests the Inquirer to get information from a party and includes the URI of the thread context and an identifier to the other party, so that the Inquirer can interact with it. In addition, it includes a reference to the requester.
	 * @param thread
	 * @param party
	 * @param requester
	 */
    public void queryInformation(URI thread, URI party, IInformationRequester requester);
}
