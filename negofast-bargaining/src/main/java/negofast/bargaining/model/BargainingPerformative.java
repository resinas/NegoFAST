package negofast.bargaining.model;

import negofast.core.model.Performative;

/**
 * The performatives managed by the NegoFAST-B framework are those used in the
 * abstract negotiation protocol. They are represented by this enumeration, which is a subtype of interface
 * {@link Performative} defined in NegoFAST-C framework.
 * 
 * @author Manuel Resinas
 * 
 */
public enum BargainingPerformative implements Performative {
	cfp, propose, commit, accept, withdraw, rejectProposal, rejectNegotiation
}
