package august.infrastructure.comms.staticproxies;

import august.infrastructure.comms.AbstractProxy;
import negofast.core.interactions.user.IUser;
import negofast.core.model.IAgreement;

public class UserProxy extends AbstractProxy<IUser> implements IUser {

	public UserProxy(IUser e) {
		super(e);
	}
	public void agreementCreated(final IAgreement a) {
		send(new Runnable() {
			public void run() {
				elem.agreementCreated(a);
			}
		});
	}
	
	public void end() {
		send(new Runnable() {
			public void run() {
				elem.end();
			}
		});
	}

}
