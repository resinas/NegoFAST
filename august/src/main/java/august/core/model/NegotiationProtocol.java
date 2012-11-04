package august.core.model;

import java.net.URI;
import java.util.Collection;


import negofast.core.model.INegotiationProtocolInstance;
import negofast.models.lite.statements.namevalue.INameValue;


public class NegotiationProtocol implements INegotiationProtocolInstance {
    
    private URI protocol;
    private Collection<INameValue> configuration;
    private boolean initiator;
    
    public NegotiationProtocol(URI protocol, boolean init, Collection<INameValue> config) {
        this.protocol = protocol;
        configuration = config;
        initiator = init;
    }

    public Collection<INameValue> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Collection<INameValue> configuration) {
        this.configuration = configuration;
    }

	public boolean isInitiator() {
		return initiator;
	}

	public URI getProtocol() {
		return protocol;
	}
}
