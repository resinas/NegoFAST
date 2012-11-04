package august.lite.model;

import java.net.URI;
import java.util.Map;

import negofast.models.lite.IProposalEquals;
import negofast.models.lite.ITermEquals;
import august.core.model.AbstractProposal;

public class ProposalEquals extends AbstractProposal<ITermEquals> implements IProposalEquals {
	private static URI format = URI.create("negofast:proposals.equals");
	
	public ProposalEquals(Map<URI,URI> parties) {
		super(parties);
	}
	
	public URI getFormat() {
		return format;
	}
}
