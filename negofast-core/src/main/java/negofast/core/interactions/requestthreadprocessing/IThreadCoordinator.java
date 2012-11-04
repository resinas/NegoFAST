package negofast.core.interactions.requestthreadprocessing;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;

/**
 * This interaction is a request for the ThreadCoordinator to initiate a thread
 * context to process a party reference that has been received by the
 * ANSCoordinator. There
 * are two participants in this interaction: the ThreadCoordinator, which
 * implements this interface , and the ANSCoordinator,
 * which implements interface {@link IThreadProcessingRequester}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IThreadCoordinator {
	/**
	 * Creates a thread context for the given party reference. Note that the processing does not start until any of the next three start methods is invoked.
	 * @param party
	 * @param req
	 * @return
	 */
	public URI coordinateThread(URI party, IThreadProcessingRequester req);
	/**
	 * Starts the processing of the given thread context and indicates that the party reference came from the User.
	 * @param thread
	 */
	public void startFromUser(URI thread);
	/**
	 * Starts the processing of the given thread context and indicates that the party reference came from an incoming protocol negotiation. It returns a reference to the element that shall process the result of the protocol negotiation.
	 * @param thread
	 * @return
	 */
	public IProtocolNegotiationRequester startFromProtocolNegotiation(URI thread);
	/**
	 * Starts the processing of the given thread context and indicates that the party reference came from an incoming negotiation. It returns a reference to the element that shall process the result of the negotiation.
	 * @param thread
	 * @param handler
	 */
	public void startFromNegotiation(URI thread, IProtocolHandler handler);
	/**
	 * Terminates the given thread context.
	 * @param thread
	 */
	public void terminate(URI thread);
	/**
	 * Terminates all active thread contexts.
	 */
	public void terminateAll();
}
