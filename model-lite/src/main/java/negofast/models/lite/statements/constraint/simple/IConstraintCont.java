package negofast.models.lite.statements.constraint.simple;

import java.net.URI;

import negofast.core.model.IConstraint;



public interface IConstraintCont<T> extends IConstraint {

	public abstract URI getVariable();

	public abstract void setMinVal(T minVal);
	public abstract T getMinVal();

	public abstract void setMaxVal(T maxVal);
	public abstract T getMaxVal();

	public abstract boolean satisfies(Comparable<? super T> val);

}