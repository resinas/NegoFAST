package august.infrastructure.comms.staticproxies;

import java.net.URI;

import august.infrastructure.comms.AbstractProxy;

import negofast.core.interactions.requestinformation.IInformationRequester;
import negofast.core.interactions.requestinformation.IInquirer;

public class InquirerProxy extends AbstractProxy<IInquirer> implements IInquirer {

	public InquirerProxy(IInquirer elem) {
		super(elem);
	}
	
	
	public void queryInformation(final URI thread, final URI party,
			final IInformationRequester requester) {
		
		send(new Runnable() {
			public void run() {
				elem.queryInformation(thread, party, requester);
			}
		});
//		
//        CommManager.send(new Message<IInquirer>(i, requester) {
//        	protected void msg() {
//                dest.queryInformation(thread, party, (IInformationRequester)sender);
//        	}
//        });
	}

}
