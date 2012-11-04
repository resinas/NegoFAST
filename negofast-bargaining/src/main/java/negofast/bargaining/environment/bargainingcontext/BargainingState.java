package negofast.bargaining.environment.bargainingcontext;

/**
 * Enumeration BargainingState models the bargaining-specific states of a
 * negotiation context:
 * <ul>
 * <li>waiting: The negotiation context is waiting a negotiation message from
 * the other party.</li>
 * <li>generating: The negotiation context is generating a negotiation message
 * to be sent to the other party.</li>
 * <li>askedForApproval: The negotiation context is waiting for the approval of
 * the CommitHandler to submit a binding negotiation message.</li>
 * <li>accepted: The negotiation context has the approval to send the binding
 * negotiation message and it is waiting for the response of the other party.</li>
 * <li>finishedSuccessfully: The negotiation context has finished its
 * processing successfully (i.e. an agreement has been reached).</li>
 * <li>finishedUnsuccessfully: The negotiation context has finished its
 * processing unsuccessfully.</li>
 * </ul>
 * 
 * 
 * @author Manuel Resinas
 * 
 */
public enum BargainingState  {generating, waiting, askedForApproval, approved, finishedSuccessfully, finishedUnsuccessfully
}
