package negofast.models.lite.intervals;

public interface RealInterval extends Interval {

	public abstract void setMinVal(double minVal);

	public abstract double getMinVal();

	public abstract void setMaxVal(double maxVal);

	public abstract double getMaxVal();

}