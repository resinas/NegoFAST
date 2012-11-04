package negofast.core.environment.worldmodel;

import java.net.URI;


/**
 * An estimator handles information about the parties, the market or a concrete domain.
 * 
 * The estimator interface includes methods to obtain the estimation for a given
 * system context and, if necessary, a thread context or a negotiation context.
 * However, depending on the estimator, it may require additional information
 * that must be sent to get the estimation. For instance, two proposals must be
 * sent to an estimator to measure their similarity.
 * 
 * @author Manuel Resinas
 * 
 */
public interface IEstimator {
	/**
	 * Obtains an estimation for a particular context and thread. The estimation may require additional information (see above) that can be of any type.
	 * @param context
	 * @param domainSpecificInfo
	 * @return
	 */
    public Object estimate(URI context, Object domainSpecificInfo);
    /**
     * Returns the error associated with an estimation.
     * @param context
     * @param domainSpecificInfo
     * @return
     */
    public IEstimation estimateWithError(URI context, Object domainSpecificInfo);
}
