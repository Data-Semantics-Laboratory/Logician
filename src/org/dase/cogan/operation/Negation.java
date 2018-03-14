package org.dase.cogan.operation;

import org.dase.cogan.logic.Predicate;

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

	public Node toNNF()
	{
		// Apply the negation sign
		Node negated = formula.negate();
		// Get the NNF of the applied negation
		Node formulaNNF = null;
		if(formula instanceof Predicate)
		{
			// A negated predicate is already in NNF
			formulaNNF = negated;
		}
		else
		{
			formulaNNF = negated.toNNF();
		}
		// Done
		return formulaNNF;
	}
}
