package org.dase.cogan.operation;

/**
 * This abstract class is for defining the default behavior for both
 * quantifiers. It prevents code duplication in the getter/setter for their
 * labels and tostring methods
 * 
 * @author Cogs
 *
 */
public abstract class Quantifier extends Node
{
	private Node formula;
	private String boundVar;
	
	public String getBoundVar()
	{
		return boundVar;
	}

	public void setBoundVar(String boundVar)
	{
		this.boundVar = boundVar;
	}

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
		String line = super.getLabel() + boundVar;
		line += "[";
		line += formula.toString();
		line += "]";
		// Done
		return line;
	}
}
