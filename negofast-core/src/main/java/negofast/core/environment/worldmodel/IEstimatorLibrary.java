package negofast.core.environment.worldmodel;

import java.net.URI;
import java.util.Collection;



/**
 * The estimators library is a repository of all estimators that are available to the automated negotiation system
 * @author Manuel Resinas
 *
 */
public interface IEstimatorLibrary {
	/**
	 * Registers a new concrete estimator that implements an estimator category.
	 * @param category
	 * @param estimator
	 */
    public void registerEstimator(URI category, IConfigureEstimator estimator);

    /**
     * Unregisters a concrete estimator. 
     * @param estimator
     */
    public void unregisterEstimator(IEstimator estimator);

    /**
     * Returns the default concrete estimator that implements an estimator category.
     * @param category
     * @return
     */
    public IEstimator getEstimator(URI category);

    /**
     * Obtains all concrete estimators that implement an estimator category.
     * @param category
     * @return
     */
    public Collection<IEstimator> getEstimators(URI category);
}
