/*
 * Starter.java
 *
 * Created on August 5, 2007, 2:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package negofast.demo;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import negofast.core.interactions.user.ICoordinator;
import august.infrastructure.registry.ServiceRegistry;


/**
 *
 * @author resman
 */
public class Starter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	
    	setupLoggers();
        
        // Create user instances
    	UserProvider provider = new UserProvider();
    	UserConsumer consumer = new UserConsumer();
    	UserProvider2 p2 = new UserProvider2();
    	
    	// Initialises the service registry of the negotiation systems
    	ServiceRegistry ansProvider = new ServiceRegistry(provider.getUserURI());
    	ServiceRegistry ansProvider2 = new ServiceRegistry(p2.getUserURI());
    	ServiceRegistry ansConsumer = new ServiceRegistry(consumer.getUserURI());
    	
    	// Obtains reference to the ANSCoordinator
    	ICoordinator providerCoordinator = ansProvider.getCoordinator();
    	ICoordinator provider2Coordinator = ansProvider2.getCoordinator();
    	ICoordinator consumerCoordinator = ansConsumer.getCoordinator();
    	
    	// Initiates the negotiation system
    	consumerCoordinator.init(consumer, consumer.getPreferences());
    	providerCoordinator.init(provider, provider.getPreferences());
    	provider2Coordinator.init(p2, p2.getPreferences());
    	
    	// Starts negotiations with the given party references 
    	consumerCoordinator.startNegotiation(provider.getUserURI());
    	consumerCoordinator.startNegotiation(p2.getUserURI());
    }
    
    public static void setupLoggers() {
    	// Initialising loggers
    	Handler[] handlers = Logger.getLogger( "" ).getHandlers();
          for ( int index = 0; index < handlers.length; index++ ) {
            handlers[index].setLevel( Level.FINE );
          }    	
        Logger.getLogger("august.infrastructure").setLevel(Level.OFF);
        Logger.getLogger("august.commit").setLevel(Level.OFF);
        Logger.getLogger("august.bilateral").setLevel(Level.INFO);		// Displays sent messages
        Logger.getLogger("august.builder.manager").setLevel(Level.OFF);
        Logger.getLogger("august.builder.ndf").setLevel(Level.OFF);
        Logger.getLogger("august.builder.initial").setLevel(Level.OFF);
        Logger.getLogger("august.composer").setLevel(Level.OFF);
        Logger.getLogger("august.protocolnegotiator").setLevel(Level.OFF);
        Logger.getLogger("august.thread").setLevel(Level.OFF);    	
    }
    
}
