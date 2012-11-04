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

public class UserConsumer implements IUser {

    private URI userURI;
    private SimpleUtilityPreferences p;
    
    public UserConsumer() {
    	userURI = URI.create("party://userconsumer");  
    	
        p = new SimpleUtilityPreferences(userURI);
        
        Collection<IWeightedUtility> requirements = new ArrayList<IWeightedUtility>();
        requirements.add(new AttributePreference(
        		URI.create("jsdl:FileSizeLimit"),
        		0.2,
        		new RealIntervalImpl(20971520, 31457280),  // Between 20 and 30 MB
        		UtilityFunctionType.ascending));

        requirements.add(new AttributePreference(
        		URI.create("jsdl:TotalCPUTime"),
        		0.4,
        		new RealIntervalImpl(1800, 3600),			// Between 1800 and 3600 CPU seconds
        		UtilityFunctionType.ascending));

        requirements.add(new AttributePreference(
        		URI.create("jsdl:TotalPhysicalMemory"),
        		0.4,
        		new RealIntervalImpl(536870912, 2147483648.0),  // Between 512 MB and 2 GB
        		UtilityFunctionType.ascending));
        p.setRequirements(requirements);

        Collection<INameValue> guidelines = new ArrayList<INameValue>();
        guidelines.add(new NameValue(URI.create("negofast:preferences.guidelines.threshold"), 0.6));
        guidelines.add(new NameValue(URI.create("negofast:preferences.guidelines.eagerness"), 0.8));        
        p.setNegotiationGuidelines(guidelines);        

        p.setDeadline(new Date(Calendar.getInstance().getTimeInMillis() + 10000));        
        p.setAgreementsNumber(1);

    }

	public void agreementCreated(IAgreement a) {
        System.out.println("Agreement created in consumer: " + a);
	}

	public IPreferencesDocLite getPreferences() {
		return p;
	}

	protected URI getUserURI() {
		return userURI;
	}

	public void end() {
		System.out.println("THE NEGOTIATION HAS FINISHED (CONSUMER)");	
	}
}
