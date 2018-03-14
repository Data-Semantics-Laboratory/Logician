package org.dase.cogan.operation;

public class Implication extends Operation
{
	public Implication(Node leftFormula, Node rightFormula)
	{
		super.setLabel(">");
		super.setLeftFormula(leftFormula);
		super.setRightFormula(rightFormula);
	}
	
	public Node negate()
	{
		// Recall that > === -a V b
		// Thus a ^ -b is the negation
		Node left = super.getLeftFormula();
		Node right = super.getRightFormula().negate();
		
		Node conjunction = new Conjunction(left, right);
		
		return conjunction;
	}
}
