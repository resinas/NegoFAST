package negofast.core.interactions.configurehandler;

import java.net.URI;

import negofast.core.interactions.IProtocolHandler;
import negofast.core.model.INegotiationProtocolInstance;



/**
 * This interaction configures a ProtocolHandler, which is protocol-specific, with the negotiation protocol instance and the reference to the other party. 
 * @author Manuel Resinas
 *
 */
public interface IConfigurableProtocolHandler {
	/**
	 * Initialises the ProtocolHandler with the thread context, the negotiation protocol instance and a URI that identifies the other party and returns a reference to the ProtocolHandler that shall manage the interaction with the other party.
	 * @param thread
	 * @param config
	 * @param party
	 * @return
	 */
    public IProtocolHandler configure(URI thread, INegotiationProtocolInstance config, URI party);
}
