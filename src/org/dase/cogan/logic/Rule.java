package org.dase.cogan.logic;

import java.util.List;

import org.dase.cogan.operation.Conjunction;
import org.dase.cogan.operation.Implication;
import org.dase.cogan.operation.Node;
import org.dase.cogan.operation.UniversalQuantifier;

public class Rule
{
	private Expression	expr;
	private ClausalForm	clausalForm;
	private Node		antecedent;
	private Node		consequent;
	private Node		rule;

	public Rule(Expression expr) throws CannotConvertToRuleException
	{
		this.expr = expr;
		this.clausalForm = expr.toClausalForm();

		createRule();
	}

	private void createRule() throws CannotConvertToRuleException
	{
		// Construct the formulas for each of these
		createAntecedent();
		createConsequent();
		// Construct the Rule!
		Node implication = new Implication(antecedent, consequent);
		createQuantifierChain(implication);
	}

	private void createAntecedent() throws CannotConvertToRuleException
	{
		int size = clausalForm.getClauses().size();
		if(size <= 1)
		{
			throw new CannotConvertToRuleException();
		}
		else
		{
			List<Node> disjunctiveClause = clausalForm.getClauses().subList(0, size - 1);
			this.antecedent = createAntecedentHelper(disjunctiveClause);
		}
	}

	private Node createAntecedentHelper(List<Node> disjunctiveClause)
	{
		Node node = disjunctiveClause.remove(0).negate();
		
		if(!disjunctiveClause.isEmpty())
		{
			node = new Conjunction(node, createAntecedentHelper(disjunctiveClause));
		}
			
		return node;
	}

	private void createConsequent()
	{
		int size = clausalForm.getClauses().size();
		this.consequent = clausalForm.getClauses().get(size - 1);
	}

	private void createQuantifierChain(Node implication)
	{
		List<String> scopes = clausalForm.getScope();
		this.rule = createQuantifierChainHelper(scopes, implication);
	}

	private Node createQuantifierChainHelper(List<String> scopes, Node implication)
	{
		Node scope = null;
		String s = scopes.remove(0);
		if(scopes.isEmpty())
		{
			scope = new UniversalQuantifier(s, implication);
		}
		else
		{
			scope = new UniversalQuantifier(s, createQuantifierChainHelper(scopes, implication));
		}
		
		return scope;
	}
	
	public Expression getExpr()
	{
		return this.expr;
	}
	
	public String toString()
	{
		// TODO
		return rule.toString();
	}

}
