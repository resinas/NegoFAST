package negofast.core.model;

import java.net.URI;


/**
 * Negotiating parties exchange messages during the negotiation. Negotiation
 * messages are composed of a {@link Performative} that expresses the intention of the
 * sender about the message and the content of the message ({@link IMessageContent}). This content is
 * usually a proposal. However, other
 * kinds of information can be exchanged such as threats, rewards or arguments.
 * Therefore, the negotiation message is parameterised by the content of the
 * message, which may be a proposal or other information.
 * 
 * @author Manuel Resinas
 * 
 * @param <Content>
 */
public interface INegotiationMessage<Content extends IMessageContent> {

	/**
	 * Returns the content of the negotiation message.
	 * @return
	 */
	public abstract Content getContent();

	/**
	 * Sets the content of the negotiation message.
	 * @param c
	 */
	public abstract void setContent(Content c);

	/**
	 * Returns the performative of the negotiation message.
	 * @return
	 */
	public abstract Performative getPerformative();

	/**
	 * Sets the performative of the negotiation message.
	 * @param perf
	 */
	public abstract void setPerformative(Performative perf);

	/**
	 * Returns the identifier of the party who sends the negotiation message.
	 * @return
	 */
	public abstract URI getSender();

	/**
	 * Sets the identifier of the party that sends the negotiation message.
	 * @param c
	 */
	public abstract void setSender(URI sender);

}