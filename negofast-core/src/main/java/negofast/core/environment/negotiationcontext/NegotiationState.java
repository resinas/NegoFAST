package negofast.core.environment.negotiationcontext;

/**
 * The states of a negotiation context are:
 * <ul>
 * <li>negotiating: Exchanging non-binding negotiation messages amongst the
 * parties.</li>
 * <li>askedApproval: An approval to send a binding negotiation message has
 * been requested to the CommitHandler.</li>
 * <li>approved: Waiting the response of the other party after sending a
 * binding negotiation message approved by the CommitHandler.</li>
 * <li>finishedSuccessfully: Negotiation finished with an agreement.</li>
 * <li>finishedUnsuccessfully: Negotiation finished without reaching an
 * agreement.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum NegotiationState {
	negotiating, 
	askedForApproval, 
	approved, 
	finishedSuccessfully, 
	finishedUnsuccessfully
}
