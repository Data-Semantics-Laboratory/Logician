package org.dase.cogan;

import org.dase.cogan.ui.RulifierGUI;
import org.dase.cogan.ui.RulifierREPL;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			String flag = args[0];

			if(flag.equals("-d"))
			{
				(new RulifierREPL()).run();
			}
			else if(flag.equals("-t"))
			{
				RulifierREPL repl = new RulifierREPL();

				if(args[1].equals("string"))
				{
					repl.stringTest();
				}
				else
				{
					repl.ontoTest();
				}
			}
			else // -g
			{
				RulifierGUI rg = new RulifierGUI();
				rg.init();
			}
		}
		catch(ArrayIndexOutOfBoundsException e) // run gui if no arguments
		{
			RulifierGUI rg = new RulifierGUI();
			rg.init();
		}
		finally
		{
			//
		}
	}
}
