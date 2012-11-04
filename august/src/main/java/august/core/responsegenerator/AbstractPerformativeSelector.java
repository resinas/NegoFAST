package august.core.responsegenerator;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.bargaining.model.BargainingPerformative;
import negofast.bargaining.model.INegotiationStatus;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import august.core.model.NegotiationMessage;
import august.infrastructure.registry.ServiceRegistry;

public abstract class AbstractPerformativeSelector<P extends IProposal<?>> implements IPerformativeSelector<P> {

	protected static Logger log = Logger.getLogger("august.composer");
	protected IBuilderManager<P> builderManager;
	protected Map<URI,Request> requests;

	private class Request {
		private NegotiationMessage<P> msg;
		private IResponseRequester<P> requester;
		private Collection<BargainingPerformative> allowed;
		private INegotiationStatus<P> last;
		
		private Request(NegotiationMessage<P> msg,
				IResponseRequester<P> requester,
				Collection<BargainingPerformative> allowed,
				INegotiationStatus<P> last) {
			this.msg = msg;
			this.requester = requester;
			this.allowed = allowed;
			this.last = last;
		}
	}
	
	public AbstractPerformativeSelector(ServiceRegistry registry) {
		super();
		builderManager = (IBuilderManager<P>) registry.getBuilderManager();
		requests = new HashMap<URI, Request>();
	}

	public INegotiationMessage<P> cancelGeneration(URI context) {
	    INegotiationMessage<P> msg = requests.get(context).msg;
	    requests.remove(context);
	    
	    return msg;
	}
	
	public abstract BargainingPerformative obtainPerformative(URI context, Collection<BargainingPerformative> allowed, 
            INegotiationStatus<P> st);

	public abstract BargainingPerformative obtainPerformative(URI context, Collection<BargainingPerformative> allowed, 
            INegotiationStatus<P> st,P generatedProposal);

	public void generateResponse(URI context, Collection<BargainingPerformative> allowed, INegotiationStatus<P> last,
			IResponseRequester<P> requester) {
				
	    BargainingPerformative p = obtainPerformative(context, allowed, last);
	    if (p == null) {
	    	requests.put(context, new Request(null,requester,allowed,last));		        
	        builderManager.generateProposal(context, allowed, last, this);			    	
	    }
	    else {		    
		    NegotiationMessage<P> msg = NegotiationMessage.create();
			
		    msg.setPerformative(p);
		    
		    if (p.equals(BargainingPerformative.propose) || p.equals(BargainingPerformative.commit)) {
		        Collection<BargainingPerformative> perf = Collections.singleton(p);
		        requests.put(context, new Request(msg, requester, allowed, last));
		        
		        builderManager.generateProposal(context, perf, last, this);
		    }
		    else if (p.equals(BargainingPerformative.accept)){
		    	msg.setContent(last.getReceived().getContent());
		        requester.negotiationMessage(context, msg);
		    }
		    else {
		        requester.negotiationMessage(context, msg);
		    }
	    }
	}

	public void proposal(URI context, P proposal) {
		Request req = requests.get(context);
		if (req == null)
			throw new RuntimeException("Esnulo!!");
		NegotiationMessage<P> message = req.msg;
	    
	    if (message == null) {
	    	message = NegotiationMessage.create();
	    	BargainingPerformative p = obtainPerformative(context,req.allowed,req.last,proposal);
	    	message.setPerformative(p);
	    }
	    
    	message.setContent(proposal);
	    
	    req.requester.negotiationMessage(context, message);
	    requests.remove(context);
	}

}