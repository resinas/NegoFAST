package negofast.core.interactions.user;

import negofast.core.model.IAgreement;


/**
 * This interaction is used by the user of the automated negotiation system to
 * provide the preferences and the references to parties with whom to negotiate.
 * The User must implement this interface, and the
 * ANSCoordinator, must implement interface {@link ICoordinator}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IUser {
	/**
	 * Notifies that the specified agreement has been reached.
	 * @param a
	 */
    public void agreementCreated(IAgreement a);
    /**
     * Notifies that the automated negotiation system finished working.
     */
    public void end();
}
