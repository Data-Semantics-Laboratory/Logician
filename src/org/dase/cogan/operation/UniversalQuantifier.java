package org.dase.cogan.operation;

public class UniversalQuantifier extends Quantifier
{
	/** Default Constructor */
	public UniversalQuantifier(Node formula)
	{
		super.setLabel("A");
		super.setFormula(formula);
	}

	/**
	 * Negation of a universal quantifier is an existential, with the internal
	 * formula also negated.
	 */
	public Node negate()
	{
		// Negate the inside
		Node formula = super.getFormula().negate();
		// Flip the sign
		Node existential = new ExistentialQuantifier(formula);
		// Done
		return existential;
	}

	public Node toNNF()
	{
		// Get the NNF of the formula
		Node formula = super.getFormula().toNNF();
		// Package
		Node universal = new UniversalQuantifier(formula);
		// Done
		return universal;
	}
}
