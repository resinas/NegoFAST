package negofast.demo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import negofast.core.interactions.user.IUser;
import negofast.core.model.IAgreement;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.statements.namevalue.INameValue;
import negofast.models.lite.statements.utility.IWeightedUtility;
import negofast.models.lite.statements.utility.UtilityFunctionType;
import august.lite.model.SimpleUtilityPreferences;
import august.lite.model.intervals.RealIntervalImpl;
import august.lite.model.statements.namevalue.NameValue;
import august.lite.model.statements.utility.AttributePreference;

public class UserProvider implements IUser {

    private URI userURI;
    private SimpleUtilityPreferences p;

    public UserProvider() {
    	userURI = URI.create("party://userprovider");  
    	
        p = new SimpleUtilityPreferences(userURI);
        
        Collection<IWeightedUtility> features = new ArrayList<IWeightedUtility>();
        features.add(new AttributePreference(
        		URI.create("jsdl:FileSizeLimit"),
        		0.2,
        		new RealIntervalImpl(1024, 1073741824),	// Between 1 KB and 1 GB
        		UtilityFunctionType.descending));

        features.add(new AttributePreference(
        		URI.create("jsdl:TotalCPUTime"),
        		0.4,
        		new RealIntervalImpl(200, 6),	// Between 200 and 6000 seconds
        		UtilityFunctionType.descending));

        features.add(new AttributePreference(
        		URI.create("jsdl:TotalPhysicalMemory"),
        		0.4,
        		new RealIntervalImpl(268435456, 2147483648.0),  // Between 256 MB and 2 GB
        		UtilityFunctionType.descending));
        p.setFeatures(features);

        Collection<INameValue> guidelines = new ArrayList<INameValue>();
        guidelines.add(new NameValue(URI.create("negofast:preferences.guidelines.threshold"), 0.6));
//        guidelines.add(new NameValue(URI.create("negofast:preferences.guidelines.deadline"), 
//        		new Date(Calendar.getInstance().getTimeInMillis() + 15000)));        
        p.setNegotiationGuidelines(guidelines);
      
        
     }

	public void agreementCreated(IAgreement a) {
        System.out.println("Agreement created in provider: " + a);
	}

	public URI getUserURI() {
		return userURI;
	}
	
	public IPreferencesDocLite getPreferences() {
		return p;
	}

	public void end() {
		System.out.println("THE NEGOTIATION HAS FINISHED (PROVIDER)");
	}

}
