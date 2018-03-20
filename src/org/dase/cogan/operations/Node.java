package org.dase.cogan.operations;

/**
 * This abstract class defines default behavior for all nodes in an expression.
 * Namely all nodes have a label and are negatable.
 * 
 * @author Cogs
 *
 */
public abstract class Node
{
	private String label;

	/**
	 * This allows us to recursively construct the negation of the expression.
	 */
	public abstract Node negate();

	/**
	 * This allows us to recursively construct the negation normal form of the
	 * expression.
	 */
	public abstract Node toNNF();

	/**
	 * This allows us to recursively construct the latex string of the
	 * expression.
	 */
	public abstract String toLatexString();

	///////////////////////////
	public boolean isPredicate()
	{
		return false;
	}

	public boolean isUnaryOperator()
	{
		return false;
	}

	public boolean isBinaryOperator()
	{
		return false;
	}

	public boolean isQuantifier()
	{
		return false;
	}

	///////////////////////////
	public String toString()
	{
		return label;
	}

	public String getLatexLabel()
	{
		String line = "\\text{";
		line += label;
		line += "}";
		
		return line;
	}
	
	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}
}
