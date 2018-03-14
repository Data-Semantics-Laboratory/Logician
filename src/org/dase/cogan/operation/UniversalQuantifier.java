package org.dase.cogan.operation;

public class UniversalQuantifier extends Quantifier
{
	public UniversalQuantifier(Node formula)
	{
		super.setLabel("A");
		super.setFormula(formula);
	}
	
	public Node negate()
	{
		Node formula = super.getFormula().negate();
		
		Node existential = new ExistentialQuantifier(formula);
		
		return existential;
	}
}
