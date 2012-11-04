package negofast.core.environment.agreements;

/**
 * It defines the states in which an agreement in the repository can be, namely:
 * <ul>
 * <li>committed: The agreement was reached successfully, and it is valid
 * currently.</li>
 * <li>decommitted: The agreement was reached successfully, but it was
 * decommitted and, hence, it is not valid currently.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum AgreementState {committed, decommitted}
