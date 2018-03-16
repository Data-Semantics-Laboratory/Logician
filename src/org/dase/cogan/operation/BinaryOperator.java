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

	/** Probably better practice than instanceof? */
	public boolean isBinaryOperator()
	{
		return true;
	}

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

	/** Iterable accessor for the formulas */
	public List<Node> getFormulas()
	{
		List<Node> formulas = new ArrayList<>();
		formulas.add(leftFormula);
		formulas.add(rightFormula);
		return formulas;
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
