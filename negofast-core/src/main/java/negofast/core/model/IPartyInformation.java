package negofast.core.model;

import java.net.URI;
import java.util.Collection;


/**
 * This structure models the public information offered by the parties about
 * their preferences. This information is obtained by means of role
 * inquirer before the negotiation process starts. Like preferences,
 * the party information is composed of two different sets of statements:
 * requirements and features and, also like preferences, it is parameterised by
 * the type of statement used to express them.
 * 
 * @author Manuel Resinas
 * 
 * @param <St>
 */
public interface IPartyInformation<St extends IStatement> {

	/**
	 * Returns an identifier of the party this information is about.
	 * @return
	 */
	public abstract URI getURI();
	
	/**
	 * Returns the statements that express the features of the party.
	 * @return
	 */
	public abstract Collection<St> getFeatures();

	/**
	 * Returns the statements expressing the requirements of the party.
	 * @return
	 */
	public abstract Collection<St> getRequirements();

}