package august.infrastructure.registry;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.requestresponse.IResponseGenerator;
import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.core.environment.agreements.IAgreementsResource;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.environment.systemcontext.ISystemContextData;
import negofast.core.environment.threadcontext.IThreadContextData;
import negofast.core.environment.worldmodel.IEstimatorLibrary;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.interactions.incomingnegotiation.IIncomingNegotiationReceiver;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiationReceiver;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestinformation.IInquirer;
import negofast.core.interactions.requestnegotiation.INegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.user.ICoordinator;
import negofast.core.model.IProposal;
import august.core.environment.BargainingContextResource;
import august.infrastructure.comms.DynamicProxy;

public class ServiceRegistry {

	private static Map<String, ServiceRegistry> parties = new HashMap<String, ServiceRegistry>();
		
	private Map<URI,Object> services;
	private Map<Object,URI> uris;
	private URI party;
	private long counter;
	
	private IServicesFactory factory;

	private ICoordinator systemCoordinator;
	private IIncomingProtocolNegotiationReceiver pnReceiver;
	private IIncomingNegotiationReceiver nReceiver;
	private IThreadCoordinator threadCoordinator;
    private INegotiator negotiator;
	
    private IInquirer inquirer;
    
    private IProtocolNegotiator pn;    
    private Map<URI,IConfigurableProtocolHandler> protocolHandlers;

    private ICoordinableNegotiator coordNegot;
    private IPoliciesManager polGen;

    private ICommitHandler<IProposal<?>> commitHandler;

    private IResponseGenerator<IProposal<?>> composer;
    private IBuilderManager<IProposal<?>> builderManager;
    private Map<URI,IProposalBuilder<? extends IProposal<?>>> proposalBuilders;
    
    private IAgreementsResource agreements;
    private IPreferencesResource preferences;
    private ISystemContextData system;
    private IThreadContextData thread;
    private INegotiationContextData negotiation;
    private IBargainingContextData bargaining;
    private IEstimatorLibrary estimators;

	public ServiceRegistry(URI party) {
		this(party, ServicesFactories.getDefaultFactory());
	}
	
	public ServiceRegistry(URI party, IServicesFactory factory) {
		 this.services = new HashMap<URI, Object>();
		 this.uris = new HashMap<Object, URI>();
		 
		 this.party = party;
		 this.counter = 0;
		 
		 this.factory = factory;
		 
		 ServiceRegistry.parties.put(party.getHost(), this);
		 
		 systemCoordinator = factory.createCoordinator(this);
	}
	
	
	///// External Services Management /////////////////
	public synchronized void registerExternalService(URI id, Object service) {
		URI full = party.resolve(id);
		services.put(full, service);
		uris.put(service, full);
		
		Logger.getLogger("august.infrastructure").info("Registered service: " + full.toString());		
	}
	
	public void unregisterService(Object o) {
		URI u = uris.remove(o);
		services.remove(u);
	}
	
	public void unregisterService(URI id) {
		URI full = party.resolve(id);
		Object o = services.remove(full);
		uris.remove(o);
	}
	
	public <T> T getService(URI id) {		
		URI full = party.resolve(id);
		T result;

		if (full.getHost().equals(party.getHost())) {
			result = (T) services.get(full);
		}
		else {
			ServiceRegistry externalRegistry = ServiceRegistry.parties.get(id.getHost());
			result = (T) DynamicProxy.createExternalProxy(externalRegistry.getService(id));
			
			if (result == null) throw new RuntimeException("Service "+full.toString()+ " does not exist.");
			
			Logger.getLogger("august.infrastructure").info("Get external service:" + full.toString());
		}		
		return result;
	}

	public <T> T getService(String id) {
		return getService(URI.create(id));
	}
	
	public URI getURI(Object o) {
		URI u = uris.get(o);
		if (u == null) throw new RuntimeException("Object has not been registered");
		return u;
	}

