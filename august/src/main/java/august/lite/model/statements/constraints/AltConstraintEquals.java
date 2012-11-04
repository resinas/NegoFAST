package august.lite.model.statements.constraints;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import negofast.core.model.IConstraint;
import negofast.core.model.Subject;
import august.core.model.AbstractStatement;

public class AltConstraintEquals<T> extends AbstractStatement implements IConstraint {

	private URI variable;
	private Set<T> values;
	

	public AltConstraintEquals(URI var, T value) {
		this(var, value, Subject.agreement);
	}

	public AltConstraintEquals(URI var, Collection<T> value) {
		this(var, value, Subject.agreement);
	}

	private AltConstraintEquals(URI var, Subject s) {
		super(null, s);
		this.variable = var;
		values = new HashSet<T>();
		
	}

	public AltConstraintEquals(URI var, T value, Subject s) {
		this(var,s);
		values.add(value);
	}

	public AltConstraintEquals(URI var, Collection<T> value, Subject s) {
		this(var,s);
		values.addAll(value);
	}

	public URI getVariable() {
		return variable;
	}

	public void setValues(Collection<T> value) {
		values.clear();
		values.addAll(value);
	}

	public Collection<T> getValues() {
		return values;
	}
	
	public boolean satisfies(T v) {
		return values.contains(v);
	}
	
	public String toString() {
		return variable + " = " + values;
	}
}
