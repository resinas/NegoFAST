package august.lite.model;

import java.net.URI;
import java.util.Collection;

import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.statements.namevalue.INameValue;
import negofast.models.lite.statements.utility.IWeightedUtility;
import august.core.model.AbstractPreferences;



public class SimpleUtilityPreferences extends AbstractPreferences<IWeightedUtility,INameValue> implements IPreferencesDocLite {
	
	private static URI format = URI.create("negofast:preferences.simpleutility");
	
    public SimpleUtilityPreferences(URI user) {
    	super(user);
    }

    public IWeightedUtility getRequirement(URI attribute) {
    	return getAttributePreference(attribute, getRequirements());
    }
    
    public IWeightedUtility getFeature(URI attribute) {
    	return getAttributePreference(attribute, getFeatures());
    }
    
    public INameValue getNegotiationGuideline(URI attribute) {
    	return getAttributePreference(attribute, getNegotiationGuidelines());
    }
    
    private IWeightedUtility getAttributePreference(URI attribute, Collection<? extends IWeightedUtility> collection) {
    	IWeightedUtility result = null;
    	
    	for (IWeightedUtility pref : collection) {
    		if (attribute.equals(pref.getAttribute())) {
    			result = pref;
    			break;
    		}
    	}    	
    	return result;    	
    }

    private INameValue getAttributePreference(URI attribute, Collection<? extends INameValue> collection) {
    	INameValue result = null;
    	
    	for (INameValue pref : collection) {
    		if (attribute.equals(pref.getAttribute())) {
    			result = pref;
    			break;
    		}
    	}    	
    	return result;    	
    }

	public URI getFormat() {
		return format;
	}
	
}
