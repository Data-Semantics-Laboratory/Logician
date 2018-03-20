package org.dase.cogan.operation;

/** A UnaryOperator is a node that has only one child */
public abstract class UnaryOperator extends Node
{
	private Node formula;

	/** Probably better practice than instanceof? */
	public boolean isUnaryOperator()
	{
		return true;
	}

	public void setFormula(Node formula)
	{
		this.formula = formula;
	}

	public Node getFormula()
	{
		return this.formula;
	}
}
