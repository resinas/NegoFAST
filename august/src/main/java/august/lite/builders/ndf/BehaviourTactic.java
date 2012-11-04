package august.lite.builders.ndf;

import java.net.URI;
import java.util.Properties;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import negofast.models.lite.intervals.RealInterval;
import negofast.models.lite.statements.constraint.IEquals;
import negofast.models.lite.statements.utility.IWeightedUtility;
import august.lite.model.statements.constraints.ConstraintEquals;

public class BehaviourTactic implements Tactic {
	private int delta;
	private IPreferencesDocLite prefs;
	private URI thread;
	private IBargainingContextData ctx;
	private INegotiationStatus<IProposalEquals> status;
	
	public BehaviourTactic() {
	}

	public void setUp(IPreferencesDocLite p, INegotiationStatus<IProposalEquals> status, IBargainingContextData ctx, URI thread, Properties config) {
		prefs = p;
		delta = (Integer) config.get("delta");
		this.thread = thread;
		this.ctx = ctx;
		this.status = status;
	}

	private IProposalEquals getBack(int num) {
		int size = ctx.countNegotiationMessages(thread);
		
		INegotiationMessage<IProposal<?>> msg = ctx.getNegotiationMessage(thread, size - num - 1);
		
		return (IProposalEquals) msg.getContent();
	}
	
	private ITermEquals getTermForAttribute(IProposalEquals p, URI att) {
		ITermEquals result = null;
		
		for (ITermEquals t : p.getTerms()) {
			if (att.equals(t.getConstraint().getVariable())) {
				result = t;
				break;
			}
		}
		
		return result;
	}
	
	private double getTermValueForAttribute(IProposalEquals p, URI att) {
		ITermEquals term = getTermForAttribute(p, att);
		
		return (Double) term.getConstraint().getValue();
	}
	
	public IEquals execute(ITermEquals t) {
    	double value = 0;
    	URI j = t.getConstraint().getVariable();
    	IWeightedUtility attPref = getAttribute(j);
    	RealInterval interval = (RealInterval) attPref.getDomain();
    	
    	IProposalEquals dosdelta = getBack(2 * delta);
    	IProposalEquals dosdeltados = getBack(2 * delta - 2);
    	IProposalEquals previous = getBack(1);
    	
    	double relativeValue = getTermValueForAttribute(dosdelta, j) / getTermValueForAttribute(dosdeltados,j) * getTermValueForAttribute(previous, j);
    	
    	value = Math.min(Math.max(relativeValue, interval.getMinVal()), interval.getMaxVal());
    	
    	return new ConstraintEquals(j, value);
	}
	
    private IWeightedUtility getAttribute(URI j) {
    	IWeightedUtility attPref = prefs.getRequirement(j);    	
    	if (attPref == null) attPref = prefs.getFeature(j);
    	
    	return attPref;    	
    }
    
	
		
}
