package org.dase.cogan.ui;

import org.dase.cogan.operation.Node;

public class Negation extends Node
{
	private Node formula;
	
	/** default constructor */
	public Negation(Node formula)
	{
		super.setLabel("-");
		this.formula = formula;
	}
	
	/** standard to string */
	public String toString()
	{
		// Construct
		String line = super.getLabel();
		line += formula.toString();
		// Done
		return line;
	}
	
	/** --F = F */
	public Node negate()
	{
		return formula;
	}
}
