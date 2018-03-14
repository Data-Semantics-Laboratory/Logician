package org.dase.cogan.operation;

public abstract class UnaryOperator extends Node
{
	private Node formula;
	
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
