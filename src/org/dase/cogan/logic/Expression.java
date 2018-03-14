package org.dase.cogan.logic;

import org.dase.cogan.operation.Node;

public class Expression
{
	private Node root;
	
	public Expression(Node root)
	{
		this.root = root;
	}
	
	public Rule convertToRule() throws CannotConvertToRuleException
	{
		// TODO: implement rule conversion
		return null;
	}
	
	public Expression NNF()
	{
		// TODO: implement NNF (negation normal form)
		return null;
	}
	
	public Expression negated()
	{
		return new Expression(root.negate());
	}
	
	public String toString()
	{
		return root.toString();
	}
}
