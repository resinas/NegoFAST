package august.lite.model.intervals;

import java.util.Set;

import negofast.models.lite.intervals.Interval;
import negofast.models.lite.intervals.SetInterval;


public class SetIntervalImpl<T> implements Interval, SetInterval<T> {

	private Set<T> set;
	
	public SetIntervalImpl(Set<T> s) {
		this.set = s;
	}
	
	public boolean contains(Object val) {
		return set.contains(val);
	}
	
	public Set<T> getElements() {
		return set;
	}

}
