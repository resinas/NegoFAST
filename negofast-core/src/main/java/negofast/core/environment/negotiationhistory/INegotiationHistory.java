package negofast.core.environment.negotiationhistory;

import java.net.URI;
import java.util.Set;

import negofast.core.environment.agreements.IAgreementsResource;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.environment.systemcontext.ISystemContextData;
import negofast.core.environment.threadcontext.IThreadContextData;

/**
 * The NegotiationHistory is mainly intended for the estimators to develop offline estimations. However, it could be used by decision-making elements of the automated negotiation system, such as the ResponseGenerator or the CommitHandler, to support their decisions. It can be seen as a list of the environmental resources of all system contexts that have been processed by the automated negotiation system.  

 * @author Manuel Resinas
 *
 */
public interface INegotiationHistory {
	/**
	 * Archives environmental resources AgreementsResource, PreferencesResource, SystemContextData, ThreadContextData and NegotiationContextData and returns a reference to the system context in order access them later.
	 * @param agreements
	 * @param prefs
	 * @param system
	 * @param thread
	 * @param nego
	 * @return
	 */
    public URI archive(IAgreementsResource agreements, 
    		IPreferencesResource prefs, 
    		ISystemContextData system, 
    		IThreadContextData thread, 
    		INegotiationContextData nego);
    
    /**
     * Queries the URIs of all past resources that are stored in the negotiation history.
     * @return
     */
    public Set<URI> getArchiveURIs();

    
	/**
	 * Gets a reference to an implementation of interface IAgreementsResource that provides access to all data stored in that environmental resource in the given system context.
	 * @return
	 */
    public IAgreementsResource getAgreementsResource(URI ref);
	/**
	 * Gets a reference to an implementation of interface IPreferencesResource that provides access to all data stored in that environmental resource in the given system context.
	 * @return
	 */
    public IPreferencesResource getPreferencesResource(URI ref);
	/**
	 * Gets a reference to an implementation of interface ISystemContextData that provides access to all data stored in that environmental resource in the given system context.
	 * @return
	 */
    public ISystemContextData getSystemContextData(URI ref);
	/**
	 * Gets a reference to an implementation of interface IThreadContextData that provides access to all data stored in that environmental resource in the given system context.
	 * @return
	 */
    public IThreadContextData getThreadContextData(URI ref);
	/**
	 * Gets a reference to an implementation of interface INegotiationContextData that provides access to all data stored in that environmental resource in the given system context.
	 * @return
	 */
    public INegotiationContextData getNegotiationContextData(URI ref);
}
