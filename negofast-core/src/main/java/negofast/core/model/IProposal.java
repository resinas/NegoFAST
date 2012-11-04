package negofast.core.model;

import java.net.URI;
import java.util.Collection;



/**
 * A proposal is an offer for an agreement made by one party to another. The
 * main difference with an agreement is that proposals may allow some terms to
 * be left open to be refined in later interactions amongst the parties. Like
 * agreements, proposals are parameterised by the type of terms it contains and
 * its interface is exactly the same. Note that proposals can be refined later
 * to include additional information such as negotiation data referring to some
 * terms specified in the proposal.
 * 
 * @author Manuel Resinas
 * 
 * @param <Term>
 */
public interface IProposal<Term extends ITerm<?>> extends IMessageContent {

	/**
	 * Returns a URI that identifies the model used to express the agreement.
	 * @return
	 */
	public URI getFormat();

	/**
	 * Returns a collection of terms that must describe both functional
	 * descriptions and non-functional guarantees of the service. These terms
	 * conform to the main part of the agreement and regulate how the later
	 * execution of the service or the sale of the good must be carried out in
	 * the context of the agreement. The terms used in an agreement must be
	 * fully specified and ambiguities must be avoided.
	 * 
	 * @return
	 */
	public abstract Collection<Term> getTerms();
	/**
	 * Sets the terms that compose the agreement.
	 * @param val
	 */
	public void setTerms(Collection<? extends Term> val);
	
	public Collection<URI> getParties();
	public Collection<URI> getParties(URI role);
	public URI getRole(URI party);
	
	public void addParty(URI party, URI role);
}