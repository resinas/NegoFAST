package negofast.demo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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

public class UserProvider2 implements IUser {

    private URI userURI;
    private SimpleUtilityPreferences p;

    public UserProvider2() {
    	userURI = URI.create("party://userprovider2");  
    	
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
        		new RealIntervalImpl(50, 3000),		// Between 50 and 3000 seconds
        		UtilityFunctionType.descending));

        features.add(new AttributePreference(
        		URI.create("jsdl:TotalPhysicalMemory"),
        		0.4,
        		new RealIntervalImpl(268435456, 17179869184.0),  // Between 256 MB and 16 GB
        		UtilityFunctionType.descending));
        p.setFeatures(features);

        Collection<INameValue> guidelines = new ArrayList<INameValue>();
        guidelines.add(new NameValue(URI.create("negofast:preferences.guidelines.threshold"), 0.5));
        guidelines.add(new NameValue(URI.create("negofast:preferences.guidelines.eagerness"), 0.5));
        p.setNegotiationGuidelines(guidelines);
      
        p.setDeadline(new Date(Calendar.getInstance().getTimeInMillis() + 45000));        
        
     }

	public void agreementCreated(IAgreement a) {
        System.out.println("Agreement created in provider 2: " + a);
	}

	public URI getUserURI() {
		return userURI;
	}
	
	public IPreferencesDocLite getPreferences() {
		return p;
	}

	public void end() {
		System.out.println("THE NEGOTIATION HAS FINISHED (PROVIDER2)");
	}

}
