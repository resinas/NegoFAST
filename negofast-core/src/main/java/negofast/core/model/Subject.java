package negofast.core.model;

/**
 * Statements can be applied to the following subjects as defined by enumeration
 * {@link Subject}:
 * <ul>
 * <li>service: When the statement is applied to the good or service offered or
 * demanded. For instance, when it specifies the <em>service interface</em> or
 * the <em>service cost</em>.</li>
 * <li>party: When the statements express characteristics of one party, not
 * about the good or service. Examples of this can be:
 * <em>Party Z is located in Spain</em> or
 * <em>Party X has a low reputation on service Y</em>.</li>
 * <li>negotiationProcess: When statements are applied to the negotiation
 * process. For instance, when specifies the negotiation protocols that can be
 * followed or the negotiation deadline.</li>
 * <li>market: When statements express characteristics of a market situation.
 * For instance, the number of potential buyers or the likelihood of finding a
 * new, more appealing offer.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum Subject {
	agreement,
	party,
	negotiationProcess,
	market
}
