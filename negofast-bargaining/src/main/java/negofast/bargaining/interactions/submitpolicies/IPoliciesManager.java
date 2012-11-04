/*
 * INegotiationPoliciesGenerator.java
 *
 * Created on August 8, 2007, 1:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package negofast.bargaining.interactions.submitpolicies;

import java.net.URI;
import java.util.Collection;

import negofast.bargaining.model.INegotiationPolicy;

/**
 * This interaction implements the submission of negotiation policies from the
 * PoliciesManager to the BilateralNegotiator. The PoliciesManager must
 * implement this interface, whereas the
 * BilateralNegotiator implements interface {@link IPolicyReceiver}.
 * 
 * @author resman
 */
public interface IPoliciesManager {
	/**
	 * The goal of this method is twofold. On the one hand, it allows the BilateralNegotiator to notify the PoliciesManager that there is a new bilateral negotiation that must be provided with negotiation policies. On the other hand, it returns the initial set of negotiation policies that shall guide the new bilateral negotiation.
	 * @param context
	 * @param neg
	 * @return
	 */
     public Collection<INegotiationPolicy> initNegotiation(URI context, IPolicyReceiver neg);
     
     /**
      * Notifies the PoliciesManager that the given negotiation context has finished and, hence, it is not necessary to provide new negotiation policies.
      * @param context
      */
     public void endNegotiation(URI context);
}
