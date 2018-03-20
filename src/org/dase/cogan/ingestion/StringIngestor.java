package org.dase.cogan.ingestion;

import java.util.Stack;

import org.dase.cogan.logic.Expression;
import org.dase.cogan.logic.Predicate;
import org.dase.cogan.logic.PredicateRelation;
import org.dase.cogan.operations.Conjunction;
import org.dase.cogan.operations.Disjunction;
import org.dase.cogan.operations.ExistentialQuantifier;
import org.dase.cogan.operations.Implication;
import org.dase.cogan.operations.Negation;
import org.dase.cogan.operations.Node;
import org.dase.cogan.operations.UniversalQuantifier;

public class StringIngestor
{
	private static Stack<String> stack;

	// @formatter:off
	/**
	 * Expression is a preorder string of space delimited tokens 
	 * 
	 * * conjunction (binary)
	 * + disjunction (binary)
	 * > implication (binary)
	 * A forall quantification (unary)
	 * E some quantification   (unary)
	 * - negation              (unary)
	 * 
	 * Quantifiers are expected to have a variable of some sort after them.
	 * An example string is
	 * Ax > Px > Ay Rxy Cy
	 * 
	 * @param exprString
	 */
	// @formatter:on
	public static Expression ingest(String exprString)
	{
		// Initialize
		stack = new Stack<>();
		// Tokenize the input
		String[] tokens = exprString.split(" ");
		for(int i = tokens.length - 1; i >= 0; i--)
		{
			stack.push(tokens[i]);
		}

		// Construct the expression
		Expression expression = new Expression(ingestHelper());

		// Done
		return expression;
	}

	/**
	 * This method creates the formula recursively by determining which sort of
	 * node to construct based on the ingested token
	 */
	private static Node ingestHelper()
	{
		String token = stack.pop();
		Node node = null;

		if(token.startsWith("/A")) // Universal
		{
			// Strip off the bound variable from
			String boundVar = token.substring(2);
			node = new UniversalQuantifier(boundVar, ingestHelper());
		}
		else if(token.startsWith("/E")) // Existential
		{
			// Strip off the bound variable
			String boundVar = token.substring(2);
			node = new ExistentialQuantifier(boundVar, ingestHelper());
		}
		else if(token.startsWith("#")) // Predicate Relation (control sequence)
		{
			node = new PredicateRelation(token);
		}
		else if(token.equals("+")) // Disjunction
		{
			Node left = ingestHelper();
			Node right = ingestHelper();

			node = new Disjunction(left, right);
		}
		else if(token.equals("*")) // Conjunction
		{
			Node left = ingestHelper();
			Node right = ingestHelper();

			node = new Conjunction(left, right);
		}
		else if(token.equals(">")) // Implication
		{
			Node left = ingestHelper();
			Node right = ingestHelper();

			node = new Implication(left, right);
		}
		else if(token.equals("-")) // Negation
		{
			node = new Negation(ingestHelper());
		}
		else // Predicate
		{
			node = new Predicate(token);
		}

		return node;
	}
	
	public static boolean isMultiexpression(String expression)
	{
		boolean isMultiexpression = expression.contains("|");
		
		return isMultiexpression;
	}
}
