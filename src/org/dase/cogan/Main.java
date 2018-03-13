package org.dase.cogan;

import org.dase.cogan.ui.RulifierGUI;

public class Main
{
	public static void main(String[] args)
	{
		String flag = args[1];
		
		if(flag.equals("-d"))
		{
			
		}
		else if(flag.equals("-t"))
		{
			test();
		}
		else
		{
			RulifierGUI rg = new RulifierGUI();
			rg.init();
		}
	}
	
	public static void test()
	{
		
	}
}
