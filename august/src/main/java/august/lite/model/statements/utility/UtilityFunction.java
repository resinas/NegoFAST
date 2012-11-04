package august.lite.model.statements.utility;

import negofast.models.lite.statements.utility.IUtilityFunction;
import negofast.models.lite.statements.utility.UtilityFunctionType;


public abstract class UtilityFunction implements IUtilityFunction  {

	private UtilityFunctionType type;
	
	public UtilityFunction(UtilityFunctionType type) {
		this.type = type;
	}
		
	public UtilityFunctionType getType() {
		return type;
	}

	public abstract double getUtility(Object o);

}