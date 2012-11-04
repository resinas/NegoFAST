package negofast.core.environment.threadcontext;

import negofast.core.environment.events.EventType;

/**
 * Interface {@link IThreadContextData} offers a subscription mechanism for
 * events of type ThreadEventType, which are as follows:
 * <ul>
 * <li>threadCreated: A new thread is created. This is a not thread-specific
 * event. The source of the notification for this event is environmental
 * resource ThreadContextData.</li>
 * <li>threadFinished: A thread is finished either successfully or
 * unsuccessfully. Like threadCreated, it is not thread-specific and the source
 * of the notification is ThreadContextData.</li>
 * <li>stateChanged: The state of the thread changes. This event is
 * thread-specific and the source of the notification is the thread that
 * generates it.</li>
 * </ul>
 * 
 * @author Manuel Resinas
 * 
 */
public enum ThreadEventType implements EventType {
	threadCreated, 
	threadFinished, 
	stateChanged,
}
