package org.dase.cogan.logic;

import java.util.Iterator;

public class PredicateRelation extends Predicate
{

	public PredicateRelation(String predToken)
	{
		// Strip off the # that indicates a control sequence to the ingestor
		super(predToken.substring(1));
	}

	/**
	 * Properly format the args with commas and parens such that it is a valid
	 * latex string
	 */
	public String toLatexString()
	{
		String line = "\\textit{" + super.getLabel() + "}";
		line += "&(";
		for(Iterator<String> i = super.getArgs().iterator(); i.hasNext();)
		{
			String arg = "\\text{" + i.next() + "}";
			if(i.hasNext())
			{
				arg += ",";
			}
			line += arg;
		}
		line += ")";
		return line;
	}
}
