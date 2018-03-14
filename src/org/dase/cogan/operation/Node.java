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

	public abstract Node toNNF();

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
