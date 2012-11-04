/*
 * ThreadManager.java
 *
 * Created on August 5, 2007, 5:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package august.core.coordinator;

import java.net.URI;
import java.util.logging.Logger;

import negofast.core.environment.threadcontext.IThreadContextData;
import negofast.core.environment.threadcontext.ThreadState;
import negofast.core.interactions.IProtocolHandler;
import negofast.core.interactions.requestinformation.IInformationRequester;
import negofast.core.interactions.requestinformation.IInquirer;
import negofast.core.interactions.requestnegotiation.INegotiationRequester;
import negofast.core.interactions.requestnegotiation.INegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;
import negofast.core.interactions.requestthreadprocessing.IThreadProcessingRequester;
import negofast.core.model.IAgreement;
import negofast.core.model.INegotiationProtocolInstance;
import negofast.core.model.IPartyInformation;
import august.infrastructure.registry.ServiceRegistry;

/**
 *
 * @author resman
 */
public class ThreadHandler implements IProtocolNegotiationRequester, 
        INegotiationRequester, IInformationRequester {
    
	private URI threadURI;
	private URI party;
	private IThreadProcessingRequester requester;
	private ThreadCoordinator coordinator;	
	
    private IThreadContextData threadContext;
    
    private IProtocolNegotiator protocolNegotiator;
    private IProtocolHandler protocolHandler;
    private INegotiator negotiator;
    
    private ServiceRegistry registry;
    
    /** Creates a new instance of ThreadManager */
    public ThreadHandler(ServiceRegistry registry, URI party, IThreadProcessingRequester req, ThreadCoordinator c) {
        this.party = party;
        this.registry = registry;
        this.requester = req;
        this.coordinator = c;
        
        // Get references from the registry
        threadContext = registry.getThreadContext();
        protocolNegotiator = registry.getProtocolNegotiator();
        negotiator = registry.getNegotiator();

        // Registers and initialises the thread context
        threadURI = threadContext.addThread(party);
    	setState(ThreadState.pending);    	
    }
    
    public URI getURI() {
    	return threadURI;
    }
    
    
    private void setState(ThreadState s) {
    	threadContext.setThreadState(threadURI, s);
    }

    public void startFromUser() {
        IInquirer inquirer = registry.getInquirer();        
        inquirer.queryInformation(threadURI, party, this);        
		setState(ThreadState.gettingInformation);
	}
    
    public IProtocolNegotiationRequester startFromProtocolNegotiation() {
		setState(ThreadState.prenegotiating);		
		return this;
	}
    
    public void startFromNegotiation(IProtocolHandler protocolHandler) {
        setState(ThreadState.negotiating);
        this.protocolHandler = protocolHandler;
        negotiator.negotiate(threadURI, protocolHandler, this);
	}
    	
    
    public void informationNotification(URI thread, IPartyInformation<?> info) {
        threadContext.addInformation(thread, info);
        setState(ThreadState.prenegotiating); 
        
        protocolNegotiator.negotiateProtocol(threadURI,party, this);       
    }

    public void successfulProtocolNegotiation(URI thread, INegotiationProtocolInstance protocol, IProtocolHandler handler) {
        threadContext.setNegotiationProtocol(thread, protocol);
        protocolHandler = handler;
        setState(ThreadState.negotiating);
        
        Logger.getLogger("august.thread").info(party.toString() + "--" + thread.toString() + "Successfull protocol negotiation: "+protocol.getProtocol().toString());
        
        negotiator.negotiate(threadURI, protocolHandler, this);
    }

    public void failedProtocolNegotiation(URI thread) {
        setState(ThreadState.finishedUnsuccessfully);
    }

    public void success(URI thread, IAgreement a) {
        setState(ThreadState.finishedSuccessfully);
        requester.agreementCreated(threadURI, a);
        coordinator.finished(threadURI);
    }

    public void fail(URI thread) {
        setState(ThreadState.finishedUnsuccessfully);
        coordinator.finished(threadURI);
    }


	public void terminate() {
		ThreadState currentState = threadContext.getThreadState(threadURI);
		
		if (currentState.equals(ThreadState.negotiating))
			negotiator.cancel(threadURI);		

		coordinator.finished(threadURI);
	}
}
