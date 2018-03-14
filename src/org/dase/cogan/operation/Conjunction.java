package org.dase.cogan.operation;

public class Conjunction extends Operation
{
	/** Default Constructor */
	public Conjunction(Node leftFormula, Node rightFormula)
	{
		super.setLabel("*");
		super.setLeftFormula(leftFormula);
		super.setRightFormula(rightFormula);
	}
	
	/** Negation requires demorgan's law */
	public Node negate()
	{
		// Negate both sides
		Node left = super.getLeftFormula().negate();
		Node right = super.getRightFormula().negate();
		// Flip the sign
		Node disjunction = new Disjunction(left, right);
		// Done
		return disjunction;
	}
}
