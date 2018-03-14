package org.dase.cogan.operation;

public class ExistentialQuantifier extends Quantifier
{
	public ExistentialQuantifier(Node formula)
	{
		super.setLabel("E");
		super.setFormula(formula);
	}
	
	public Node negate()
	{
		Node formula = super.getFormula().negate();
		
		Node universal = new UniversalQuantifier(formula);
		
		return universal;
	}
}
