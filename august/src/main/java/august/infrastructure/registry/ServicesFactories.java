package august.infrastructure.registry;

import august.infrastructure.registry.servicesfactories.DefaultServicesFactory;

public class ServicesFactories {

	public static IServicesFactory getDefaultFactory() {
		return new DynamicProxyServicesFactory(new DefaultServicesFactory());
	}
}
