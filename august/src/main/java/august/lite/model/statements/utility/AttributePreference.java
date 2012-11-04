package august.lite.model.statements.utility;

import java.net.URI;

import negofast.core.model.Subject;
import negofast.models.lite.intervals.Interval;
import negofast.models.lite.intervals.RealInterval;
import negofast.models.lite.statements.utility.IUtilityFunction;
import negofast.models.lite.statements.utility.IWeightedUtility;
import negofast.models.lite.statements.utility.UtilityFunctionType;
import august.core.model.AbstractStatement;



public class AttributePreference extends AbstractStatement implements IWeightedUtility {

	private URI attribute;
	private double weight;
	private Interval interval;
	private IUtilityFunction utility;

	public AttributePreference(URI att, double weight, Interval i, UtilityFunctionType f) {
		this(att, weight, i, f, Subject.agreement);
	}

	public AttributePreference(URI att, double weight, Interval i, UtilityFunctionType f, Subject s) {
		this(att, weight, i, getStandardUtility(f, i), s);		
	}

	public AttributePreference(URI att, double weight, Interval i, IUtilityFunction u, Subject s) {
		this(att, weight, i, u, s, null);
	}

	public AttributePreference(URI att, double weight, Interval i, IUtilityFunction u, Subject s, URI lang) {
		super(lang, s);
		this.attribute = att;
		this.weight = weight;
		this.interval = i;
		this.utility = u;
	}

	
	private static IUtilityFunction getStandardUtility(UtilityFunctionType f, final Interval interval) {
		IUtilityFunction result;
		
		if (f.equals(UtilityFunctionType.ascending)) {
			result = new IUtilityFunction() {
				public UtilityFunctionType getType() {
					return UtilityFunctionType.ascending;
				}
				
				public double getUtility(Object o) {
					Double d = (Double) o;
					RealInterval i = (RealInterval) interval;
					return (d - i.getMinVal()) / (i.getMaxVal() - i.getMinVal());
				}
			};
		}
		else if (f.equals(UtilityFunctionType.descending)) {
			result = new IUtilityFunction() {
				public UtilityFunctionType getType() {
					return UtilityFunctionType.descending;
				}
				
				public double getUtility(Object o) {
					Double d = (Double) o;
					RealInterval i = (RealInterval) interval;
					return 1 - ((d - i.getMinVal()) / (i.getMaxVal() - i.getMinVal()));
				}				
			};
		}
		else {
			result = new IUtilityFunction() {
				public UtilityFunctionType getType() {
					return UtilityFunctionType.discontinuous;
				}
				
				public double getUtility(Object o) {					
					return 1;
				}				
			};			
		}
		
		return result;
	}

	public URI getAttribute() {
		return attribute;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Interval getDomain() {
		return interval;
	}

	public IUtilityFunction getUtilityFunction() {
		return utility;
	}
	
	public double getWeightedUtility(Object o) {
		return weight * utility.getUtility(o);
	}

}
