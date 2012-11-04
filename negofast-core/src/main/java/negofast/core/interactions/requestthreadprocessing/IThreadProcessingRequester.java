package negofast.core.interactions.requestthreadprocessing;

import java.net.URI;

import negofast.core.model.IAgreement;

/**
 * This interaction is a request for the ThreadCoordinator to initiate a thread
 * context to process a party reference that has been received by the
 * ANSCoordinator. There
 * are two participants in this interaction: the ThreadCoordinator, which
 * implements interface {@link IThreadCoordinator}, and the \ANSCoordinator,
 * which implements this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IThreadProcessingRequester {
	/**
	 * Notifies that an agreement was reached with the given party
	 * @param thread
	 * @param agreement
	 */
	public void agreementCreated(URI thread, IAgreement agreement);
}
