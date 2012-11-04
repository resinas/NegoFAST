package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.model.INegotiationProtocolInstance;

public class ConfigurableProtocolHandlerProxy extends AbstractProxy<IConfigurableProtocolHandler> implements
		IConfigurableProtocolHandler {

	public ConfigurableProtocolHandlerProxy(IConfigurableProtocolHandler e) {
		super(e);
	}
	
	public IProtocolHandler configure(URI thread,
			INegotiationProtocolInstance config, URI party) {
		return getInternalReference(IBargainingProtocolHandler.class, (IBargainingProtocolHandler) elem.configure(thread, config, party));
	}

}
