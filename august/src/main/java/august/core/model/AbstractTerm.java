package august.core.model;

import java.net.URI;

import negofast.core.model.IConstraint;
import negofast.core.model.ITerm;

public abstract class AbstractTerm<C extends IConstraint> implements ITerm<C> {
    protected C constraint;
    private URI obligatedParty;

    public AbstractTerm(C c, URI party) {
    	constraint = c;
    	obligatedParty = party;
    }
    
    public AbstractTerm() {
    	
    }

    public C getConstraint() {
        return constraint;
    }

    public URI getObligatedParty() {
        return obligatedParty;
    }

    public void setObligatedParty(URI val) {
        this.obligatedParty = val;
    }

	public void setConstraint(C val) {
		constraint = val;		
	}
	
	public String toString() {
		return "[" + obligatedParty + "]" + constraint.toString();
	}
}
