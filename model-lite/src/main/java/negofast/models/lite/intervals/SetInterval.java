package negofast.models.lite.intervals;

import java.util.Set;

public interface SetInterval<T> extends Interval {

	public abstract Set<T> getElements();

}