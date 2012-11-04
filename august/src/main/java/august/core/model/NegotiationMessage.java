package august.core.model;

import java.net.URI;

import negofast.core.model.IMessageContent;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;
import negofast.core.model.Performative;

public class NegotiationMessage<C extends IMessageContent> implements INegotiationMessage<C> {
	
    private C content;
    private Performative performative;
    private URI sender;

    public static <T extends IProposal<?>> NegotiationMessage<T> create() {
    	return new NegotiationMessage<T>();
    }
    
    public NegotiationMessage() {
    }
    
    public NegotiationMessage(URI sender, Performative p, C content) {
    	this.sender = sender;
    	this.performative = p;
    	this.content = content;
    }

    public C getContent() {
        return content;
    }

    public void setContent(C val) {
        this.content = val;
    }

    public Performative getPerformative() {
        return performative;
    }

    public void setPerformative(Performative val) {
        this.performative = val;
    }

    public URI getSender() {
        return sender;
    }

    public void setSender(URI val) {
        this.sender = val;
    }
    
	public String toString() {
		String result = "MESSAGE." + performative + " (Sender: " + sender +" )\n";
		result       += content;
		
		return result;
	}
    
}
