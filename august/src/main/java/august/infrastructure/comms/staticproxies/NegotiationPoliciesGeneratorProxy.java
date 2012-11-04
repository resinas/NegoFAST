package august.infrastructure.comms.staticproxies;

import java.net.URI;
import java.util.Collection;

import august.infrastructure.comms.AbstractProxy;

import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.bargaining.interactions.submitpolicies.IPolicyReceiver;
import negofast.bargaining.model.INegotiationPolicy;

public class NegotiationPoliciesGeneratorProxy extends AbstractProxy<IPoliciesManager> implements
		IPoliciesManager {

	public NegotiationPoliciesGeneratorProxy(IPoliciesManager e) {
		super(e);
	}
	public Collection<INegotiationPolicy> initNegotiation(URI thread,
			IPolicyReceiver neg) {

		return elem.initNegotiation(thread, getInternalReference(IPolicyReceiver.class, neg));
	}

	public void endNegotiation(final URI context) {
		send(new Runnable() {
			public void run() {
				elem.endNegotiation(context);
			}
		});
	}

}
