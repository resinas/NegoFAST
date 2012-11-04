package august.infrastructure.registry.servicesfactories;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestinformation.IInquirer;
import negofast.core.interactions.requestnegotiation.INegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.user.ICoordinator;
import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IProposal;
import august.commithandler.simple.SimpleCommitHandler;
import august.core.coordinator.ANSCoordinator;
import august.core.coordinator.ThreadCoordinator;
import august.core.negotiationcoordinator.BargainingCoordinator;
import august.core.negotiationcoordinator.bilateralnegotiator.BilateralNegotiator;
import august.core.responsegenerator.IPerformativeSelector;
import august.infrastructure.registry.IServicesFactory;
import august.infrastructure.registry.ServiceRegistry;
import august.inquirer.nullinquirer.Inquirer;
import august.lite.builders.initial.InitialBuilder;
import august.lite.builders.ndf.NegotiationDecisionFunctionsBuilder;
import august.lite.commithandler.decisionpoints.DecisionPointCommitHandler;
import august.lite.policiesmanager.SimplePoliciesManager;
import august.lite.responsegenerator.BuilderManager;
import august.lite.responsegenerator.PerformativeSelector;
import august.protocolhandler.simplebilateralprotocol.SimpleProtocolHandler;
import august.protocolnegotiator.simpleprotocolnegotiation.ProtocolNegotiator;

public class DefaultServicesFactory implements IServicesFactory {

	public ICoordinator createCoordinator(ServiceRegistry r) {
		return new ANSCoordinator(r);
	}

	public IThreadCoordinator createThreadCoordinator(ServiceRegistry r) {
		return new ThreadCoordinator(r);
	}

	public IInquirer createInquirer(ServiceRegistry r) {
        return new Inquirer(r);
    }
    
    public IProtocolNegotiator createProtocolNegotiator(ServiceRegistry r) {
        return new ProtocolNegotiator(r);
    }
    
    public Map<URI,IConfigurableProtocolHandler> createProtocolHandlers(ServiceRegistry r) {
    	Map<URI,IConfigurableProtocolHandler> protocolHandlers = new HashMap<URI, IConfigurableProtocolHandler>();
    	
    	protocolHandlers.put(URI.create("negofast:protocols.simplebilateralprotocol"), new SimpleProtocolHandler(r));
    	
    	return protocolHandlers;
    } 
    
    public INegotiator createNegotiator(ServiceRegistry r) {
        return new BargainingCoordinator(r);
    }

    public ICoordinableNegotiator createCoordinableNegotiator(ServiceRegistry r) {
        return new BilateralNegotiator(r);
    }

    public IPoliciesManager createPoliciesGenerator(ServiceRegistry r) {
        return new SimplePoliciesManager(r);
    }
   
    public <T extends IProposal<?>> IPerformativeSelector<T> createPerformativeSelector(ServiceRegistry r) {
    	return new PerformativeSelector<T>(r);
    }

    public <T extends IProposal<?>> IBuilderManager<T> createBuilderManager(ServiceRegistry r) {
    	return new BuilderManager<T>(r);
    }    
    
    public <T extends IProposal<?>> ICommitHandler<T> createCommitHandler(ServiceRegistry r) {
    	ICommitHandler<T> result = null;

    	URI id = URI.create("negofast:commitHandlers.simple");
    	IPreferencesResource preferences = r.getPreferences();
    	
    	if (preferences != null) {
    		IPreferencesDocument<?, ?> p = preferences.getPreferences();
    		
    		int an = p.getAgreementsNumber();    		
    		if (an > 0) {
    			id = URI.create("negofast:commitHandlers.decisionpoint");
    		}
    	}
    	
    	Logger.getLogger("august.infrastructure").info("Commit Handler selected: " + id);
    	
    	
    	if (id.equals(URI.create("negofast:commitHandlers.simple")))
    		result = (ICommitHandler<T>) new SimpleCommitHandler();
    	else if (id.equals(URI.create("negofast:commitHandlers.decisionpoint")))
    		result = (ICommitHandler<T>) new DecisionPointCommitHandler(r);
    	
    	return result;
    }

	public Map<URI, IProposalBuilder<? extends IProposal<?>>> createProposalBuilders(ServiceRegistry r) {
    	Map<URI,IProposalBuilder<? extends IProposal<?>>> proposalBuilders = new HashMap<URI, IProposalBuilder<? extends IProposal<?>>>();
    	
    	proposalBuilders.put(URI.create("negofast:proposalBuilders.negotiationdecisionfunctions"), 
    			new NegotiationDecisionFunctionsBuilder(r));
    	proposalBuilders.put(URI.create("negofast:proposalBuilders.initial"),
    			new InitialBuilder(r));
    	
    	return proposalBuilders;
	}

}
