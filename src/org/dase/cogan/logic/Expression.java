package org.dase.cogan.logic;

import java.util.ArrayList;
import java.util.List;

import org.dase.cogan.operations.BinaryOperator;
import org.dase.cogan.operations.Node;
import org.dase.cogan.operations.Quantifier;
import org.dase.cogan.operations.UnaryOperator;

public class Expression
{
	private Node root;

	/** Default Constructor */
	public Expression(Node root)
	{
		this.root = root;
	}

	/** This method attempts to convert the method to a rule */
	public Rule toRule() throws CannotConvertToRuleException
	{
		return new Rule(this.NNF());
	}

	/** This method attempts to conver the method to its clausal form */
	public ClausalForm toClausalForm()
	{
		return new ClausalForm(this.NNF());
	}

	/**
	 * This method will construct an expression that is logically equivalent to
	 * the original expression, but it is in negation normal form (NNF).
	 * 
	 * To do so, we will give a method to every node "toNNF()" so that it may
	 * construct a NNF version of itself.
	 * 
	 * @return
	 */
	public Expression NNF()
	{
		// Get the NNF of the expression
		Node nnf = root.toNNF();
		// Package
		Expression nnfExpr = new Expression(nnf);
		// Done
		return nnfExpr;
	}

	/** Return a negated version of this expression */
	public Expression negated()
	{
		// Get the negation of the the expression
		Node negated = root.negate();
		// Package
		Expression negatedExpr = new Expression(negated);
		// Done
		return negatedExpr;
	}

	/** This method currently expects an outermost quantifier */
	public void printScope()
	{
		List<String> scope = new ArrayList<>();

		Quantifier quantifier = (Quantifier) root;
		String boundVar = quantifier.getBoundVar();
		scope.add(boundVar);

		printScopeHelper(quantifier.getFormula(), scope);
	}

	public void printScopeHelper(Node node, List<String> scope)
	{
		System.out.println("\tNode " + node.getLabel() + " has scope " + scope);

		if(node.isQuantifier())
		{
			Quantifier quantifier = (Quantifier) node;
			String boundVar = quantifier.getBoundVar();
			scope.add(boundVar);
			printScopeHelper(quantifier.getFormula(), scope);
			scope.remove(boundVar);
		}
		else if(node.isBinaryOperator())
		{
			BinaryOperator binOp = (BinaryOperator) node;
			printScopeHelper(binOp.getLeftFormula(), scope);
			printScopeHelper(binOp.getRightFormula(), scope);
		}
		else if(node.isUnaryOperator())
		{
			UnaryOperator unOp = (UnaryOperator) node;
			printScopeHelper(unOp.getFormula(), scope);
		}
		else
		{
			// Do nothing if Predicate
		}
	}

	public Node getRoot()
	{
		return this.root;
	}

	/** Returns a latex formatted string */
	public String toLatexString()
	{
		return this.root.toLatexString();
	}

	public String toString()
	{
		return root.toString();
	}
}
