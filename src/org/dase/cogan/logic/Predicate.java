package org.dase.cogan.logic;

import org.dase.cogan.operation.Negation;
import org.dase.cogan.operation.Node;

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
		// Negate
		Node negate = new Negation(this);
		// Done
		return negate;
	}

	/** By definition, Predicates are already in NNF */
	public Node toNNF()
	{
		return this;
	}
}
