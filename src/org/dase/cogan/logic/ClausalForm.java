package org.dase.cogan.logic;

import java.util.ArrayList;
import java.util.List;

import org.dase.cogan.operations.Disjunction;
import org.dase.cogan.operations.DisjunctionMaker;
import org.dase.cogan.operations.Node;
import org.dase.cogan.operations.Quantifier;
import org.dase.cogan.operations.UniversalQuantifier;

/**
 * The ClausalForm class is a representation of an expression in Disjunctive
 * Normal Form. That is, we take the nodes that are 'or-ed' together and put
 * them into a list for easier processing into a rule.
 * 
 * @author Cogs
 *
 */
public class ClausalForm
{
	private Expression		expr;
	private List<Node>		clauses;
	private List<String>	scope;
	private int				numPositiveLiterals;

	/** Default Constructor */
	public ClausalForm(Expression expr)
	{
		// Set stuff
		this.expr = expr;
		this.clauses = new ArrayList<>();
		this.scope = new ArrayList<>();
		// Construct the clausalform from the given expression
		clausalForm();
		numPositiveLiterals();
		// Convert to disjunctive rule, if necessary.
		if(this.numPositiveLiterals > 1)
		{
			convertToDisjunctiveRule();
		}
	}

	/** toplevel construction of the clausalform */
	private void clausalForm()
	{
		if(expr.getRoot() instanceof PredicateRelation)
		{
			clauses.add(expr.getRoot());
		}
		else
		{
			// Strip the first quantifier away
			Quantifier quantifier = (Quantifier) expr.getRoot();
			scope.add(quantifier.getBoundVar());
			// Starting with the root formula
			clausalFormHelper(clauses, scope, quantifier.getFormula());
		}
	}

	/**
	 * this helper method strips away the expression tree and leaves us with the
	 * 'or-ed' together subexpressions. It modifies clauses and scope by
	 * reference.
	 */
	private void clausalFormHelper(List<Node> clauses, List<String> scope, Node node)
	{
		// If the node is a disjunction
		if(node instanceof Disjunction)
		{
			// Typecast it
			Disjunction disjunction = (Disjunction) node;
			// For each of its arguments
			for(Node formula : disjunction.getFormulas())
			{
				// Recurse if the argument is a disjunction
				if(formula instanceof Disjunction)
				{
					clausalFormHelper(clauses, scope, formula);
				}
				// If the subexpression is universally quantified, track the
				// scope and parse the bound formula
				// TODO check to make sure that the scope is not bound. I think
				// that this should be fine for now, as we're not trying to
				// parse any existentials.
				else if(formula instanceof UniversalQuantifier)
				{
					Quantifier universal = (Quantifier) formula;
					Node boundFormula = universal.getFormula();
					String boundVar = universal.getBoundVar();
					scope.add(boundVar);
					clausalFormHelper(clauses, scope, boundFormula);
				}
				// Otherwise, add the formula to the disjunctive clause
				else
				{
					clauses.add(formula);
				}
			}
		}
		else if(node instanceof UniversalQuantifier)
		{
			Quantifier universal = (Quantifier) node;
			Node boundFormula = universal.getFormula();
			String boundVar = universal.getBoundVar();
			scope.add(boundVar);
			clausalFormHelper(clauses, scope, boundFormula);
		}
		// Otherwise, add the node to the disjunctive clause
		else
		{
			clauses.add(node);
		}
	}

	/**
	 * This method is only called if there are > 1 positive literals (i.e.
	 * non-quantifier nodes) detected in the clause. It extracts them, and
	 * reassembles them into a disjunction (after the the original disjunctions
	 * had been flattened.
	 */
	private void convertToDisjunctiveRule()
	{
		List<Node> positiveLiterals = new ArrayList<>();
		// Remove the positive literals
		for(Node n : clauses)
		{
			if(!n.isNegation())
			{
				positiveLiterals.add(n);
			}
		}
		// Remove afterwards
		for(Node n : positiveLiterals)
		{
			clauses.remove(n);
		}
		// Create a disjunction from the positive literals
		Node disjunction = DisjunctionMaker.makeDisjunctionChain(positiveLiterals);
		// Add it to the end of the clause
		clauses.add(disjunction);
	}

	private int numPositiveLiterals()
	{
		numPositiveLiterals = 0;
		for(Node node : clauses)
		{
			if(!node.isNegation() && !node.isQuantifier())
			{
				numPositiveLiterals++;
			}
		}

		return numPositiveLiterals;
	}

	public Expression getExpr()
	{
		return expr;
	}

	public List<Node> getClauses()
	{
		return clauses;
	}

	public List<String> getScope()
	{
		return scope;
	}

	public int getNumPositiveLiterals()
	{
		return numPositiveLiterals;
	}

	public String toString()
	{
		return scope.toString() + clauses.toString();
	}
}
