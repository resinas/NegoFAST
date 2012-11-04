package negofast.bargaining.interactions.submitpolicies;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.INegotiationPolicy;


/**
 * This interaction implements the submission of negotiation policies from the
 * PoliciesManager to the BilateralNegotiator. The PoliciesManager must
 * implement {@link IPoliciesManager} interface, whereas the
 * BilateralNegotiator implements this interface.
 * 
 * @author resman
 */

public interface IPolicyReceiver {
	/**
	 * Sets the negotiation policies of the given negotiation context.
	 * @param context
	 * @param policies
	 */
    public void setNegotiationPolicies(URI context, Collection<INegotiationPolicy> policies);
}
