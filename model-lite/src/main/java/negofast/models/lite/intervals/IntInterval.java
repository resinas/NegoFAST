package negofast.models.lite.intervals;

public interface IntInterval extends Interval {

	public abstract void setMinVal(int minVal);

	public abstract int getMinVal();

	public abstract void setMaxVal(int maxVal);

	public abstract int getMaxVal();

}