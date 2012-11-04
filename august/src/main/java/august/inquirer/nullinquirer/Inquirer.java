/*
 * Inquirer.java
 *
 * Created on August 5, 2007, 6:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.inquirer.nullinquirer;

import java.net.URI;

import negofast.core.interactions.requestinformation.IInformationRequester;
import negofast.core.interactions.requestinformation.IInquirer;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class Inquirer implements IInquirer {
    
    /** Creates a new instance of Inquirer */
    public Inquirer(ServiceRegistry r) {
    }

    /** This method does nothing */
    public void queryInformation(URI thread, URI party, IInformationRequester requester) {
        requester.informationNotification(thread, null);
    }
    
}
