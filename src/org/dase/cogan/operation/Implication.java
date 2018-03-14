package org.dase.cogan.operation;

public class Implication extends Operation
{
	/** Default Constructor */
	public Implication(Node leftFormula, Node rightFormula)
	{
		super.setLabel(">");
		super.setLeftFormula(leftFormula);
		super.setRightFormula(rightFormula);
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

	public Node toNNF()
	{
		// Get the NNF of both sides
		// This should be fine because we are not
		// Distributing anything over the sign.
		Node left = super.getLeftFormula().toNNF();
		Node right = super.getRightFormula().toNNF();
		// Package
		Node implication = new Implication(left, right);
		// Done
		return implication;
	}
}
