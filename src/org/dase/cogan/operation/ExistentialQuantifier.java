package org.dase.cogan.operation;

public class ExistentialQuantifier extends Quantifier
{
	/** Default Constructor */
	public ExistentialQuantifier(Node formula)
	{
		super.setLabel("E");
		super.setFormula(formula);
	}
	
	/** Negation of an existential is a universal with the formula also negated */
	public Node negate()
	{
		// Negate the formula
		Node formula = super.getFormula().negate();
		// Flip the quantifier
		Node universal = new UniversalQuantifier(formula);
		// Done
		return universal;
	}
}
