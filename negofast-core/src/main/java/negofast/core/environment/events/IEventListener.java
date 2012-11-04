package negofast.core.environment.events;

import java.net.URI;




/**
 * All environmental resources that provides a subscription mechanism must
 * implement a method to subscribe to one or several events. Then, when one of
 * these events take place, the subscribers receive the notification by means of
 * this interface.
 * 
 * @author Manuel Resinas
 * 
 */


public interface IEventListener {
	/**
	 * Notifies that a subscribed event has occurred and includes the source of the event.
	 * @param source
	 * @param event
	 * @param eventInformation
	 */
	public void notify(URI source, EventType event, Object eventInformation);
}
