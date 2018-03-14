package org.dase.cogan.ingestion;

import java.util.Stack;

import org.dase.cogan.logic.Expression;
import org.dase.cogan.logic.Predicate;
import org.dase.cogan.operation.Conjunction;
import org.dase.cogan.operation.Disjunction;
import org.dase.cogan.operation.ExistentialQuantifier;
import org.dase.cogan.operation.Implication;
import org.dase.cogan.operation.Node;
import org.dase.cogan.operation.UniversalQuantifier;
import org.dase.cogan.ui.Negation;

public class StringIngestor
{
	private static Stack<String> stack;
	
	/**
	 * Expression is a preorder string of space delimited tokens 
	 * 
	 * * conjunction (binary)
	 * + disjunction (binary)
	 * > implication (binary)
	 * A forall quantification (unary)
	 * E some quantification   (unary)
	 * - negation              (unary)
	 * @param exprString
	 */
	public static Expression ingest(String exprString)
	{
		// Initialize
		stack = new Stack<>();
		// Tokenize the input
		String[] tokens = exprString.split(" ");
		for(int i=tokens.length-1;i>=0;i--)
		{
			stack.push(tokens[i]);
		}
		
		// Construct the expression
		Expression expression = new Expression(ingestHelper());
		
		// Done
		return expression;
	}
	
	public static Node ingestHelper()
	{
		String token = stack.pop();
		Node node = null;
		
		if(token.equals("A"))      // Universal
		{
			node = new UniversalQuantifier(ingestHelper());
		}
		else if(token.equals("E")) // Existential
		{
			node = new ExistentialQuantifier(ingestHelper());
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
		else                       // Predicate
		{
			node = new Predicate(token);
		}
		
		return node;
	}
}