package negofast.models.lite.statements.constraint.simple;

import java.net.URI;
import java.util.Set;

import negofast.core.model.IConstraint;



public interface IConstraintSet<T> extends IConstraint {

	public abstract URI getVariable();

	public abstract boolean satisfies(T val);

	public abstract void setValues(Set<T> values);

	public abstract Set<T> getValues();

}