package negofast.core.environment.agreements;

import negofast.core.environment.events.EventType;

/**
 * Events for resource IAgreementsResource:
 * <ul>
 * <li>agreementCommitted: A new agreement has been created. The source of the
 * notification is the URI of the agreement.</li>
 * <li>agreementDecommitted: An agreement has been decommitted. The source of
 * the notification is the URI of the agreement.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum AgreementEventType implements EventType {
	agreementCommited,
	agreementDecommited
}
