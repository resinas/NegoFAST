package negofast.core.environment.worldmodel;

import negofast.core.environment.agreements.IAgreementsResource;
import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.environment.preferences.IPreferencesResource;
import negofast.core.environment.systemcontext.ISystemContextData;
import negofast.core.environment.threadcontext.IThreadContextData;

public interface IConfigureEstimator {
	public IEstimator configure(
			ISystemContextData system,
			IThreadContextData thread,
			INegotiationContextData negotiation,
			IPreferencesResource preferences,
			IAgreementsResource agreements);
}
