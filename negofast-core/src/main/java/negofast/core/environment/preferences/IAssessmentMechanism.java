package negofast.core.environment.preferences;

import negofast.core.model.IPreferencesDocument;
import negofast.core.model.IProposal;

/**
 * An assessment mechanism provides a way to evaluate a proposal or an agreement
 * and, hence, to compare several proposals to find out which is the most
 * appealing. Assessment mechanisms are parameterised by the model used to
 * express the preferences ({@link IPreferencesDocument}) and the model used
 * to express the terms of a proposal ({@link IProposal}).
 * 
 * @author Manuel Resinas
 * 
 * @param <Pref>
 * @param
 * <P>
 */
public interface IAssessmentMechanism<Pref extends IPreferencesDocument<?,?>, P extends IProposal<?>> {

	/**
	 * Evaluates the given proposal.
	 * @param p
	 * @return
	 */
    public double evaluate(P p);
    /**
     * Compares both proposals.
     * @param p1
     * @param p2
     * @return
     */
    public int compare(P p1, P p2);
    /**
     * Analyses whether the proposal satisfies the minimum established by the user preferences.
     * @param p
     * @return
     */
    public boolean satisfies(P p);

}
