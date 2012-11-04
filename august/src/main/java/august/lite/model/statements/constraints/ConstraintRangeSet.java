package august.lite.model.statements.constraints;

import java.net.URI;
import java.util.Set;

import negofast.core.model.Subject;
import negofast.models.lite.statements.constraint.simple.IConstraintSet;


import august.core.model.AbstractStatement;

public class ConstraintRangeSet<T> extends AbstractStatement implements IConstraintSet<T> {
	private URI variable;
	private Set<T> values;
	
	public ConstraintRangeSet(URI v, Set<T> val) {
		this(v, val, Subject.agreement);
	}

	public ConstraintRangeSet(URI v, Set<T> val, Subject s) {
		super(null, s);
		variable = v;
		setValues(val);
	}

	public URI getVariable() {
		return variable;
	}

	public boolean satisfies(T val) {
		return values.contains(val);
	}

	public void setValues(Set<T> values) {
		this.values = values;
	}

	public Set<T> getValues() {
		return values;
	}
}
