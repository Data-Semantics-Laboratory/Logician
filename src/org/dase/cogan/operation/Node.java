package org.dase.cogan.operation;

public abstract class Node
{
	private String label;

	public abstract Node negate();
	
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
