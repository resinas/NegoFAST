package august.core.model;

import java.net.URI;

import negofast.bargaining.model.INegotiationPolicy;

public class NegotiationPolicy implements INegotiationPolicy {
    private URI policy;
    private Object value;
    
    public NegotiationPolicy(URI aPolicy, Object aValue) {
        policy = aPolicy;
        value = aValue;
    }

    public URI getPolicy() {
        return policy;
    }

    public void setPolicy(URI policy) {
        this.policy = policy;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
