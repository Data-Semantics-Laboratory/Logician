package org.dase.cogan.operations;

import java.util.List;

/**
 * This method creates a disjunctive chain, reification from n-ary disjunction
 * to binary disjunction
 * 
 * @author Cogs
 *
 */
public class DisjunctionMaker
{
	public static Node makeDisjunctionChain(List<Node> nodes)
	{
		return makeDisjunctionChainHelper(nodes);
	}

	private static Node makeDisjunctionChainHelper(List<Node> nodes)
	{
		Node disjunction = null; // to be returned
		Node left = nodes.remove(0);
		Node right = null; // to be set
		// Recurse if necessary
		if(nodes.size() == 1)
		{
			right = nodes.remove(0);
		}
		else
		{
			right = makeDisjunctionChainHelper(nodes);
		}
		// Construct
		disjunction = new Disjunction(left, right);
		// Done!
		return disjunction;

	}
}
