package org.dase.cogan.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.dase.cogan.operation.BinaryOperator;
import org.dase.cogan.operation.Node;
import org.dase.cogan.operation.Quantifier;
import org.dase.cogan.operation.UnaryOperator;

public class Expression
{
	private Node root;

	public Expression(Node root)
	{
		this.root = root;
	}

	public Rule convertToRule() throws CannotConvertToRuleException
	{
		// TODO: implement rule conversion
		return null;
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
		System.out.println("Node " + node.getLabel() + " has scope " + scope);
		
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
	
	public String toString()
	{
		return root.toString();
	}
}
