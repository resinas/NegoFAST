package august.core.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import negofast.core.model.IAgreement;
import negofast.core.model.IProposal;
import negofast.core.model.ITerm;


public class Agreement implements IAgreement {
    private List<ITerm<?>> terms;

    private Map<URI,URI> parties;
    
    public Agreement() {
    }
    
    public Agreement(IProposal<?> p) {
    	parties = AbstractProposal.getPartiesMap(p);
    	
    	terms = new ArrayList<ITerm<?>>();
    	terms.addAll(p.getTerms());
    }

    public List<ITerm<?>> getTerms() {
        return terms;
    }

	public URI getFormat() {
		return URI.create("negofast:terms.generic");
	}

	public void setTerms(Collection<? extends ITerm<?>> val) {
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
		String result = "A G R E E M E N T\n";
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
