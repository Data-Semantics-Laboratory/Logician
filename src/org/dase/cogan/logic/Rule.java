package org.dase.cogan.logic;

import java.util.List;

import org.dase.cogan.operations.Conjunction;
import org.dase.cogan.operations.Implication;
import org.dase.cogan.operations.Node;
import org.dase.cogan.operations.UniversalQuantifier;

public class Rule
{
	private Expression	expr;
	private ClausalForm	clausalForm;
	private Node		antecedent;
	private Node		consequent;
	private Node		rule;

	private boolean		verbose;

	/** Default Constructor */
	public Rule(Expression expr) throws CannotConvertToRuleException
	{
		this.expr = expr;
		this.clausalForm = expr.toClausalForm();

		if(expr.getRoot() instanceof PredicateRelation)
		{
			rule = expr.getRoot();
		}
		else
		{
			createRule();
		}
	}

	public Rule(Expression expr, boolean verbose) throws CannotConvertToRuleException
	{
		this.expr = expr;
		this.clausalForm = expr.toClausalForm();
		this.verbose = verbose;

		if(expr.getRoot() instanceof PredicateRelation)
		{
			rule = expr.getRoot();
		}
		else
		{
			createRule();
		}
	}

	/** toplevel procedure for creating the rule */
	private void createRule() throws CannotConvertToRuleException
	{
		// Construct the formulas for each of these
		createAntecedent(); // Recall that antecdent is before the implication
		createConsequent(); // Recall that consequent is after the implication
		// Construct the Rule!
		Node implication = new Implication(antecedent, consequent);
		// Bind the variables in the outermost scope
		if(verbose)
		{
			createQuantifierChain(implication);
		}
		else
		{
			rule = implication;
		}
	}

	/**
	 * createAntecedent is a recursive method for creating the conjunction that
	 * preceeds the implication.
	 */
	private void createAntecedent() throws CannotConvertToRuleException
	{
		// Right now we are not interested in facts
		int size = clausalForm.getClauses().size();
		if(size <= 1) // Throw an exception if we can't create the conjunction
		{
			throw new CannotConvertToRuleException();
		}
		else
		{
			// The last element in the disjunctive clause should be the
			// consequent
			List<Node> disjunctiveClause = clausalForm.getClauses().subList(0, size - 1);
			// Recursively create the antecedent
			this.antecedent = createAntecedentHelper(disjunctiveClause);
		}
	}

	/**
	 * This method is the recursive helper method. It is essentially applying
	 * demorgan's law for the definition of implication
	 */
	private Node createAntecedentHelper(List<Node> disjunctiveClause)
	{
		// Negate (for demorgans)
		Node node = disjunctiveClause.remove(0).negate();

		// If there are more elements, continue to chain the conjunction
		if(!disjunctiveClause.isEmpty())
		{
			node = new Conjunction(node, createAntecedentHelper(disjunctiveClause));
		}

		// return the conjunction chain
		return node;
	}

	/** This method simply strips off the consequence and sets it */
	private void createConsequent()
	{
		// Get the last element in the disjunctiveClause
		int size = clausalForm.getClauses().size();
		this.consequent = clausalForm.getClauses().get(size - 1);
	}

	/**
	 * construct the quantifiers for the outermost scope of the rule and attach
	 * it to the implication
	 */
	private void createQuantifierChain(Node implication)
	{
		List<String> scopes = clausalForm.getScope();
		this.rule = createQuantifierChainHelper(scopes, implication);
	}

	/** this is the recursive helper method for creating the quantifier chain */
	private Node createQuantifierChainHelper(List<String> scopes, Node implication)
	{
		Node scope = null;
		// Get the current scope
		String s = scopes.remove(0);
		// If there are no more variables to bind, do not recurse
		if(scopes.isEmpty()) // base case
		{
			scope = new UniversalQuantifier(s, implication);
		}
		// Otherwise, continue constructing the universalquantifier chain.
		else // recursive case
		{
			scope = new UniversalQuantifier(s, createQuantifierChainHelper(scopes, implication));
		}
		// Return the quantifier chain terminated with the implication
		return scope;
	}

	public Expression getExpr()
	{
		return this.expr;
	}

	/** Returns a latex formatted string for the rule. */
	public String toLatexString()
	{
		return this.rule.toLatexString();
	}

	public String toString()
	{
		return this.rule.toString();
	}
}
