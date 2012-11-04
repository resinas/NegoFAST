package august.protocolhandler.simplebilateralprotocol;


public class FinishedState extends SimpleProtocolState {

	public FinishedState(BilateralHandler context) {
		super(context);		
		context.unregister();
	}

}
