package org.dase.cogan.logic;

import java.util.ArrayList;
import java.util.List;

import org.dase.cogan.operations.Disjunction;
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

	/** Default Constructor */
	public ClausalForm(Expression expr)
	{
		// Set stuff
		this.expr = expr;
		this.clauses = new ArrayList<>();
		this.scope = new ArrayList<>();
		// Construct the clausalform from the given expression
		clausalForm();
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
		// Otherwise, add the node to the disjunctive clause
		else
		{
			clauses.add(node);
		}
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

	public String toString()
	{
		return scope.toString() + clauses.toString();
	}
}
