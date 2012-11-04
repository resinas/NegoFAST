package negofast.bargaining.model;

import java.net.URI;

/**
 * The negotiation policies are modelled through this interface.
 * @author Manuel Resinas
 *
 */
public interface INegotiationPolicy {

	/**
	 * Returns an URI that identifies the negotiation policy.
	 * @return
	 */
	public abstract URI getPolicy();

	/**
	 * Returns the value assigned to the negotiation policy.
	 * @return
	 */
	public abstract Object getValue();

}