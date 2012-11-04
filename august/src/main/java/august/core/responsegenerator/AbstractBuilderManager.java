package august.core.responsegenerator;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.IProposal;

public abstract class AbstractBuilderManager<P extends IProposal<?>> implements IBuilderManager<P> {

	private Map<URI,IProposalRequester<? super P>> requesters;
	private Map<URI,IProposalBuilder<P>> builders;
	public static Logger log = Logger.getLogger("august.builder.manager");

	public AbstractBuilderManager() {
		super();
        requesters = new HashMap<URI,IProposalRequester<? super P>>();
        builders = new HashMap<URI,IProposalBuilder<P>>();		
	}

	public void generateProposal(URI context, Collection<BargainingPerformative> allowed, INegotiationStatus<P> status,
			IProposalRequester<? super P> requester) {
			    requesters.put(context,requester);
			    
			    IProposalBuilder<P> builder = selectBuilder(context, status);
			    builder.generateProposal(context, status, this);
			    
			    builders.put(context,builder);
			}

	public abstract IProposalBuilder<P> selectBuilder(URI context, INegotiationStatus<P> status);

	public P cancelGeneration(URI context) {
	    return builders.get(context).cancelGeneration(context);
	    
	}

	public void proposal(URI context, P msg) {
	    requesters.get(context).proposal(context, msg);
	}

}