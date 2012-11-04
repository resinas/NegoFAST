package august.infrastructure.comms;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public abstract class AbstractProxy<T> {

	private static ExecutorService executor = Executors.newCachedThreadPool();

	protected T elem;
	
	public AbstractProxy(T e) {
		if (e == null) throw new RuntimeException("The element is null");
		elem = e;
	}

	public void send(Runnable r) {
		executor.execute(r);
	}

	public <S> S getInternalReference(Class<S> c, S service) {
		S result = null;
		
		if (service instanceof AbstractProxy<?>) {
			result = service;
		}
		else {
			String name = c.getSimpleName();
			String proxyname = getClass().getPackage().getName() + "." + name.substring(1).concat("Proxy");

			Logger.getLogger("august.infrastructure").info("Loading proxy: " + proxyname);

			try {
				Class<S> cl = (Class<S>) Class.forName(proxyname);
				result = cl.getConstructor(c).newInstance(service);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

}
