package org.dase.cogan;

import org.dase.cogan.ui.RulifierGUI;
import org.dase.cogan.ui.RulifierREPL;

public class Main
{
	public static void main(String[] args)
	{
		String flag = args[0];

		if(flag.equals("-d"))
		{

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
		else
		{
			RulifierGUI rg = new RulifierGUI();
			rg.init();
		}
	}
}
