package negofast.core.environment.threadcontext;

/**
 * The states of a thread context are modelled through this enumeration:
 * <ul>
 * <li>pending: Waiting to be processed.</li>
 * <li>gettingInformation: Gathering information from the other party.</li>
 * <li>prenegotiating: Selecting a negotiation protocol.</li>
 * <li>negotiating: Performing the negotiation.</li>
 * <li>finishedSuccessfully: Negotiation finished with a new agreement.</li>
 * <li>finishedUnsuccessfully: Negotiation finished without reaching an
 * agreement.</li>
 * <li>decommitted: Negotiation that finished successfully but the agreement
 * was decommitted later.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum ThreadState {
	pending, 
	gettingInformation, 
	prenegotiating, 
	negotiating, 
	finishedSuccessfully, 
	finishedUnsuccessfully, 
	decommitted
}
