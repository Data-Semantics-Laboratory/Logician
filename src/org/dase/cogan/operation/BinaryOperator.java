package org.dase.cogan.operation;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class defines some default behavior for the binary operations
 * 
 * @author Cogs
 *
 */
public abstract class BinaryOperator extends Node
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

	public List<Node> getFormulas()
	{
		List<Node> formulas = new ArrayList<>();
		formulas.add(leftFormula);
		formulas.add(rightFormula);
		return formulas;
	}
	
	public boolean isBinaryOperator()
	{
		return true;
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
