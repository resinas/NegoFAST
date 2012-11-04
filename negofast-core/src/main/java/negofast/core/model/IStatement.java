package negofast.core.model;

import java.net.URI;
import java.util.Collection;

/**
 * A statement is a preference about one or several attributes that are defined
 * in one or several ontologies or vocabularies. Some examples of statements are
 * an utility function defined over an attribute, a constraint specified on
 * several attributes, a pair attribute-value or a rule relating several
 * attributes. Statements can be applied to the following subjects as defined by
 * enumaration {@link Subject}.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IStatement {

	/**
	 * Returns identifiers to the ontologies or vocabularies that are used by attributes used in the statement.
	 * @return
	 */
	public abstract Collection<URI> getLanguage();
	/**
	 * Returns the subject to which the statement is applied.
	 * @return
	 */
	public abstract Subject getSubject();

}