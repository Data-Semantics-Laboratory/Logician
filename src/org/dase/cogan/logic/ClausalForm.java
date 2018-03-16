package org.dase.cogan.logic;

import java.util.ArrayList;
import java.util.List;

import org.dase.cogan.operation.Disjunction;
import org.dase.cogan.operation.Node;
import org.dase.cogan.operation.Quantifier;
import org.dase.cogan.operation.UniversalQuantifier;

public class ClausalForm
{
	private Expression		expr;
	private List<Node>		clauses;
	private List<String>	scope;

	public ClausalForm(Expression expr)
	{
		// Set stuff
		this.expr = expr;
		this.clauses = new ArrayList<>();
		this.scope = new ArrayList<>();
		// Construct the clausalform from the given expression
		clausalForm();
	}

	private void clausalForm()
	{
		// Strip the first quantifier away
		Quantifier quantifier = (Quantifier) expr.getRoot();
		scope.add(quantifier.getBoundVar());
		// Starting with the root formula
		clausalFormHelper(clauses, scope, quantifier.getFormula());
	}

	private void clausalFormHelper(List<Node> clauses, List<String> scope, Node node)
	{
		if(node instanceof Disjunction)
		{
			Disjunction disjunction = (Disjunction) node;

			for(Node formula : disjunction.getFormulas())
			{
				if(formula instanceof Disjunction)
				{
					clausalFormHelper(clauses, scope, formula);
				}
				else if(formula instanceof UniversalQuantifier)
				{
					Quantifier universal = (Quantifier) formula;
					Node boundFormula = universal.getFormula();
					String boundVar = universal.getBoundVar();
					scope.add(boundVar);
					clausalFormHelper(clauses, scope, boundFormula);
				}
				else
				{
					clauses.add(formula);
				}
			}
		}
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
