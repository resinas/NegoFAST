package negofast.core.model;


/**
 * An agreement defines a relationship between parties. The goal of an agreement
 * is to define the terms that regulate the execution of services or the sale of
 * goods. The different models of agreements are characterised by different
 * types of terms. Therefore, the interface IAgreement is parameterised
 * by the type of terms used in it (e.g. terms as pairs attribute-value or terms
 * as constraints).
 * 
 * @author Manuel Resinas
 * 
 */
public interface IAgreement extends IProposal<ITerm<?>> {
	
}