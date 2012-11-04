package august.core.environment.negotiationcontext;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import negofast.core.environment.negotiationcontext.INegotiationContext;
import negofast.core.environment.negotiationcontext.NegotiationState;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.INegotiationProtocolInstance;

public class NegotiationContext implements INegotiationContext {
    private NegotiationState state;

    private INegotiationProtocolInstance negotiationProtocol;
    private List<URI> threads;

    private List<INegotiationMessage<?>> negotiationHistory;
    
    private URI id;
    private Date startDate;


    public NegotiationContext(URI id, URI thread, INegotiationProtocolInstance p) {
        this.id = id;
        this.negotiationProtocol = p;
        this.state = NegotiationState.negotiating;
        this.threads = new ArrayList<URI>(Collections.singleton(thread));
        this.negotiationHistory = new ArrayList<INegotiationMessage<?>>();
        this.startDate = new Date();
    }

    public NegotiationState getState() {
        return state;
    }

	public void setState(NegotiationState state) {
		this.state = state;
	}

    public URI getURI() {
        return id;
    }

	public void addNegotiationMessage(INegotiationMessage<?> msg) {
        this.negotiationHistory.add(msg);	
	}

	public void addThread(URI thread) {
		threads.add(thread);
	}

	public INegotiationMessage<?> getLastNegotiationMessage() {
		return negotiationHistory.get(negotiationHistory.size()-1);
	}

	public INegotiationMessage<?> getNegotiationMessage(long i) {
		return negotiationHistory.get((int)i);
	}

	public List<INegotiationMessage<?>> getNegotiationMessages() {
		return negotiationHistory;
	}

	public long getNumberNegotiationMessages() {
		return negotiationHistory.size();
	}

	public INegotiationProtocolInstance getProtocol() {
		return negotiationProtocol;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Collection<URI> getThreads() {
		return threads;
	}

}
