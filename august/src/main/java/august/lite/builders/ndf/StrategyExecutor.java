package august.lite.builders.ndf;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.model.INegotiationStatus;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import negofast.models.lite.statements.constraint.IEquals;
import negofast.models.lite.statements.utility.IWeightedUtility;
import august.core.model.AbstractProposal;
import august.lite.model.ProposalEquals;
import august.lite.model.TermEquals;
import august.lite.model.statements.constraints.ConstraintEquals;

public class StrategyExecutor {
	
	private Map<URI, Map<String, TacticConfig>> gamma;
	private URI thread;
	private IPreferencesDocLite prefs;
	private IBargainingContextData ctx;
	
	private static String packageName = StrategyExecutor.class.getPackage().getName();
	
	private class TacticConfig {
		private float weight;
		private Properties config;
		
		private TacticConfig(float w, Properties c) {
			weight = w;
			config = c;
		}
	}
	
	public StrategyExecutor(IPreferencesDocLite p, IBargainingContextData c, URI thread) {
		this.gamma = new HashMap<URI, Map<String, TacticConfig>>();
		this.prefs = p;
		this.ctx = c;
		this.thread = thread;
	}
	
	public void setTimeTactic(double beta) {
		Properties c = new Properties();
		c.put("beta", beta);
		
		for (IWeightedUtility u : prefs.getRequirements()) {			
			setTactic(u.getAttribute(), StrategyExecutor.packageName + ".TimeTactic", c, 1);
		}

		for (IWeightedUtility u : prefs.getFeatures()) {
			setTactic(u.getAttribute(), StrategyExecutor.packageName + ".TimeTactic", c, 1);
		}
	}

	public void setBehaviourTactic(int delta) {
		Properties c = new Properties();
		c.put("delta", delta);
		
		for (IWeightedUtility u : prefs.getRequirements()) {
			setTactic(u.getAttribute(), StrategyExecutor.packageName + ".BehaviourTactic", c, 1);
		}

		for (IWeightedUtility u : prefs.getFeatures()) {
			setTactic(u.getAttribute(), StrategyExecutor.packageName + ".BehaviourTactic", c, 1);
		}
	}

	public void setTactic(URI attribute, String tactic, Properties config, float weight) {		
		Map<String, TacticConfig> weights = gamma.get(attribute);
		
		if (weights == null) {
			weights = new HashMap<String, TacticConfig>();
			gamma.put(attribute, weights);
		}

		TacticConfig c = new TacticConfig(weight, config);

		weights.put(tactic, c);
	}
	
	public IProposalEquals execute(INegotiationStatus<IProposalEquals> status) {
		IProposalEquals p = status.getSent().getContent();
		ProposalEquals result = new ProposalEquals(AbstractProposal.getPartiesMap(p));

		Collection<ITermEquals> terms = new ArrayList<ITermEquals>();
		
		for (ITermEquals t : p.getTerms()) {
			URI att = t.getConstraint().getVariable();
			IEquals eq = executeTactics(att, t, status);
			
			ITermEquals newTerm = new TermEquals(eq, t.getObligatedParty());
			
			terms.add(newTerm);
		}
		
		result.setTerms(terms);
		
		return result;
	}
	
	private IEquals executeTactics(URI att, ITermEquals t, INegotiationStatus<IProposalEquals> status) {
		double result = 0;
		Map<String, TacticConfig> conf = gamma.get(att);

		if (conf == null)
			result = (Double) t.getConstraint().getValue();
		else {
			for (Entry<String, TacticConfig> tactic : conf.entrySet()) {
				TacticConfig c = tactic.getValue();
				
				IEquals e = executeTactic(tactic.getKey(), c.config, t, status);
				
				result += c.weight * ((Double) e.getValue());
			}
		}
		
		return new ConstraintEquals(att, result);
	}
	
	private IEquals executeTactic(String tactic, Properties config, ITermEquals term, INegotiationStatus<IProposalEquals> status) {
		Class<?> c;
		IEquals result = null;
		
		try {
			c = Class.forName(tactic);
			Tactic t = (Tactic) c.newInstance();
			t.setUp(prefs, status, ctx, thread, config);
			result = t.execute(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;		
	}

}
