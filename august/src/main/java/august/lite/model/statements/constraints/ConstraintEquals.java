package august.lite.model.statements.constraints;

import java.net.URI;

import negofast.core.model.Subject;
import negofast.models.lite.statements.constraint.IEquals;


import august.core.model.AbstractStatement;

public class ConstraintEquals extends AbstractStatement implements IEquals {

	private URI variable;
	private Object value;
	
	public ConstraintEquals(URI var, Object value) {
		this(var, value, Subject.agreement);
	}

	public ConstraintEquals(URI var, Object value, Subject s) {
		super(null, s);
		this.variable = var;
		this.setValue(value);
	}

	public URI getVariable() {
		return variable;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
	
	public boolean satisfies(Object v) {
		return value.equals(v);
	}
	
	public String toString() {
		return variable + " = " + value;
	}
	
}
