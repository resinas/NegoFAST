/*
 * Context.java
 *
 * Created on August 4, 2007, 1:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.environment;

import java.net.URI;
import java.util.Date;

import negofast.core.environment.systemcontext.ISystemContextData;

/**
 *
 * @author resman
 */
public class SystemContextResource implements ISystemContextData {
    
    private URI userURI;
    private Date creationDate;
    private URI contextURI;
    
    /** Creates a new instance of Context */
    public SystemContextResource(URI user) {
    	userURI = user;
    	creationDate = new Date();
    	contextURI = URI.create("negofast:" + user);
    }

    public Date getCreationDate() {
		return creationDate;
	}

	public URI getURI() {
		return contextURI;
	}

	public URI getUser() {
		return userURI;
	}
    
}
