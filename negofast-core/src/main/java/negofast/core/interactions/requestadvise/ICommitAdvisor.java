package negofast.core.interactions.requestadvise;

import java.net.URI;

import negofast.core.model.IProposal;


/**
 * This interaction is used to obtain a recommendation about whether it is convenient to commit to an agreement or not. 
 * @author Manuel Resinas
 *
 * @param <P>
 */
public interface ICommitAdvisor<P extends IProposal<?>> {
	/**
	 * Returns a normalised value (between 0 and 1) that is a measure of how convenient committing to that proposal is.
	 * @param thread
	 * @param p
	 * @return
	 */
    public double getAdvise(URI thread, P p);
}
