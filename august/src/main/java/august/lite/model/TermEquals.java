package august.lite.model;

import java.net.URI;

import negofast.models.lite.ITermEquals;
import negofast.models.lite.statements.constraint.IEquals;
import august.core.model.AbstractTerm;


public class TermEquals extends AbstractTerm<IEquals> implements ITermEquals {

	public TermEquals(IEquals c, URI party) {
		super(c, party);
	}
}
