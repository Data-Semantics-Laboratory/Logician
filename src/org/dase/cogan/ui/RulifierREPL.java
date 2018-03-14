package org.dase.cogan.ui;

import java.util.Scanner;

import org.dase.cogan.ingestion.StringIngestor;
import org.dase.cogan.logic.Expression;

public class RulifierREPL
{
	public RulifierREPL()
	{
		
	}
	
	/** this method is a 'read eval print loop' used for testing the ingestion code. */
	public void test()
	{
		// Init resources for loop
		Scanner keyboard = new Scanner(System.in);
		boolean loop = true;

		while(loop)
		{
			System.out.println("Type 'exit' to quit.");
			System.out.print("Enter a string for ingestion: ");
		
			String line = keyboard.nextLine();
			
			loop = !line.equals("exit");
			if(loop)
			{
				Expression e = StringIngestor.ingest(line);
				System.out.println("\nThe string ingested is: " + e);
			}			
		}
		
		// close resources
		keyboard.close();
	}
}
