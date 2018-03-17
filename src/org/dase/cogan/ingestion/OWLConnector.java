package org.dase.cogan.ingestion;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.MissingImportHandlingStrategy;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class OWLConnector
{
	private OWLOntology			ontology;
	private OWLDataFactory		dataFactory;

	public OWLConnector(String filename) throws OWLOntologyCreationException
	{
		// Get an ontology manager and datafactory
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		this.dataFactory = manager.getOWLDataFactory();
		// Force silent import errors
		manager.setOntologyLoaderConfiguration(manager.getOntologyLoaderConfiguration()
		        .setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT));
		// Create a file from the name
		File file = new File(filename);
		IRI iri = IRI.create(file.toURI());
		// Load Ontology
		this.ontology = manager.loadOntologyFromOntologyDocument(iri);
	}
	
	public OWLOntology getOntology()
	{
		return ontology;
	}

	public OWLDataFactory getDataFactory()
	{
		return dataFactory;
	}

	public void setOntology(OWLOntology ontology)
	{
		this.ontology = ontology;
	}

	public void setDataFactory(OWLDataFactory dataFactory)
	{
		this.dataFactory = dataFactory;
	}
}
