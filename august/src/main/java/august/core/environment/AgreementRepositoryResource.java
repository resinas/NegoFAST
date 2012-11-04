/*
 * AgreementsRepositoryResource.java
 *
 * Created on August 4, 2007, 5:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import negofast.core.environment.agreements.AgreementEventType;
import negofast.core.environment.agreements.AgreementState;
import negofast.core.environment.agreements.IAgreementsResource;
import negofast.core.environment.events.IEventListener;
import negofast.core.model.IAgreement;
import august.core.environment.events.EventManager;

/**
 *
 * @author resman
 */
public class AgreementRepositoryResource implements IAgreementsResource {
    
    private Map<URI,IAgreement> committedAgreements;
    private Map<URI,IAgreement> decommittedAgreements;
    private long counter;
    private EventManager<AgreementEventType> eventManager;
    
    /** Creates a new instance of AgreementsRepositoryResource */
    public AgreementRepositoryResource() {
        committedAgreements = new HashMap<URI,IAgreement>();
        decommittedAgreements = new HashMap<URI,IAgreement>();
        counter = 0;
        eventManager = new EventManager<AgreementEventType>(URI.create("negofast:agreementrepository"));
    }

    public URI add(IAgreement a) {
        URI aURI = URI.create("negofast:agreement:"+counter);
        counter++;
        committedAgreements.put(aURI, a);
        eventManager.sendEvent(AgreementEventType.agreementCommited, aURI);
        
        return aURI;
    }

    public AgreementState getAgreementState(URI agreement) {
        AgreementState result = null;
        
        if (committedAgreements.containsKey(agreement))
            result = AgreementState.committed;
        else if (decommittedAgreements.containsKey(agreement))
            result = AgreementState.decommitted;
        
        return result;
    }

    public IAgreement getAgreement(URI agreement) {
        IAgreement a = committedAgreements.get(agreement);
        if (a == null)
            a = decommittedAgreements.get(agreement);
        
        return a;
    }

    public void setDecommitted(URI agreement) {
        IAgreement a = committedAgreements.remove(agreement);
        decommittedAgreements.put(agreement,a);
        eventManager.sendEvent(AgreementEventType.agreementDecommited, agreement);
    }

    public Set<IAgreement> getCommittedAgreements() {
        return (Set<IAgreement>) committedAgreements.values();
    }

    public Set<IAgreement> getDecommittedAgreements() {
        return (Set<IAgreement>) decommittedAgreements.values();
    }

    public Set<IAgreement> getAgreements() {
        Set<IAgreement> agreements = new HashSet<IAgreement>(committedAgreements.values());
        agreements.addAll(decommittedAgreements.values());
        
        return agreements;
    }

	public void subscribeEvent(AgreementEventType[] event,
			IEventListener callback) {
		eventManager.subscribeEvent(event, callback);
	}
    
}
