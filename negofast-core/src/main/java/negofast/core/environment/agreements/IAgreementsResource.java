package negofast.core.environment.agreements;

import java.net.URI;
import java.util.Set;

import negofast.core.environment.events.IEventListener;
import negofast.core.model.IAgreement;


/**
 * It stores all agreements with other parties within the current system
 * context. The goal of the AgreementsResource is to allow the comparison of
 * agreements already reached with current negotiations and to allow the
 * decommitment of one of them if necessary. The agreements stored can be in two
 * states: committed and decommitted. When added, all agreements are in the
 * committed state. If a decommitment from an agreement takes place, the
 * agreement changes to the decommitted state. An agreement which is in the
 * decommitted state cannot change back to a committed state.
 * 
 * Interface IAgreementsResource provides a subscription mechanism for events of
 * type {@link AgreementEventType}. In addition, interface
 * IAgreementsResource requires enumeration {@link AgreementState},
 * which defines the states in which an agreement in the repository can be.
 * 
 * @author Manuel Resinas
 * 
 */

public interface IAgreementsResource {
	/**
	 * Adds a new agreement and returns a URI that identifies it.
	 * @param a
	 * @return
	 */
    public URI add(IAgreement a);

    /**
     * Queries the state of an agreement given its URI (committed or decommitted).
     * @param agreementId
     * @return
     */
    public AgreementState getAgreementState(URI agreementId);
    /**
     * Obtains a concrete agreement by its URI.
     * @param agreementId
     * @return
     */
    public IAgreement getAgreement(URI agreementId);

    /**
     * Marks an agreement as decommitted, hence changing its state.
     * @param agreementId
     */
    public void setDecommitted(URI agreementId);

    /**
     * Returns all committed agreements.
     * @return
     */
    public Set<IAgreement> getCommittedAgreements();
    /**
     * Returns all decommitted agreements.
     * @return
     */
    public Set<IAgreement> getDecommittedAgreements();
    /**
     * Returns all agreements.
     * @return
     */
    public Set<IAgreement> getAgreements();
    
    /**
     * Subscribes to one or several events. When the event occurs, the event listener is notified.
     * @param event
     * @param callback
     */
    void subscribeEvent(AgreementEventType[] event, IEventListener callback);
}
