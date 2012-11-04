package august.core.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import negofast.core.model.IProposal;
import negofast.core.model.ITerm;

public abstract class AbstractProposal<Term extends ITerm<?>> implements IProposal<Term> {

	public static Map<URI,URI> getPartiesMap(IProposal<?> p) {
		Map<URI,URI> parties = new HashMap<URI, URI>();
		for (URI party : p.getParties()) {
			parties.put(party, p.getRole(party));
		}
		return parties;
	}
	public static final URI serviceConsumerRole = URI.create("negofast:services.agreement.roles.consumer");
	public static final URI serviceProviderRole = URI.create("negofast:services.agreement.roles.provider");
	
    private Collection<Term> terms;
    private Map<URI,URI> parties;

    
    public AbstractProposal(Map<URI,URI> parties) {
    	this.terms = new ArrayList<Term>();
    	this.parties = new HashMap<URI, URI>();
    	this.parties.putAll(parties);
    }

    public Collection<Term> getTerms() {
        return terms;
    }
    
    public void setTerms(Collection<? extends Term> val) {
    	this.terms.clear();
    	this.terms.addAll(val);
    }
    
    public Collection<URI> getParties() {
    	return parties.keySet();
    }
    
    public Collection<URI> getParties(URI role) {
    	List<URI> result = new ArrayList<URI>();
    	
    	for (Entry<URI, URI> e : parties.entrySet()) {
    		if (e.getValue().equals(role))
    			result.add(e.getKey());
    	}
    	
    	return result;
    }
    
    public URI getRole(URI party) {
    	return parties.get(party);
    }
    
    public void addParty(URI party, URI role) {
    	parties.put(party, role);
    }
    

	public String toString() {
		String result = "PROPOSAL\n";
		result       += "---------------------------------------\n";
		
		for (Entry<URI,URI> e : parties.entrySet()) {
			result   += e.getValue() + "  :  " + e.getKey() + "\n";
		}
		
		for (ITerm<?> t : terms) {
			result   += t.toString() + "\n";
		}
		
		return result;
	}

}
