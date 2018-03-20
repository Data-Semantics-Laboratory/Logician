package org.dase.cogan.operation;

public class ExistentialQuantifier extends Quantifier
{
	/** Default Constructor */
	public ExistentialQuantifier(String boundVar, Node formula)
	{
		super.setLabel("E");
		super.setBoundVar(boundVar);
		super.setFormula(formula);
	}

	/**
	 * Negation of an existential is a universal with the formula also negated
	 */
	public Node negate()
	{
		// Negate the formula
		Node formula = super.getFormula().negate();
		// Flip the quantifier
		Node universal = new UniversalQuantifier(super.getBoundVar(), formula);
		// Done
		return universal;
	}

	/** Converts the bound formula to NNF */
	public Node toNNF()
	{
		// Get the NNF of the formula
		Node formula = super.getFormula().toNNF();
		// Package
		Node existential = new ExistentialQuantifier(super.getBoundVar(), formula);
		// Done
		return existential;
	}
	
	/** Infix latex string */
	public String toLatexString()
	{
		String line = "\\exists ";
		line += "(";
		line += super.getFormula().toLatexString();
		line += ")";
		return line; // TODO document
	}
}
