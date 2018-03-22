package org.dase.cogan.renderer;

import static org.semanticweb.owlapi.util.OWLAPIPreconditions.verifyNotNull;
import static org.semanticweb.owlapi.util.OWLAPIStreamUtils.asList;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.dase.cogan.ingestion.RuleObjectVisitor;
import org.dase.cogan.ingestion.StringIngestor;
import org.dase.cogan.logic.CannotConvertToRuleException;
import org.dase.cogan.logic.Expression;
import org.dase.cogan.logic.Rule;
import org.semanticweb.owlapi.io.AbstractOWLRenderer;
import org.semanticweb.owlapi.io.OWLRendererException;
import org.semanticweb.owlapi.latex.renderer.LatexRendererIOException;
import org.semanticweb.owlapi.latex.renderer.LatexWriter;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLRuntimeException;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.util.OWLEntityComparator;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

public class RuleRenderer extends AbstractOWLRenderer
{

	private final ShortFormProvider		shortFormProvider	= new SimpleShortFormProvider();
	private final OWLEntityComparator	entityComparator	= new OWLEntityComparator(shortFormProvider);

	@Override
	public void render(OWLOntology o, PrintWriter _w) throws OWLRendererException
	{
		try
		{
			// Wrap the printwriter with a latex writer
			LatexWriter w = new LatexWriter(_w);

			/* Begin preamble */
			w.write("\\documentclass{article}\n");
			w.write("\\usepackage[fleqn]{amsmath}\n"); // amsmath must be first.
			w.write("\\parskip 0pt\n");
			w.write("\\parindent 0pt\n");
			w.write("\\oddsidemargin 0cm\n");
			w.write("\\textwidth 19cm\n");
			w.write("\\begin{document}\n\n");
			/* End preamble */

			// TODO: implement fopl -> rule conversion
			// At this point, the renderer will create fopl strings.
			// 1. We need to convert them to rules
			// 2. Convert them to latex strings
			StringWriter stringWriter = new StringWriter();
			RuleObjectVisitor renderer = new RuleObjectVisitor(stringWriter,
			        o.getOWLOntologyManager().getOWLDataFactory());

			w.write("\\subsection*{Classes}\n");
			sortEntities(o.classesInSignature()).forEach(cls -> {
				writeEntities(stringWriter, w, renderer, cls, sortAxioms(o.axioms(cls)));
			});

			w.write("\\section*{Object properties}\n");
			sortEntities(o.objectPropertiesInSignature()).forEach(p -> {
			writeEntities(stringWriter, w, renderer, p, sortAxioms(o.axioms(p))); });
			
			w.write("\\section*{Data properties}\n");
			o.dataPropertiesInSignature().sorted(entityComparator).forEach(
			prop -> { writeEntities(stringWriter, w, renderer, prop,
			sortAxioms(o.axioms(prop))); });
			
			w.write("\\section*{Individuals}\n");
			o.individualsInSignature().sorted(entityComparator).forEach(i ->
			{ writeEntities(stringWriter, w, renderer, i, sortAxioms(o.axioms(i))); });
			
			w.write("\\section*{Datatypes}\n");
			o.datatypesInSignature().sorted(entityComparator).forEach(type ->
			{ writeEntities(stringWriter, w, renderer, type, sortAxioms(o.axioms(type,
			Imports.EXCLUDED))); });

			w.write("\\end{document}\n");
			w.flush();
		}
		catch(OWLRuntimeException e)
		{
			throw new LatexRendererIOException(e);
		}
	}

	private void writeEntitySection(OWLEntity entity, LatexWriter w)
	{
		w.write("\\subsubsection*{");
		w.write(escapeName(shortFormProvider.getShortForm(entity)));
		w.write("}\n");
	}

	private void writeEntities(StringWriter sw, LatexWriter w, RuleObjectVisitor renderer, OWLEntity cls,
	        Collection<? extends OWLAxiom> axioms)
	{
		// Write the header to the subsection
		writeEntitySection(cls, w);
		// Align over subclass and equivalent
		if(axioms.size() > 0)
		{
			// Enter align* environment
			w.write("\\begin{align*}\n");
			// Write entity axioms
			for(Iterator<? extends OWLAxiom> it = axioms.iterator(); it.hasNext();)
			{
				OWLAxiom axiom = it.next();
				System.out.println("Parsing: " + axiom.toString());
				renderer.reset();
				axiom.accept(renderer);

				// Get string from stream
				String foplString = sw.toString();
				sw.getBuffer().setLength(0); // this does what .flush would do
				
				if(StringIngestor.isMultiexpression(foplString))
				{
					String[] exprStrings = foplString.split("\\|");
					for(String exprString : exprStrings)
					{
						writeRule(exprString, w, it);
					}
				}
				else
				{
					writeRule(foplString, w, it);
				}
			}
			w.write("\\end{align*}\n\n");
		}
	}

	public void writeRule(String foplString, LatexWriter w, Iterator<?extends OWLAxiom> it)
	{
		System.out.println("\t"+foplString);
		// Construct an expression from the rule
		Expression expr = StringIngestor.ingest(foplString);
		try
		{
			// Convert the Expression to a Rule
			Rule rule = expr.toRule();
			String ruleString = rule.toLatexString();
			// Write the rule to the latex document
			w.write(ruleString);
			if(it.hasNext())
			{
				w.write("\\\\");
			}
			w.write("\n");
		}
		catch(CannotConvertToRuleException e)
		{
			System.out.println(foplString);
			System.out.println("The expression " + expr + "could not be converted to a rule.");
		}
	}
	
	/** Sorts entities alphabetically */
	private <T extends OWLEntity> Collection<T> sortEntities(Stream<T> entities)
	{
		return asList(entities.sorted(entityComparator));
	}

	/** sorts axioms alphabetically */
	private static Collection<? extends OWLAxiom> sortAxioms(Stream<? extends OWLAxiom> axioms)
	{
		return asList(axioms.sorted(new OWLAxiomComparator()));
	}

	/** escapes _ and # for latex compilation */
	private static String escapeName(String name)
	{
		return name.replace("_", "\\_").replace("#", "\\#");
	}

	/** for use in comparing axioms */
	private static class OWLAxiomComparator implements Comparator<OWLAxiom>, Serializable
	{
		/** bookkeeping */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(@Nullable OWLAxiom o1, @Nullable OWLAxiom o2)
		{
			int index1 = verifyNotNull(o1).getAxiomType().getIndex();
			int index2 = verifyNotNull(o2).getAxiomType().getIndex();
			return index1 - index2;
		}
	}
}
