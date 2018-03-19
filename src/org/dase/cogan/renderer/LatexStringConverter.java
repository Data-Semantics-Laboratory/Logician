package org.dase.cogan.renderer;

import org.dase.cogan.logic.Expression;

public class LatexStringConverter
{
	// @formatter:off
	/** AND. */         public static final String		AND			= "* ";
	/** OR. */          public static final String		OR			= "+ ";
	/** NOT. */ 	    public static final String		NOT			= "- ";
	/** ALL. */ 	    public static final String		ALL			= "/A";
	/** SOME. */        public static final String		SOME		= "/E";
	/** HASVALUE. */    public static final String		HASVALUE	= "hasValue ";
	/** MIN. */ 	    public static final String		MIN			= "\\geq";
	/** MAX. */ 		public static final String		MAX			= "\\leq";
	/** MINEX. */ 		public static final String		MINEX		= ">";
	/** MAXEX. */ 		public static final String		MAXEX		= "<";
	/** EQUAL. */ 		public static final String		EQUAL		= "=";
	/** SUBCLASS. */ 	public static final String		SUBCLASS	= "&\\sqsubseteq ";
	/** EQUIV. */ 		public static final String		EQUIV		= "&\\equiv ";
	/** NOT_EQUIV. */ 	public static final String		NOT_EQUIV	= "&\\not\\equiv ";
	/** TOP. */ 		public static final String		TOP			= "\\top ";
	/** BOTTOM. */ 		public static final String		BOTTOM		= "\\bot ";
	/** SELF. */ 		public static final String		SELF		= "\\textsf{Self} ";
	/** CIRC. */ 		public static final String		CIRC		= "\\circ ";
	/** INVERSE */ 		public static final String		INVERSE		= "^-";
	/** RARROW */		public static final String		RARROW		= ">";
	// @formatter:on
	
	public static String convertExpression(Expression expression)
	{
		return null;
	}
}
