package negofast.core.interactions.user;

import java.net.URI;

import negofast.core.model.IPreferencesDocument;


/**
 * This interaction is used by the user of the automated negotiation system to
 * provide the preferences and the references to parties with whom to negotiate.
 * The User must implement interface {@link IUser}, and the
 * ANSCoordinator, must implement this interface.
 * 
 * @author Manuel Resinas
 * 
 */
public interface ICoordinator {
	/**
	 * Starts a new negotiation context with the given preferences.
	 * @param user
	 * @param preferences
	 */
    public boolean init(IUser user, IPreferencesDocument<?,?> preferences);

    /**
     * Finishes the negotiation context and, hence, all active negotiation threads.
     */
    public void terminate();

    /**
     * Requests the automated negotiation system to initiate a negotiation with the given party.
     * @param party
     */
    public void startNegotiation(URI party);
}
