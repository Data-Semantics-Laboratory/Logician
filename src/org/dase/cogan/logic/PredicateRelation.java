package org.dase.cogan.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dase.cogan.ingestion.StringIngestor;
import org.dase.cogan.operations.Node;
import org.dase.cogan.operations.Variable;

public class PredicateRelation extends Node
{
	private List<Node> args;
	
	public PredicateRelation(String predToken)
	{
		this.args = new ArrayList<>();
		// Extract and set the name of the predicate
		int argIndex = predToken.indexOf("(");
		// Strip off the # that indicates a control sequence to the ingestor
		String label = predToken.substring(1, argIndex);
		super.setLabel(label);
		/*
		 * Extract the bound variables. This varies from a predicate because the
		 * tokens may be complex.
		 */
		String argString = predToken.substring(argIndex+1, predToken.length() - 1);
		String[] argTokens = argString.split(",");
		parseArgTokens(argTokens);
	}

	private void parseArgTokens(String[] argTokens)
	{
		for(String argToken : argTokens)
		{
			if(argToken.startsWith("/"))
			{
				// Parse the embedded expression
				Expression expr = StringIngestor.ingest(argToken);
				// Convert the expression to a rule
				Rule rule;
				try
				{
					rule = expr.toRule();
					args.add(rule.toExpression().getRoot());
				}
				catch(CannotConvertToRuleException e)
				{
					System.out.println("Could not convert " + expr + "to rule.");
				}
			}
			else
			{
				args.add(new Variable(argToken));
			}
		}
	}
	
	/**
	 * Properly format the args with commas and parens such that it is a valid
	 * latex string
	 */
	public String toLatexString()
	{
		String line = "\\textit{" + super.getLabel() + "}";
		line += "&(";
		for(Iterator<Node> i = args.iterator(); i.hasNext();)
		{
			String arg = i.next().toLatexString();
			if(i.hasNext())
			{
				arg += ", ";
			}
			line += arg;
		}
		line += ")";
		return line;
	}

	public Node negate()
	{
		// This should never be called?
		// Or should it pass on the sign to the args?
		return this;
	}

	public Node toNNF()
	{
		// This should never be called?
		// Or should it pass on the sign to the args?
		return this;
	}
}
