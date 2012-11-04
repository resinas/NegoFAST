package august.core.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IStatement;

public abstract class AbstractPreferences<FR extends IStatement, G extends IStatement> implements IPreferencesDocument<FR, G>  {

	private Collection<FR> features;
	private Collection<FR> requirements;
	private Collection<G> negotiationGuidelines;
	private URI user;
	private Date deadline;
	private int agreementsNumber;

	public AbstractPreferences(URI user) {
    	this.user = user;
    	this.requirements = new ArrayList<FR>();
    	this.features = new ArrayList<FR>();
    	this.negotiationGuidelines = new ArrayList<G>();
    	this.deadline = null;
    	this.agreementsNumber = 0;
	}

	public Collection<? extends FR> getFeatures() {
	    return features;
	}

	public void setFeatures(Collection<? extends FR> val) {
		this.features.clear();
	    this.features.addAll(val);
	}

	public Collection<? extends FR> getRequirements() {
	    return requirements;
	}

	public void setRequirements(Collection<? extends FR> val) {
		requirements.clear();
	    this.requirements.addAll(val);
	}

	public Collection<? extends G> getNegotiationGuidelines() {
	    return negotiationGuidelines;
	}

	public void setNegotiationGuidelines(Collection<? extends G> val) {
		negotiationGuidelines.clear();
	    this.negotiationGuidelines.addAll(val);
	}

	public URI getUser() {
		return user;
	}
	
	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public int getAgreementsNumber() {
		return agreementsNumber;
	}
	
	public void setAgreementsNumber(int agreementsNumber) {
		this.agreementsNumber = agreementsNumber;
	}

}