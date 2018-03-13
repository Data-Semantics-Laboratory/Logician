package org.dase.cogan.ingestion;

import java.util.Scanner;

public class StringIngestor
{
	/**
	 * Expression is a preorder string of characters 
	 * 
	 * * conjunction
	 * + disjunction
	 * > implication
	 * A forall quantification
	 * E some quantification
	 * @param expression
	 */
	public static void ingest(String expression)
	{
		Scanner reader = new Scanner(expression);

		while(reader.hasNext())
		{
			
		}
		
		// clean up
		reader.close();
	}
}
