package august.lite.builders.ndf;

import java.net.URI;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.model.INegotiationStatus;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import negofast.models.lite.intervals.RealInterval;
import negofast.models.lite.statements.constraint.IEquals;
import negofast.models.lite.statements.utility.IWeightedUtility;
import negofast.models.lite.statements.utility.UtilityFunctionType;
import august.core.model.NegotiationPoliciesQuery;
import august.lite.model.statements.constraints.ConstraintEquals;

public class TimeTactic implements Tactic {

	private double beta;
	private double k;
	private IPreferencesDocLite prefs;
	private IBargainingContextData ctx;
	private INegotiationStatus<IProposalEquals> status;
	private NegotiationPoliciesQuery policies;
	
	public TimeTactic() {
	}
	
	public void setUp(IPreferencesDocLite p, INegotiationStatus<IProposalEquals> status, IBargainingContextData ctx, URI thread, Properties config) {
		beta = (Double) config.get("beta");
		Double k = (Double) config.get("k");
		if (k == null)
			this.k = 0;
		else
			this.k = k;
		prefs = p;
		policies = new NegotiationPoliciesQuery(status.getNegotiationPolicies());
		this.ctx = ctx;
		this.status = status;
	}
	
    public IEquals execute(ITermEquals t) {
    	IEquals currentValue = t.getConstraint();
    	IEquals result;
    	URI j = currentValue.getVariable();
    	
    	IWeightedUtility attPref = getAttribute(j);
    	
    	if (attPref == null || attPref.getUtilityFunction().getType().equals(UtilityFunctionType.discontinuous)) {
    		result = currentValue;
    		Logger.getLogger("august.builder.ndf").fine(j + ": same value");
    	}
    	else {
        	RealInterval i = (RealInterval) attPref.getDomain();
        	double value = (Double) currentValue.getValue();
        	
        	if (attPref.getUtilityFunction().getType().equals(UtilityFunctionType.descending)) {
        		value = i.getMinVal() + alfa(j) * (i.getMaxVal() - i.getMinVal());        		
        	}
        	else {
        		value = i.getMinVal() + (1 - alfa(j)) * (i.getMaxVal() - i.getMinVal());
        	}
        	
        	Logger.getLogger("august.strategies.ndf").fine(j + ": new value: " + value);
        	result = new ConstraintEquals(j, value);
    	}
    	
    	return result;
    }
    
    private IWeightedUtility getAttribute(URI j) {
    	IWeightedUtility attPref = prefs.getRequirement(j);    	
    	if (attPref == null) attPref = prefs.getFeature(j);
    	
    	return attPref;    	
    }
    
    private double alfa(URI j) {
    	double tini = status.getInitialTime().getTime();
    	double t = Calendar.getInstance().getTimeInMillis() - tini;
    	double tmax = ((Long) policies.getPolicy(URI.create("negofast:policies.deadline"))) - tini;
    	
    	Logger.getLogger("august.builder.ndf").finer("alfa("+j+"): t("+t+"), tmax("+tmax+") t/tmax("+Math.min(t, tmax)/tmax+")");
		
    	return k + (1 - k) * Math.pow((Math.min(t, tmax) / tmax), (1 / beta));
    }
}
