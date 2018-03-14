package org.dase.cogan.logic;

import org.dase.cogan.operation.Node;

public class Expression
{
	private Node root;
	
	public Expression(Node root)
	{
		this.root = root;
	}
	
	public Expression NNF()
	{
		// TODO
		return null;
	}
	
	public String toString()
	{
		return root.toString();
	}
}
