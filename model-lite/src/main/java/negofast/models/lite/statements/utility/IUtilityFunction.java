package negofast.models.lite.statements.utility;

public interface IUtilityFunction {

	public abstract UtilityFunctionType getType();

	public abstract double getUtility(Object o);

}