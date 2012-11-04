package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.requestnegotiation.INegotiationRequester;
import negofast.core.model.IAgreement;

public class NegotiationRequesterProxy extends AbstractProxy<INegotiationRequester> implements INegotiationRequester {

	public NegotiationRequesterProxy(INegotiationRequester e) {
		super(e);
	}
	
	public void fail(final URI thread) {
		send(new Runnable() {
			public void run() {
				elem.fail(thread);
			}
		});
	}

	public void success(final URI thread, final IAgreement a) {
		send(new Runnable() {
			public void run() {
				elem.success(thread, a);
			}
		});
	}

}
