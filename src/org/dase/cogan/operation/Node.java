package org.dase.cogan.operation;

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

	/** This allows us to negate an entire expression subtree */
	public abstract Node negate();

	/** This allows us to construct an nnf of the expression subtree */
	public abstract Node toNNF();

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

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}
}
