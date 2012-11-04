package august.lite.model.statements.namevalue;

import java.net.URI;

import negofast.core.model.Subject;
import negofast.models.lite.statements.namevalue.INameValue;



public class NameValue extends august.core.model.AbstractStatement implements INameValue {

	private URI attribute;
	private Object value;
	
	public NameValue(URI att, Object val) {
		super(null, Subject.agreement);
		
		attribute = att;
		value = val;
	}

	public NameValue(URI att, Object val, Subject s) {
		super(null, s);
		
		attribute = att;
		value = val;
	}

	public URI getAttribute() {
		return attribute;
	}

	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}
}
