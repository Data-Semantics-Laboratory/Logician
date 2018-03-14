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

	/**
	 * This method will construct an expression that is logically equivalent to
	 * the original expression, but it is in negation normal form (NNF).
	 * 
	 * To do so, we will give a method to every node "toNNF()" so that it may
	 * construct a NNF version of itself.
	 * 
	 * @return
	 */
	public Expression NNF()
	{
		// Get the NNF of the expression
		Node nnf = root.toNNF();
		// Package
		Expression nnfExpr = new Expression(nnf);
		// Done
		return nnfExpr;
	}

	public Expression negated()
	{
		// Get the negation of the the expression
		Node negated = root.negate();
		// Package
		Expression negatedExpr = new Expression(negated);
		// Done
		return negatedExpr;
	}

	public String toString()
	{
		return root.toString();
	}
}
