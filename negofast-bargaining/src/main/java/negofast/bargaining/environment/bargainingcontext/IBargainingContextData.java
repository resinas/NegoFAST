package negofast.bargaining.environment.bargainingcontext;

import java.net.URI;
import java.util.List;

import negofast.core.environment.negotiationcontext.INegotiationContextData;
import negofast.core.model.INegotiationMessage;
import negofast.core.model.IProposal;


/**
 * This interface extends interface {@link INegotiationContextData} that was
 * defined by the NegoFAST-C framework to support the bargaining-specific states
 * of the negotiation context and the provision
 * of additional information such as the best utility value of all current
 * negotiation threads.
 * 
 * The state of a bargaining negotiation is modelled by means of enumeration {@link BargainingState}. 
 * 
 * @author Manuel Resinas
 * 
 */
public interface IBargainingContextData extends INegotiationContextData {
    public BargainingState getBargainingState(URI context);
    /**
     * Sets the bargaining-specific state of the negotiation context as \inline{newState}.
     * @param context
     * @param newState
     */
    public void setBargainingState(URI context, BargainingState newState);

    public URI getParty(URI ctx);
    
    public INegotiationMessage<IProposal<?>> getLastNegotiationMessage(URI negotiation);
    public INegotiationMessage<IProposal<?>> getNegotiationMessage(URI negotiation, int i);
    public List<INegotiationMessage<IProposal<?>>> getNegotiationMessages(URI negotiation);
}
