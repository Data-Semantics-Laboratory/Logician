package org.dase.cogan.operation;

import org.dase.cogan.logic.Predicate;

public class Negation extends UnaryOperator
{
	/** default constructor */
	public Negation(Node formula)
	{
		super.setLabel("-");
		super.setFormula(formula);
	}

	/** standard to string */
	public String toString()
	{
		// Construct
		String line = super.getLabel();
		line += super.getFormula().toString();
		// Done
		return line;
	}

	/** --F = F */
	public Node negate()
	{
		return super.getFormula();
	}

	public Node toNNF()
	{
		// Apply the negation sign
		Node negated = super.getFormula().negate();
		// Get the NNF of the applied negation
		Node formulaNNF = null;
		if(super.getFormula() instanceof Predicate)
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
