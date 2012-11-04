package august.core.model;

import java.net.URI;
import java.util.Collection;

import negofast.core.model.IPartyInformation;
import negofast.core.model.IStatement;



public class PartyInformation implements IPartyInformation<IStatement> {
    private Collection<IStatement> features;

    private Collection<IStatement> requirements;

    public PartyInformation() {
    }
    
    public URI getURI() {
    	return null;
    }

    public Collection<IStatement> getFeatures() {
        return features;
    }

    public void setFeatures(Collection<IStatement> val) {
        this.features = val;
    }

    public Collection<IStatement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Collection<IStatement> val) {
        this.requirements = val;
    }
}
