package august.lite.builders.initial;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import negofast.models.lite.intervals.IntInterval;
import negofast.models.lite.intervals.Interval;
import negofast.models.lite.intervals.RealInterval;
import negofast.models.lite.statements.utility.IUtilityFunction;
import negofast.models.lite.statements.utility.IWeightedUtility;
import negofast.models.lite.statements.utility.UtilityFunctionType;
import august.infrastructure.registry.ServiceRegistry;
import august.lite.model.ProposalEquals;
import august.lite.model.TermEquals;
import august.lite.model.statements.constraints.ConstraintEquals;

public class InitialBuilder implements IProposalBuilder<IProposalEquals> {

	IPreferencesResource prefs;
	IBargainingContextData ctx;
	
	public InitialBuilder(ServiceRegistry r) {
		prefs = r.getPreferences();
		ctx = r.getBargainingContext();
	}
	
	public IProposalEquals cancelGeneration(URI context) {
		return null;
	}

	public void configure(URI context, Object builderConfig) {
		
	}
	
	public void generateProposal(URI context, INegotiationStatus<IProposalEquals> status,
			IProposalRequester<? super IProposalEquals> requester) {
		
		URI myURI = prefs.getUser();
		URI herURI = ctx.getParty(context);
		
		Logger.getLogger("august.builder.initial").info("Generating initial proposal: " + myURI + " -> " + herURI);
		
		Map<URI,URI> parties = new HashMap<URI, URI>();
		parties.put(myURI,null);
		parties.put(herURI,null);
		
		IProposalEquals p = new ProposalEquals(parties);
		Collection<ITermEquals> terms = new ArrayList<ITermEquals>();
		
		IPreferencesDocLite pref = prefs.getPreferences(URI.create("negofast:preferences.simpleutility"));
		
		for (IWeightedUtility u : pref.getRequirements()) {
			ITermEquals term = new TermEquals(new ConstraintEquals(u.getAttribute(), getBestValue(u)), herURI);	
			terms.add(term);			
		}

		for (IWeightedUtility u : pref.getFeatures()) {
			ITermEquals term = new TermEquals(new ConstraintEquals(u.getAttribute(), getBestValue(u)), myURI);
			terms.add(term);			
		}

		p.setTerms(terms);
		
		requester.proposal(context, p);
	}

	private Object getBestValue(IWeightedUtility u) {
		Interval i = u.getDomain();
		IUtilityFunction uf = u.getUtilityFunction();
		
		Object result = null;
		
		if (i instanceof IntInterval) {
			result = getBestValueInteger((IntInterval) i, uf);
		}
		else if (i instanceof RealInterval) {
			result = getBestValueReal((RealInterval) i, uf); 
		}
		
		return result;
	}
	
	private int getBestValueInteger(IntInterval i, IUtilityFunction uf) {
		int result = 0;
		if (uf.getType().equals(UtilityFunctionType.ascending)) {
			result = i.getMaxVal();
		}
		else
			result = i.getMinVal();
		
		return result;
	}

	private double getBestValueReal(RealInterval i, IUtilityFunction uf) {
		double result = 0;
		if (uf.getType().equals(UtilityFunctionType.ascending)) {
			result = i.getMaxVal();
		}
		else
			result = i.getMinVal();
		
		return result;
	}

}
