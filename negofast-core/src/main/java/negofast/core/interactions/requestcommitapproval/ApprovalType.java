package negofast.core.interactions.requestcommitapproval;


/**
 * The type of approval is represented by this enumeration, which is composed
 * of:
 * <ul>
 * <li>commit: The approval is asked for sending a commit. This means that the
 * other party may or may not accept the proposal.</li>
 * <li>accept: The approval is asked for sending an accept. This means that the
 * other party has already committed to the proposal and, hence, if approved, an
 * agreement shall be reached.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum ApprovalType {commit, accept
}
