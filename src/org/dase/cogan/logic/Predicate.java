package org.dase.cogan.logic;

import org.dase.cogan.operation.Node;
import org.dase.cogan.ui.Negation;

public class Predicate extends Node
{
	/** Default Constructor */
	public Predicate(String label)
	{
		super.setLabel(label);
	}
	
	/** Negation of an atom P is -P */
	public Node negate()
	{
		Node negate = new Negation(this);
		
		return negate;
	}
}
