package org.dase.cogan.operation;

public abstract class Operation extends Node
{
	private Node	leftFormula;
	private Node	rightFormula;
	
	public Node getLeftFormula()
	{
		return leftFormula;
	}
	public Node getRightFormula()
	{
		return rightFormula;
	}
	public void setLeftFormula(Node leftFormula)
	{
		this.leftFormula = leftFormula;
	}
	public void setRightFormula(Node rightFormula)
	{
		this.rightFormula = rightFormula;
	}
	
	public String toString()
	{
		// Construct
		String line = "(" + super.getLabel();
		line += " " + leftFormula.toString();
		line += " " + rightFormula.toString();
		line += ")";
		// Done
		return line;
	}
}
