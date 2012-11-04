package august.infrastructure.registry;

import java.net.URI;
import java.util.Map;

import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestinformation.IInquirer;
import negofast.core.interactions.requestnegotiation.INegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.user.ICoordinator;
import negofast.core.model.IProposal;
import august.core.responsegenerator.IPerformativeSelector;

public interface IServicesFactory {
	
	public ICoordinator createCoordinator(ServiceRegistry r);
	public IThreadCoordinator createThreadCoordinator(ServiceRegistry r);
	
	public IInquirer createInquirer(ServiceRegistry r);
    
    public IProtocolNegotiator createProtocolNegotiator(ServiceRegistry r);
    
    public Map<URI,IConfigurableProtocolHandler> createProtocolHandlers(ServiceRegistry r);
   
    public INegotiator createNegotiator(ServiceRegistry r);
    public ICoordinableNegotiator createCoordinableNegotiator(ServiceRegistry r);
    public IPoliciesManager createPoliciesGenerator(ServiceRegistry r);

    public <T extends IProposal<?>> IPerformativeSelector<T> createPerformativeSelector(ServiceRegistry r);
    public <T extends IProposal<?>> IBuilderManager<T> createBuilderManager(ServiceRegistry r);
    public <T extends IProposal<?>> ICommitHandler<T> createCommitHandler(ServiceRegistry r);
    
    public Map<URI,IProposalBuilder<? extends IProposal<?>>> createProposalBuilders(ServiceRegistry r);
}
