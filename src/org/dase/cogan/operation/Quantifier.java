package org.dase.cogan.operation;

public abstract class Quantifier extends Node
{
	private Node formula;
	
	public void setFormula(Node formula)
	{
		this.formula = formula;
	}
	
	public Node getFormula()
	{
		return this.formula;
	}
	
	public String toString()
	{
		// Construct
		String line = super.getLabel() + "[";
		line += formula.toString();
		line += "]";
		// Done
		return line;
	}
}
