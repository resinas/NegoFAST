package negofast.models.lite.statements.constraint;

import java.net.URI;

import negofast.core.model.IConstraint;

public interface IEquals extends IConstraint {

	public abstract URI getVariable();

	public abstract void setValue(Object value);

	public abstract Object getValue();

	public abstract boolean satisfies(Object v);

}