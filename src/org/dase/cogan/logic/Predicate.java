package org.dase.cogan.logic;

import org.dase.cogan.operation.Node;
import org.dase.cogan.ui.Negation;

public class Predicate extends Node
{
	public Predicate(String label)
	{
		super.setLabel(label);
	}
	
	public Node negate()
	{
		Node negate = new Negation(this);
		
		return negate;
	}
}