	public synchronized URI registerExternalService(Object service) {
		URI relative = URI.create("/" + service.getClass().getName() + "/" + counter);
		counter++;		
		registerExternalService(relative, service);
		return party.resolve(relative);
	}
	/////// End External Services Management ///////////////////////////
	
    
    ///////// Setters for all internal services /////////////////////////
	public void setCoordinator(ICoordinator coordinator, 
			IIncomingProtocolNegotiationReceiver pnreceiver, 
			IIncomingNegotiationReceiver nreceiver) {
		this.systemCoordinator = coordinator;
		this.pnReceiver = pnreceiver;
		this.nReceiver = nreceiver;
	}
	
    public void setEnvironment(IAgreementsResource aAgreements,
    		IPreferencesResource aPreferences,
    		ISystemContextData aSystem,
    		IThreadContextData aThread,
    		INegotiationContextData aNegotiation) {
        agreements = aAgreements;
        preferences = aPreferences;
        system = aSystem;
        thread = aThread;
        negotiation = aNegotiation;
        bargaining = new BargainingContextResource(negotiation, this);
    }
    
    public void createComponents() {
    	// The order in which the components are instantiated is relevant
        inquirer = factory.createInquirer(this);
    	proposalBuilders = factory.createProposalBuilders(this);
        builderManager = factory.createBuilderManager(this);    	
        composer = factory.createPerformativeSelector(this);
        commitHandler = factory.createCommitHandler(this);
        polGen = factory.createPoliciesGenerator(this);
        coordNegot = factory.createCoordinableNegotiator(this);
        negotiator = factory.createNegotiator(this);
    	protocolHandlers = factory.createProtocolHandlers(this);
        pn = factory.createProtocolNegotiator(this);
		threadCoordinator = factory.createThreadCoordinator(this);
    }
    ///////// End setters for all internal services /////////////////////////
    
    
    ///////// Getters for all internal services /////////////////////////////
    public IAgreementsResource getAgreementsResource() {
        return agreements;
    }
    public IPreferencesResource getPreferences() {
        return preferences;
    }
    public ISystemContextData getSystemContext() {
        return system;
    }
    public IThreadContextData getThreadContext() {
        return thread;
    }
    public INegotiationContextData getNegotiationContext() {
        return negotiation;
    }    
    public IBargainingContextData getBargainingContext() {
    	return bargaining;
    }
    public IEstimatorLibrary getEstimatorsLibrary() {
    	return estimators;
    }
    
    public ICoordinator getCoordinator() {
		return systemCoordinator;
	}
    public IIncomingProtocolNegotiationReceiver getIncomingProtocolNegotiationReceiver() {
		return pnReceiver;
	}
    public IIncomingNegotiationReceiver getIncomingNegotiationReceiver() {
		return nReceiver;
	}
	public IThreadCoordinator getThreadCoordinator() {
		return threadCoordinator;
	}    
	public IInquirer getInquirer() {
        return inquirer;
    }    
    public IProtocolNegotiator getProtocolNegotiator() {
        return pn;
    }    
    public IConfigurableProtocolHandler getProtocolHandler(URI protocol) {
    	return protocolHandlers.get(protocol);
    }
    public Set<URI> getProtocolsSupported() {
    	return protocolHandlers.keySet();
    }    
    public INegotiator getNegotiator() {
        return negotiator;
    }
    public ICoordinableNegotiator getCoordinableNegotiator() {
        return coordNegot;
    }
    public IPoliciesManager getPoliciesGenerator() {
        return polGen;
    }   
    public IResponseGenerator<IProposal<?>> getPerformativeSelector() {
        return composer;
    }
    public IBuilderManager<IProposal<?>> getBuilderManager() {
        return builderManager;
    }
    public ICommitHandler<IProposal<?>> getCommitHandler() {
        return commitHandler;
    }
    public <T extends IProposal<?>> IProposalBuilder<T> getProposalBuilder(URI id) {
    	// We assume the caller knows what he is doing
    	return (IProposalBuilder<T>) proposalBuilders.get(id);
    }
    public Set<URI> getAvailableProposalBuilders() {
    	return proposalBuilders.keySet();
    }
}
