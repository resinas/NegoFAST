package august.infrastructure.comms;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import negofast.bargaining.interactions.coordinatenegotiation.IBargainingCoordinator;
import negofast.bargaining.interactions.coordinatenegotiation.ICoordinableNegotiator;
import negofast.bargaining.interactions.createproposal.IProposalBuilder;
import negofast.bargaining.interactions.protocolconversion.IBargainingNegotiator;
import negofast.bargaining.interactions.protocolconversion.IBargainingProtocolHandler;
import negofast.bargaining.interactions.requestproposal.IBuilderManager;
import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.interactions.requestresponse.IResponseGenerator;
import negofast.bargaining.interactions.requestresponse.IResponseRequester;
import negofast.bargaining.interactions.submitpolicies.IPoliciesManager;
import negofast.bargaining.interactions.submitpolicies.IPolicyReceiver;
import negofast.core.interactions.configurehandler.IConfigurableProtocolHandler;
import negofast.core.interactions.incomingnegotiation.IIncomingNegotiationReceiver;
import negofast.core.interactions.incomingnegotiation.IIncomingProtocolHandler;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiationReceiver;
import negofast.core.interactions.incomingprotocolnegotiation.IIncomingProtocolNegotiator;
import negofast.core.interactions.requestcommitapproval.ICommitHandler;
import negofast.core.interactions.requestcommitapproval.ICommitRequester;
import negofast.core.interactions.requestinformation.IInformationRequester;
import negofast.core.interactions.requestinformation.IInquirer;
import negofast.core.interactions.requestnegotiation.INegotiationRequester;
import negofast.core.interactions.requestnegotiation.INegotiator;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiationRequester;
import negofast.core.interactions.requestprotocolnegotiation.IProtocolNegotiator;
import negofast.core.interactions.requestthreadprocessing.IThreadCoordinator;
import negofast.core.interactions.requestthreadprocessing.IThreadProcessingRequester;
import negofast.core.interactions.user.ICoordinator;
import negofast.core.interactions.user.IUser;
import august.core.responsegenerator.IPerformativeSelector;

public class DynamicProxy implements InvocationHandler {

	private static ExecutorService executor = Executors.newCachedThreadPool();
	private static Set<Class<?>> proxyable;

	public static Object createProxy(Object obj, Class<?> iface) {
		if (obj == null) throw new RuntimeException("The element is null");
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				new Class<?>[] { iface },
				new DynamicProxy(obj, true));
	}
	
	public static Object createExternalProxy(Object obj) {
		if (obj == null) throw new RuntimeException("The element is null");
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(),
				new DynamicProxy(obj, false));
	}

	
	static {
		proxyable = new HashSet<Class<?>>();
		
		proxyable.add(IBargainingCoordinator.class);
		proxyable.add(IBargainingNegotiator.class);
		proxyable.add(IBargainingProtocolHandler.class);
		proxyable.add(IBuilderManager.class);
		proxyable.add(ICommitHandler.class);
		proxyable.add(ICommitRequester.class);
		proxyable.add(IConfigurableProtocolHandler.class);
		proxyable.add(ICoordinableNegotiator.class);
		proxyable.add(ICoordinator.class);
		proxyable.add(IIncomingNegotiationReceiver.class);
		proxyable.add(IIncomingProtocolHandler.class);
		proxyable.add(IIncomingProtocolNegotiator.class);
		proxyable.add(IIncomingProtocolNegotiationReceiver.class);
		proxyable.add(IInformationRequester.class);
		proxyable.add(IInquirer.class);
		proxyable.add(IPoliciesManager.class);
		proxyable.add(INegotiationRequester.class);
		proxyable.add(INegotiator.class);
		proxyable.add(IPerformativeSelector.class);
		proxyable.add(IPolicyReceiver.class);
		proxyable.add(IProposalBuilder.class);
		proxyable.add(IProposalRequester.class);
		proxyable.add(IProtocolNegotiationRequester.class);
		proxyable.add(IProtocolNegotiator.class);
		proxyable.add(IResponseGenerator.class);
		proxyable.add(IResponseRequester.class);
		proxyable.add(IThreadCoordinator.class);
		proxyable.add(IThreadProcessingRequester.class);
		proxyable.add(IUser.class);
		
	}
	
	private Object target;
	private boolean setProxy;
	
	public DynamicProxy(Object obj, boolean setProxy) {
		target = obj;
		this.setProxy = setProxy;
	}
	
	public Object invoke(Object proxy, final Method m, final Object[] args)
			throws Throwable {
		Object result = null;
		
		if (m.getReturnType() == null || m.getReturnType().equals(Void.TYPE)) {		
			send(new Runnable() {				
				public void run() {
					invokeMethod(target, m, args);
				}
			});
		}
		else {
			result = invokeMethod(target, m, args);
		}
		
		return result;
	}
	
	private Object invokeMethod(Object proxy, Method m, Object[] args) {
		Object result = null;
		Object[] newArgs;
		
		if (setProxy && args != null) 
			newArgs = setProxy(m, args);
		else
			newArgs = args;
		
		try {
			result = m.invoke(target, newArgs);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Object[] setProxy(Method m, Object[] args) {
		Object[] result = new Object[args.length];
		
		Class<?>[] ifaces = m.getParameterTypes();
		
		for (int i = 0; i < args.length; i++) {
			if (!(args[i] == null) && ! Proxy.isProxyClass(args[i].getClass()) && proxyable.contains(ifaces[i])) {
				result[i] = DynamicProxy.createProxy(args[i], ifaces[i]);
			}
			else {
				result[i] = args[i];
				if (! (args[i] == null) && Proxy.isProxyClass(args[i].getClass()) && ! ifaces[i].isInstance(args[i])) {
					throw new RuntimeException("It is not an instance!!");
				}
			}			
		}
		
		return result;
	}

	public void send(Runnable r) {
		executor.execute(r);
	}

}
