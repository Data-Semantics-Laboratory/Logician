package org.dase.cogan.logic;

/**
 * This Exception is thrown if the Rule constructor is passed an Expression that
 * cannot be converted to regular rule, existential rule, or disjunctive rule.
 * 
 * @author Cogs
 *
 */
public class CannotConvertToRuleException extends Exception
{
	/** Bookkeeping */
	private static final long serialVersionUID = 1L;
}
