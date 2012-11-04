package august.core.responsegenerator;

import negofast.bargaining.interactions.requestproposal.IProposalRequester;
import negofast.bargaining.interactions.requestresponse.IResponseGenerator;
import negofast.core.model.IProposal;

public interface IPerformativeSelector<P extends IProposal<?>> extends IResponseGenerator<P>, IProposalRequester<P> {

}
