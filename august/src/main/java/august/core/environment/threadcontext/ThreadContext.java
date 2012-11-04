package august.core.environment.threadcontext;

import java.net.URI;

import negofast.core.environment.threadcontext.IThread;
import negofast.core.environment.threadcontext.ThreadState;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IPartyInformation;

public class ThreadContext implements IThread {
    private URI id;
    private URI party;    
    private ThreadState state;

    private IPartyInformation<?> information;
    private INegotiationProtocolInstance negotiationProtocol;
    private URI negotiationContext;

    public ThreadContext(URI id, URI p) {
        this.id = id;
        this.state = ThreadState.pending;
        this.party = p;
    }

    public ThreadState getState() {
        return state;
    }

    public void setState(ThreadState val) {
        this.state = val;
    }

    public INegotiationProtocolInstance getNegotiationProtocol() {
        return negotiationProtocol;
    }

    public void setNegotiationProtocol(INegotiationProtocolInstance val) {
        this.negotiationProtocol = val;
    }

    public IPartyInformation<?> getInformation() {
        return information;
    }


    public URI getParty() {
        return party;
    }

    public void setParty(URI val) {
        this.party = val;
    }

    public URI getURI() {
        return id;
    }

	public URI getNegotiationContext() {
		return negotiationContext;
	}

	public void setInformation(IPartyInformation<?> info) {
		information = info;		
	}

	public void setNegotiationContext(URI context) {
		negotiationContext = context;		
	}
}
