package august.lite.model.statements.constraints;

import java.net.URI;

import negofast.core.model.Subject;
import negofast.models.lite.statements.constraint.simple.IConstraintCont;


import august.core.model.AbstractStatement;

public class ConstraintRangeCont<T> extends AbstractStatement implements IConstraintCont<T> {
	private URI variable;
	private T minVal;
	private T maxVal;
	
	public ConstraintRangeCont(URI v, T min, T max) {
		this(v, min, max, Subject.agreement);
	}

	public ConstraintRangeCont(URI v, T min, T max, Subject s) {
		super(null, s);
		variable = v;
		setMinVal(min);
		setMaxVal(max);
	}

	public URI getVariable() {
		return variable;
	}

	public void setMinVal(T minVal) {
		this.minVal = minVal;
	}

	public T getMinVal() {
		return minVal;
	}

	public void setMaxVal(T maxVal) {
		this.maxVal = maxVal;
	}

	public T getMaxVal() {
		return maxVal;
	}
	
	public boolean satisfies(Comparable<? super T> val) {
		return val.compareTo(minVal) > 0 && val.compareTo(maxVal) < 0;
	}
}
