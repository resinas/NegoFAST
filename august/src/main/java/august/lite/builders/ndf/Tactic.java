package august.lite.builders.ndf;

import java.net.URI;
import java.util.Properties;

import negofast.bargaining.environment.bargainingcontext.IBargainingContextData;
import negofast.bargaining.model.INegotiationStatus;
import negofast.models.lite.IPreferencesDocLite;
import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import negofast.models.lite.statements.constraint.IEquals;

public interface Tactic {

	public abstract IEquals execute(ITermEquals t);
	
	public void setUp(IPreferencesDocLite p, INegotiationStatus<IProposalEquals> status, IBargainingContextData ctx, URI thread, Properties config);

}