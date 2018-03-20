package org.dase.cogan.operation;

public class UniversalQuantifier extends Quantifier
{
	/** Default Constructor */
	public UniversalQuantifier(String boundVar, Node formula)
	{
		super.setLabel("A");
		super.setBoundVar(boundVar);
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
		Node existential = new ExistentialQuantifier(super.getBoundVar(), formula);
		// Done
		return existential;
	}

	/** Converts the bound formula to NNF */
	public Node toNNF()
	{
		// Get the NNF of the formula
		Node formula = super.getFormula().toNNF();
		// Package
		Node universal = new UniversalQuantifier(super.getBoundVar(), formula);
		// Done
		return universal;
	}
	
	/** Infix latex string */
	public String toLatexString()
	{
		String line = "\\forall ";
		line += "x_{" + super.getBoundVar() + "}";
		line += "(";
		line += super.getFormula().toLatexString();
		line += ")";
		return line; // TODO document
	}
}
