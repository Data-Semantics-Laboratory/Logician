package org.dase.cogan.operation;

public class Disjunction extends Operation
{
	public Disjunction(Node leftFormula, Node rightFormula)
	{
		super.setLabel("+");
		super.setLeftFormula(leftFormula);
		super.setRightFormula(rightFormula);
	}
	
	public Node negate()
	{
		Node left = super.getLeftFormula().negate();
		Node right = super.getRightFormula().negate();
		
		Node conjunction = new Conjunction(left, right);
		
		return conjunction;
	}
}
