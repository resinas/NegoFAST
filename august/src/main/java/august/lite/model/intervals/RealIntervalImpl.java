package august.lite.model.intervals;

import negofast.models.lite.intervals.Interval;
import negofast.models.lite.intervals.RealInterval;


public class RealIntervalImpl implements Interval, RealInterval{

	private double minVal;
	private double maxVal;
	
	public RealIntervalImpl(double min, double max) {
		setMinVal(min);
		setMaxVal(max);
	}

	public void setMinVal(double minVal) {
		this.minVal = minVal;
	}

	public double getMinVal() {
		return minVal;
	}

	public void setMaxVal(double maxVal) {
		this.maxVal = maxVal;
	}

	public double getMaxVal() {
		return maxVal;
	}
	
	public boolean contains(Object val) {
		Double d = (Double) val;
		return minVal <= d && d <= maxVal;
	}

}
