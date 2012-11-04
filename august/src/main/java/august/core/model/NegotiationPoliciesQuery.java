package august.core.model;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import negofast.bargaining.model.INegotiationPolicy;

public class NegotiationPoliciesQuery {

	Map<URI, INegotiationPolicy> policies;
	
	public NegotiationPoliciesQuery(Collection<INegotiationPolicy> p) {
		policies = new HashMap<URI, INegotiationPolicy>();
		
		for (INegotiationPolicy pol : p) {
			policies.put(pol.getPolicy(), pol);
		}
	}
	
	public Object getPolicy(URI p) {
		Object result = null;
		
		INegotiationPolicy pol = policies.get(p);
		if (pol != null)
			result = pol.getValue();
		
		return result;
	}
}
