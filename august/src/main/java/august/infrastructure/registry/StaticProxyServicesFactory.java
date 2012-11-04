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
import august.infrastructure.comms.staticproxies.BuilderManagerProxy;
import august.infrastructure.comms.staticproxies.CommitHandlerProxy;
import august.infrastructure.comms.staticproxies.ConfigurableProtocolHandlerProxy;
import august.infrastructure.comms.staticproxies.CoordinableNegotiatorProxy;
import august.infrastructure.comms.staticproxies.CoordinatorProxy;
import august.infrastructure.comms.staticproxies.InquirerProxy;
import august.infrastructure.comms.staticproxies.NegotiationPoliciesGeneratorProxy;
import august.infrastructure.comms.staticproxies.NegotiatorProxy;
import august.infrastructure.comms.staticproxies.PerformativeSelectorProxy;
import august.infrastructure.comms.staticproxies.ProposalBuilderProxy;
import august.infrastructure.comms.staticproxies.ProtocolNegotiatorProxy;
import august.infrastructure.comms.staticproxies.ThreadCoordinatorProxy;

public class StaticProxyServicesFactory implements IServicesFactory {

	private IServicesFactory f;
	
	public StaticProxyServicesFactory(IServicesFactory f) {
		this.f = f;
	}
	
	public ICoordinator createCoordinator(ServiceRegistry r) {
		return new CoordinatorProxy(f.createCoordinator(r));
	}

	public IThreadCoordinator createThreadCoordinator(ServiceRegistry r) {
		return new ThreadCoordinatorProxy(f.createThreadCoordinator(r));
	}

	public <T extends IProposal<?>> IBuilderManager<T> createBuilderManager(
			ServiceRegistry r) {
		IBuilderManager<T> b = f.createBuilderManager(r);
		return new BuilderManagerProxy<T>(b);
	}

	public <T extends IProposal<?>> ICommitHandler<T> createCommitHandler(ServiceRegistry r) {
		ICommitHandler<T> c = f.createCommitHandler(r);
		return new CommitHandlerProxy<T>(c);
	}

	public ICoordinableNegotiator createCoordinableNegotiator(ServiceRegistry r) {
		return new CoordinableNegotiatorProxy(f.createCoordinableNegotiator(r));
	}

	public IInquirer createInquirer(ServiceRegistry r) {
		return new InquirerProxy(f.createInquirer(r));
	}

	public <T extends IProposal<?>> IPerformativeSelector<T> createPerformativeSelector(
			ServiceRegistry r) {
		IPerformativeSelector<T> c = f.createPerformativeSelector(r);
		return new PerformativeSelectorProxy<T>(c);
	}

	public INegotiator createNegotiator(ServiceRegistry r) {
		return new NegotiatorProxy(f.createNegotiator(r));
	}

	public IPoliciesManager createPoliciesGenerator(ServiceRegistry r) {
		return new NegotiationPoliciesGeneratorProxy(f.createPoliciesGenerator(r));
	}

	public Map<URI, IProposalBuilder<? extends IProposal<?>>> createProposalBuilders(
			ServiceRegistry r) {
		Map<URI, IProposalBuilder<? extends IProposal<?>>> m = f.createProposalBuilders(r);
		
		for (IProposalBuilder<? extends IProposal<?>> b : m.values()) {			
			b = createProposalBuilder(b);
		}
		
		return m;
	}
	
	private <T extends IProposal<?>> IProposalBuilder<T> createProposalBuilder(IProposalBuilder<T> b) {
		return new ProposalBuilderProxy<T>(b);
	}

	public Map<URI, IConfigurableProtocolHandler> createProtocolHandlers(
			ServiceRegistry r) {
		Map<URI, IConfigurableProtocolHandler> m = f.createProtocolHandlers(r);
		
		for (IConfigurableProtocolHandler c : m.values()) {			
			c = new ConfigurableProtocolHandlerProxy(c);
		}
		
		return m;
	}

	public IProtocolNegotiator createProtocolNegotiator(ServiceRegistry r) {
		return new ProtocolNegotiatorProxy(f.createProtocolNegotiator(r));
	}

}
