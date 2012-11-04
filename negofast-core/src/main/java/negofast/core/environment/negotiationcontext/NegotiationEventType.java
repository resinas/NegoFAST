package negofast.core.environment.negotiationcontext;

import negofast.core.environment.events.EventType;

public enum NegotiationEventType implements EventType {
	negotiationCreated,
	negotiationFinished,
	stateChanged,
	messageReceived,
	messageSent
}
