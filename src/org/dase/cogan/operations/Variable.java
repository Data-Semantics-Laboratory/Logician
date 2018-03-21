package org.dase.cogan.operations;

public class Variable extends Node
{
	/** Default Constructor */
	public Variable(String label)
	{
		super.setLabel(label);
	}
	
	/** This should never be called. */
	public Node negate()
	{
		return this;
	}

	/** This should never be called. */
	public Node toNNF()
	{
		return this;
	}

	/** Latex formatted string */
	public String toLatexString()
	{
		String line = "\\text{";
		line += super.getLabel();
		line += "}";
		
		return line;
	}
}
