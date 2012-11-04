package negofast.core.model;

import java.net.URI;



/**
 * Terms define the content of an agreement or proposal. They specify
 * constraints over some attributes with which a party must comply. Constraints
 * define the obligations, permissions and prohibitions imposed to the party.
 * The content of these constraints is very broad and domain-specific. Some
 * examples of constraints that are usually included in service agreements are:
 * <em>the service interface is specified in document
 * http://example.org/myservice.wsdl</em>, <em>the response time is less
 * than 20 ms</em>, or <em>the number of service requests is lower than 10 times
 * per minute</em>. Terms are parameterised by the type of constraint they enclose
 * (e.g. equality, constraints over one attribute, constraints over several
 * attributes).
 * 
 * @author Manuel Resinas
 * 
 * @param <T>
 */
public interface ITerm<T extends IConstraint> {

	public static final URI ALLPARTIES = URI.create("negofast:allparties");
	
	
	/**
	 * Returns the constraint specified over one or several attributes of a certain language. 
	 * @return
	 */
	public T getConstraint();

	/**
	 * Sets the constraint enclosed by the term.    
	 * @param val
	 */
	public abstract void setConstraint(T val);

	/**
	 * Returns the identifier of the party to whom the term is applied to. Each term is to be applied to one of the parties involved in the agreement or proposal and the party is obligated to fulfil what it is specified in it. Obviously, the party must be one of those that have been designated in the agreement or proposal as one of the parties that are involved in it.
	 * @return
	 */
	public abstract URI getObligatedParty();

	/**
	 * Sets the party that must comply with the constraint specified in the term.
	 * @param val
	 */
	public abstract void setObligatedParty(URI val);

}