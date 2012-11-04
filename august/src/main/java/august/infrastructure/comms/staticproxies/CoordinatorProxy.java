package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.user.ICoordinator;
import negofast.core.interactions.user.IUser;
import negofast.core.model.IPreferencesDocument;

public class CoordinatorProxy extends AbstractProxy<ICoordinator> implements ICoordinator {

	public CoordinatorProxy(ICoordinator e) {
		super(e);
	}
	
	public void terminate() {
		send(new Runnable() {
			public void run() {
				elem.terminate();
			}
		});
	}

	public boolean init(final IUser user, final IPreferencesDocument<?, ?> p) {
		return elem.init(user, p);

		//		send(new Runnable() {
//			public void run() {
//				elem.init(user, p);
//			}
//		});
	}

	public void startNegotiation(final URI party) {
		send(new Runnable() {
			public void run() {
				elem.startNegotiation(party);
			}
		});
	}

}
