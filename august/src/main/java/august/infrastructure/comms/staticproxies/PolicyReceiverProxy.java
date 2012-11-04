package august.infrastructure.comms.staticproxies;

import java.net.URI;
import java.util.Collection;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.submitpolicies.IPolicyReceiver;
import negofast.bargaining.model.INegotiationPolicy;

public class PolicyReceiverProxy extends AbstractProxy<IPolicyReceiver> implements IPolicyReceiver {

	public PolicyReceiverProxy(IPolicyReceiver e) {
		super(e);
	}
	public void setNegotiationPolicies(final URI thread,
			final Collection<INegotiationPolicy> policies) {
		send(new Runnable() {
			public void run() {
				elem.setNegotiationPolicies(thread, policies);
			}
		});
	}

}
