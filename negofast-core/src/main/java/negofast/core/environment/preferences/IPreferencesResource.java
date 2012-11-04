package negofast.core.environment.preferences;

import java.net.URI;

import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IProposal;


/**
 * The PreferencesResource allow all the elements in the negotiation system to
 * have access to the user preferences. It provides access to the preferences
 * supplied by the user (see {@link IPreferencesDocument}) and methods to
 * evaluate and compare several proposals. In addition, it also may provide
 * mechanisms to convert the preferences supplied by the user to a different
 * preferences model. To evaluate proposals, the PreferencesResource uses a
 * {@link IAssessmentMechanism}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IPreferencesResource {
	/**
	 * Returns the preferences as they were supplied by the user.
	 * @return
	 */
	public IPreferencesDocument<?, ?> getPreferences();	
	/**
	 * Returns the preferences converted to the specified preferences model if possible. Note that not all conversions between preferences models are possible.
	 * @param <Pref>
	 * @param format
	 * @return
	 */
	public <Pref extends IPreferencesDocument<?, ?>> Pref getPreferences(URI format);
	
	/**
	 * Obtains the URI that identifies the user who set the preferences.
	 * @return
	 */
	public URI getUser();
	
	/**
	 * Applies an assessment mechanism to evaluate the given proposal and returns a normalised value of this evaluation.
	 * @param <P>
	 * @param p
	 * @return
	 */
    public <P extends IProposal<?>> double evaluate(P p);
    /**
     * Applies an assessment mechanism to analyse whether the proposal satisfies the minimum requirements established by the user preferences. Note that this minimum is necessary but not sufficient for a proposal to be accepted.
     * @param <P>
     * @param p
     * @return
     */
    public <P extends IProposal<?>> boolean satisfies(P p);
    /**
     * Applies an assessment mechanism to compare two proposals. If p1 is more appealing than p2, it returns a value greater than 0; if p2 is more appealing than p1, it returns a value lower than 0, and if they are equally appealing, it returns 0.
     * @param <P>
     * @param p1
     * @param p2
     * @return
     */
    public <P extends IProposal<?>> int compare(P p1, P p2);    
}
