package negofast.core.environment.systemcontext;

import java.net.URI;
import java.util.Date;


/**
 * It provides access to the information stored related to the system context. 
 * @author Manuel Resinas
 *
 */
public interface ISystemContextData {
	/**
	 * Returns the URI that identifies the current system context.
	 * @return
	 */
	public URI getURI();
	/**
	 * Obtains the URI that identifies the user who set the preferences.
	 * @return
	 */
	public URI getUser();
	/**
	 * The moment when the system context was created.
	 * @return
	 */
    public Date getCreationDate();
//    
//    public IPreferencesResource getPreferencesResource();
//    public IAgreementsResource getAgreementsResource();
//    public IThreadContextData getThreadContextData();
//    public INegotiationContextData getNegotiationContextData();
}