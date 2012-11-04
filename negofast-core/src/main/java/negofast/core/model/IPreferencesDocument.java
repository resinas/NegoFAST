package negofast.core.model;

import java.net.URI;
import java.util.Collection;
import java.util.Date;


/**
 * Preferences express the set of data that it is used to ensure the agreement
 * is reached according the user's needs. To allow different ways of modelling
 * preferences, interface IPreferencesDocument is parameterised by the type of
 * statements used to specify features and requirements and the type of
 * statements used to specify the negotiation guidelines.
 * 
 * @author Manuel Resinas
 * 
 * @param <FR>
 * @param <G>
 */
public interface IPreferencesDocument<FR extends IStatement,G extends IStatement> {
	/**
	 * Returns a URI that identifies the model used to express preferences.
	 * @return
	 */
	public URI getFormat();
	
	/**
	 * Returns a set of statements that express the features of the party. For instance, in the case of a service provider or a seller, they express the characteristics of the service or good provided or sold.
	 * @return
	 */
	public Collection<? extends FR> getFeatures();
	/**
	 * Returns a set of statements that specify requirements on other parties. For instance, the desired characteristics of the service or good consumed or bought.
	 * @return
	 */
	public Collection<? extends FR> getRequirements();
	/**
	 * Returns a set of statements that specify the negotiation guidelines that the automated negotiation system must follow during the negotiation. For instance, the negotiation deadline, the eagerness to reach a deal or specific criteria to be applied with new customers.
	 * @return
	 */
	public Collection<? extends G> getNegotiationGuidelines();
	/**
	 * Returns an URI identifying the user who sends the preferences.
	 * @return
	 */
    public URI getUser();
    
    public Date getDeadline();
    
    public int getAgreementsNumber();
}