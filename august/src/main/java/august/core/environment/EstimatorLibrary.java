/*
 * EstimatorLibrary.java
 *
 * Created on August 5, 2007, 1:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negofast.core.environment.worldmodel.IConfigureEstimator;
import negofast.core.environment.worldmodel.IEstimator;
import negofast.core.environment.worldmodel.IEstimatorLibrary;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class EstimatorLibrary implements IEstimatorLibrary {
    
    private Map<URI,List<IEstimator>> estimators;
    private ServiceRegistry registry;
    
    /** Creates a new instance of EstimatorLibrary */
    public EstimatorLibrary(ServiceRegistry reg) {
        estimators = new HashMap<URI,List<IEstimator>>();
        registry = reg;
    }
    
    public void registerEstimator(URI category, IConfigureEstimator estimator) {
        List<IEstimator> estimatorsList = estimators.get(category);
        
        if (estimatorsList == null) {
            estimatorsList = new ArrayList<IEstimator>();
            estimators.put(category,estimatorsList);
        }
    
        IEstimator est = estimator.configure(
        		registry.getSystemContext(), 
        		registry.getThreadContext(), 
        		registry.getNegotiationContext(), 
        		registry.getPreferences(), 
        		registry.getAgreementsResource());
        
        estimatorsList.add(est);
    }

    //TOIMPROVE This implementation can be improved
    public void unregisterEstimator(IEstimator estimator) {
        Collection<List<IEstimator>> allEstimators = estimators.values();
        for (List<IEstimator> estimatorsList : allEstimators) {
            if (estimatorsList.remove(estimator))
                break;
        }
    }

    public IEstimator getEstimator(URI category) {
        IEstimator result = null;
        List<IEstimator> estimatorsList = estimators.get(category);
        
        if (estimatorsList != null)
            result = estimatorsList.get(0);
        
        return result;
    }

    public Collection<IEstimator> getEstimators(URI category) {
        return estimators.get(category);
    }
    
}
