package negofast.models.lite;

import java.net.URI;

import negofast.core.model.IPreferencesDocument;
import negofast.models.lite.statements.namevalue.INameValue;
import negofast.models.lite.statements.utility.IWeightedUtility;
 
public interface IPreferencesDocLite extends IPreferencesDocument<IWeightedUtility,INameValue> {
    public IWeightedUtility getRequirement(URI attribute);    
    public IWeightedUtility getFeature(URI attribute);    
    public INameValue getNegotiationGuideline(URI attribute);
}
