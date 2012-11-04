package august.infrastructure.registry.servicesfactories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import august.core.responsegenerator.IPerformativeSelector;
import august.infrastructure.registry.IServicesFactory;
import august.infrastructure.registry.ServiceRegistry;

public class XmlServicesFactory implements IServicesFactory {
	
	private Document config;
	private Map<String,String> roles;
	private Map<URI,String> proposalBuilders;
	private Map<URI,String> protocolHandlers;

	public XmlServicesFactory(String fileName) {
		roles = new HashMap<String, String>();
		proposalBuilders = new HashMap<URI, String>();
		protocolHandlers = new HashMap<URI, String>();
		openXML(fileName);
		loadConfig();
	}

	private void openXML(String fileName) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();
			config = builder.parse(fileName);

		} catch (Exception e) {
			System.err.println("Error al abrir el archivo de configuraci—n");
		}

	}
	
	private void loadConfig() {
		Node root = config.getFirstChild();
		if (root == null || root.getNodeName().equalsIgnoreCase("ANS")) throw new RuntimeException("Config file not valid!");
		
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
				processNode(children.item(i));
		}
		
	}
	
	private void processNode(Node node) {
		if (node.getNodeName().equalsIgnoreCase("proposalbuilder"))
			processMultipleRole(node, proposalBuilders);
		else if (node.getNodeName().equalsIgnoreCase("protocolhandler"))
			processMultipleRole(node, protocolHandlers);
		else
			processSimpleRole(node, roles);
	}
	
	private void processMultipleRole(Node node, Map<URI,String> target) {
		URI type;
		String className;

		Node typeNode = node.getAttributes().getNamedItem("type");
		type = URI.create(typeNode.getNodeValue());
		
		Node classNode = node.getAttributes().getNamedItem("implementationClass");
		className = classNode.getNodeValue();
		
		target.put(type, className);
	}
	
	private void processSimpleRole(Node node, Map<String,String> target) {
		String roleName;
		String className;
		
		roleName = node.getNodeName().toLowerCase();
		
		Node classNode = node.getAttributes().getNamedItem("implementationClass");
		className = classNode.getNodeValue();
		
		target.put(roleName, className);
	}
	
	public <T> T createSingleRole(String roleName, ServiceRegistry reg) {		
		String className = roles.get(roleName);
		return instantiate(className, reg);		
	}
	
	public <T> T instantiate(String className, ServiceRegistry reg) {
		T result = null;
		
		try {
			Class<T> c = (Class<T>) Class.forName(className);
			Constructor<T> constr = c.getConstructor(ServiceRegistry.class);
			result = constr.newInstance(reg);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;		
	}

	

	public <T extends IProposal<?>> IBuilderManager<T> createBuilderManager(
			ServiceRegistry r) {

		return createSingleRole("buildermanager",r);
	}

	public <T extends IProposal<?>> ICommitHandler<T> createCommitHandler(
			ServiceRegistry r) {

		return createSingleRole("commithandler",r);
	}

	public ICoordinableNegotiator createCoordinableNegotiator(ServiceRegistry r) {
		return createSingleRole("bilateralnegotiator", r);
	}

	public ICoordinator createCoordinator(ServiceRegistry r) {
		return createSingleRole("systemcoordinator", r);
	}

	public IInquirer createInquirer(ServiceRegistry r) {
		return createSingleRole("inquirer", r);
	}

	public INegotiator createNegotiator(ServiceRegistry r) {
		return createSingleRole("bargainingcoordinator", r);
	}

	public <T extends IProposal<?>> IPerformativeSelector<T> createPerformativeSelector(
			ServiceRegistry r) {
		return createSingleRole("performativeselector", r);
	}

	public IPoliciesManager createPoliciesGenerator(ServiceRegistry r) {
		return createSingleRole("policiesgenerator", r);
	}

	private <T> Map<URI,T> createMultipleRole(Map<URI,String> roles, ServiceRegistry reg) {
		Map<URI,T> builders = new HashMap<URI, T>();
		
		for (Entry<URI, String> entry : roles.entrySet()) {
			URI id = entry.getKey();
			T builder = instantiate(entry.getValue(),reg);
			
			builders.put(id, builder);
		}
		
		return builders;
		
	}

	public Map<URI, IProposalBuilder<? extends IProposal<?>>> createProposalBuilders(
			ServiceRegistry r) {
				
		return createMultipleRole(proposalBuilders, r);
	}

	public Map<URI, IConfigurableProtocolHandler> createProtocolHandlers(
			ServiceRegistry r) {
		return createMultipleRole(protocolHandlers, r);
	}

	public IProtocolNegotiator createProtocolNegotiator(ServiceRegistry r) {
		return createSingleRole("protocolnegotiator",r);
	}

	public IThreadCoordinator createThreadCoordinator(ServiceRegistry r) {
		return createSingleRole("threadcoordinator",r);
	}

}
