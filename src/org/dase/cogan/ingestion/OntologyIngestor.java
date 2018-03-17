package org.dase.cogan.ingestion;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OntologyIngestor
{
	public static void ingest(String filename)
	{
		try
		{
			OWLConnector owlConnector = new OWLConnector(filename);
			
		}
		catch(OWLOntologyCreationException e)
		{
			System.out.println("Could not load ontology.");
		}
	}
	
}
