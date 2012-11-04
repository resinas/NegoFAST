/*
 * ANSCoordinator.java
 *
 * Created on August 4, 2007, 1:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.coordinator;

import java.net.URI;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import negofast.core.environment.agreements.IAgreementsResource;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.negotiationhistory.INegotiationHistory;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.environment.systemcontext.ISystemContextData;
import negofast.core.environment.threadcontext.IThreadContextData;
import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.incomingnegotiation.IIncomingNegotiationReceiver;
import negofast.core.interactions.incomingnegotiation.IIncomingProtocolHandler;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiationReceiver;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.requestthreadprocessing.IThreadProcessingRequester;
import negofast.core.interactions.user.ICoordinator;
import negofast.core.interactions.user.IUser;
import negofast.core.model.IAgreement;
import negofast.core.model.IPreferencesDocument;
import august.core.environment.AgreementRepositoryResource;
import august.core.environment.NegotiationHistoryResource;
import august.core.environment.SystemContextResource;
import august.core.environment.negotiationcontext.NegotiationContextResource;
import august.core.environment.preferences.PreferencesResource;
import august.core.environment.threadcontext.ThreadContextResource;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class ANSCoordinator implements ICoordinator, IThreadProcessingRequester, IIncomingProtocolNegotiationReceiver, IIncomingNegotiationReceiver {
    
    private IUser user;

    private ServiceRegistry registry;
    
    private IAgreementsResource agreements;
    private IPreferencesResource preferences;
    private ISystemContextData system;
    private IThreadContextData thread;
    private INegotiationContextData negotiation;
    
    private IThreadCoordinator threadCoordinator;
    
    private int agreementsNumber;
    
    private Timer deadlineTimer;
        
    /** Creates a new instance of ANSCoordinator */
    public ANSCoordinator(ServiceRegistry reg) {
    	registry = reg;
    }

    public synchronized boolean init(final IUser user, IPreferencesDocument<?,?> p) {
        this.user = user;

        // Sets up the environment
        createEnvironment(user, p);
        registry.setEnvironment(agreements, preferences, system, thread, negotiation);
        registry.setCoordinator(this, this, this);
        registry.createComponents();
        
        // Gets a reference to the thread coordinator
        threadCoordinator = registry.getThreadCoordinator();
        
        // Sets termination conditions
        agreementsNumber = p.getAgreementsNumber();
        Date deadline = p.getDeadline();
        if (deadline != null) {
        	deadlineTimer = new Timer();
        	deadlineTimer.schedule(
        			new TimerTask() { 
        				public void run() {
        					terminationHolds();
        				}
        			}, 
        			deadline);
        }
        
        return true;
    }
    
    private void createEnvironment(IUser user, IPreferencesDocument<?,?> p) {
        agreements = new AgreementRepositoryResource();
        preferences = new PreferencesResource(p);
        
        system = new SystemContextResource(p.getUser());
        thread = new ThreadContextResource(p.getUser());
        negotiation = new NegotiationContextResource(p.getUser(), registry);    	
    }

	public void terminate() {
        INegotiationHistory nh = NegotiationHistoryResource.getInstance();
        nh.archive(agreements, preferences, system, thread, negotiation);
	}

    public synchronized void startNegotiation(URI party) {
    	URI thread = threadCoordinator.coordinateThread(party, this);
    	threadCoordinator.startFromUser(thread);
    }

	public synchronized void negotiationReceived(URI party, IIncomingProtocolHandler incoming) {
    	URI thread = threadCoordinator.coordinateThread(party, this);
    	IProtocolHandler handler = incoming.acceptNegotiation(thread, party);
    	threadCoordinator.startFromNegotiation(thread, handler);
	}

	public synchronized void protocolNegotiationReceived(URI party, IIncomingProtocolNegotiator incoming) {
    	URI thread = threadCoordinator.coordinateThread(party, this);
    	IProtocolNegotiationRequester requester = threadCoordinator.startFromProtocolNegotiation(thread);
		incoming.acceptProtocolNegotiation(thread, party, requester);
	}

	public void agreementCreated(URI thread, IAgreement agreement) {
		agreements.add(agreement);
		user.agreementCreated(agreement);
		
		if (agreementsNumber > 0 && agreements.getAgreements().size() >= agreementsNumber) {
			terminationHolds();
	        
	        if (deadlineTimer != null) {
	        	deadlineTimer.cancel();
	        }
		}
	}
	
	private void terminationHolds() {
		user.end();
		terminate();
	}
        
}
