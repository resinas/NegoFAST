package negofast.core.interactions.requestinformation;

import java.net.URI;

import negofast.core.model.IPartyInformation;


/**
 * This interaction is a request for the Inquirer to obtain the information
 * from other parties that is necessary to start the negotiation. It is an
 * asynchronous request. The
 * participants of this interaction are the Inquirer, which must implement
 * interface {@link IInquirer}, and the ThreadCoordinator, which must
 * implement this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IInformationRequester {
	/**
	 * It is invoked when the information has been obtained, and it includes the URI of the thread context and the information gathered. 
	 * @param thread
	 * @param info
	 */
    public void informationNotification(URI thread, IPartyInformation<?> info);
}
