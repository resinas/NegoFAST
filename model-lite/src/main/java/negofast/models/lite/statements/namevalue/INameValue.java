package negofast.models.lite.statements.namevalue;

import java.net.URI;

import negofast.core.model.IStatement;

public interface INameValue extends IStatement {

	public abstract URI getAttribute();

	public abstract void setValue(Object value);

	public abstract Object getValue();
}
