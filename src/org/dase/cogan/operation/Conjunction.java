package org.dase.cogan.operation;

public class Conjunction extends Operation
{
	public Conjunction(Node leftFormula, Node rightFormula)
	{
		super.setLabel("*");
		super.setLeftFormula(leftFormula);
		super.setRightFormula(rightFormula);
	}
	
	public Node negate()
	{
		Node left = super.getLeftFormula().negate();
		Node right = super.getRightFormula().negate();
		
		Node disjunction = new Disjunction(left, right);
		
		return disjunction;
	}
}
