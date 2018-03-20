package org.dase.cogan.operations;

public class Implication extends BinaryOperator
{
	/** Default Constructor */
	public Implication(Node leftFormula, Node rightFormula)
	{
		super.setLabel(">");
		super.setLeftFormula(leftFormula);
		super.setRightFormula(rightFormula);
	}

	/** Probably better practice than instanceof? */
	public boolean isQuantifier()
	{
		return true;
	}

	/**
	 * Recall that the implication is defined as -a V b, thus the negation is
	 * the conjunction a ^ -b
	 */
	public Node negate()
	{
		// Negate only the right hand side
		Node left = super.getLeftFormula();
		Node right = super.getRightFormula().negate();
		// Construct the top level negation
		Node conjunction = new Conjunction(left, right);
		// Done
		return conjunction;
	}

	/** We want to remove the implication for the NNF */
	public Node toNNF()
	{
		// Convert implication to -a v b
		Node left = super.getLeftFormula().negate();
		Node right = super.getRightFormula();
		// Get the NNF of both sides
		left = left.toNNF();
		right = right.toNNF();
		// Package as disjunction
		Node disjunction = new Disjunction(left, right);
		// Done
		return disjunction;
	}
	
	/** Infix latex string */
	public String toLatexString()
	{
		String line = super.getLeftFormula().toLatexString();
		line += " &\\rightarrow ";
		line += super.getRightFormula().toLatexString();
		
		return line; // TODO document
	}
}
