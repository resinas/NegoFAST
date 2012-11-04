package negofast.models.lite.statements.utility;

import java.net.URI;

import negofast.core.model.IStatement;
import negofast.models.lite.intervals.Interval;

public interface IWeightedUtility extends IStatement {

	public abstract URI getAttribute();

	public abstract double getWeight();

	public abstract Interval getDomain();

	public abstract IUtilityFunction getUtilityFunction();

	public abstract double getWeightedUtility(Object o);

}