package august.lite.model.intervals;

import negofast.models.lite.intervals.IntInterval;
import negofast.models.lite.intervals.Interval;


public class IntIntervalImpl implements Interval, IntInterval {

	private int minVal;
	private int maxVal;
	
	public IntIntervalImpl(int min, int max) {
		setMinVal(min);
		setMaxVal(max);
	}

	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

	public int getMinVal() {
		return minVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	public int getMaxVal() {
		return maxVal;
	}
	
	public boolean contains(Object val) {
		Integer i = (Integer) val;
		return minVal <= i && i <= maxVal;
	}


}
