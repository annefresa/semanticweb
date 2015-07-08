package CreateOntologies;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import semweb.Resources;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.XSD;

public class OntologyBuilder {

	public Resources res = new Resources();

	public void createOntology(ArrayList<String[]> crimeData,
			ArrayList<String[]> locationRelationData,
			ArrayList<String[]> unemploymentData,
			ArrayList<String[]> populationData) {

		String uri = "http://www.imn.htwk-leipizg.de/~amatthes/semweb/schema#";

		OntModel model = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		model.setNsPrefix("am", uri);

		Resource crimeRes = model.createResource(uri + "Crime");
		model.add(crimeRes, RDF.type, RDFS.Class);
		Resource countyRes = model.createResource(uri + "County");
		model.add(countyRes, RDF.type, RDFS.Class);
		Resource dateRes = model.createResource(uri + "Date");
		model.add(dateRes, RDF.type, RDFS.Class);
		Resource regionRes = model.createResource(uri + "Region");
		model.add(regionRes, RDF.type, RDFS.Class);
		Resource unempRes = model.createResource(uri + "Unemployment");
		model.add(unempRes, RDF.type, RDFS.Class);
		Resource popRes = model.createResource(uri + "Population");
		model.add(popRes, RDF.type, RDFS.Class);

		Property hasCrimeCounty = model.createProperty(uri + "hasCrimeCounty");
		model.add(hasCrimeCounty, RDF.type, RDF.Property);
		model.add(hasCrimeCounty, RDFS.domain, crimeRes);
		model.add(hasCrimeCounty, RDFS.range, countyRes);

		Property hasCrimeDate = model.createProperty(uri + "hasCrimeDate");
		model.add(hasCrimeDate, RDF.type, RDF.Property);
		model.add(hasCrimeDate, RDFS.domain, crimeRes);
		model.add(hasCrimeDate, RDFS.range, dateRes);

		Property hasCrimeAmount = model.createProperty(uri + "hasCrimeAmount");
		model.add(hasCrimeAmount, RDF.type, RDF.Property);
		model.add(hasCrimeAmount, RDFS.domain, crimeRes);
		model.add(hasCrimeAmount, RDFS.range, XSD.xdouble);

		Property countyInRegion = model.createProperty(uri + "countyInRegion");
		model.add(countyInRegion, RDF.type, RDF.Property);
		model.add(countyInRegion, RDFS.domain, countyRes);
		model.add(countyInRegion, RDFS.range, regionRes);

		Property unempInRegion = model.createProperty(uri + "unempInRegion");
		model.add(unempInRegion, RDF.type, RDF.Property);
		model.add(unempInRegion, RDFS.domain, unempRes);
		model.add(unempInRegion, RDFS.range, regionRes);

		Property hasUnempDate = model.createProperty(uri + "hasUnempDate");
		model.add(hasUnempDate, RDF.type, RDF.Property);
		model.add(hasUnempDate, RDFS.domain, unempRes);
		model.add(hasUnempDate, RDFS.range, dateRes);

		Property hasUnempAmount = model.createProperty(uri + "hasUnempAmount");
		model.add(hasUnempAmount, RDF.type, RDF.Property);
		model.add(hasUnempAmount, RDFS.domain, unempRes);
		model.add(hasUnempAmount, RDFS.range, XSD.xdouble);

		Property hasPopCounty = model.createProperty(uri + "hasPopCounty");
		model.add(hasPopCounty, RDF.type, RDF.Property);
		model.add(hasPopCounty, RDFS.domain, popRes);
		model.add(hasPopCounty, RDFS.range, countyRes);

		Property hasPopDate = model.createProperty(uri + "hasPopDate");
		model.add(hasPopDate, RDF.type, RDF.Property);
		model.add(hasPopDate, RDFS.domain, popRes);
		model.add(hasPopDate, RDFS.range, dateRes);

		Property hasPopAmount = model.createProperty(uri + "hasPopAmount");
		model.add(hasPopAmount, RDF.type, RDF.Property);
		model.add(hasPopAmount, RDFS.domain, popRes);
		model.add(hasPopAmount, RDFS.range, XSD.xdouble);

		for (String[] entity : crimeData) {
			Resource crime = model.createResource(uri + "crime_" + entity[0]
					+ "_" + entity[1]);
			Resource county = model.createResource(uri + entity[0]);
			Resource date = model.createResource(uri + entity[1]);
			model.add(crime, hasCrimeCounty, county);
			model.add(crime, hasCrimeDate, date);
			model.add(crime, hasCrimeAmount,
					model.createTypedLiteral(Double.parseDouble(entity[2])));
			model.add(crime, RDF.type, crimeRes);
		}

		for (String[] entity : locationRelationData) {
			Resource county = model.createResource(uri + entity[0]);
			Resource region = model.createResource(uri + entity[1]);
			model.add(county, countyInRegion, region);
			model.add(region, RDF.type, regionRes);
			model.add(county, RDF.type, countyRes);
		}

		for (String[] entity : unemploymentData) {
			Resource unemployment = model.createResource(uri + "unemployment_"
					+ entity[0] + "_" + entity[1]);
			Resource region = model.createResource(uri + entity[0]);
			Resource date = model.createResource(uri + entity[1]);
			model.add(unemployment, unempInRegion, region);
			model.add(unemployment, hasUnempDate, date);
			model.add(unemployment, hasUnempAmount,
					model.createTypedLiteral(Double.parseDouble(entity[2])));
			model.add(unemployment, RDF.type, unempRes);
		}

		for (String[] entity : populationData) {
			Resource population = model.createResource(uri + "population_"
					+ entity[0] + "_" + entity[1]);
			Resource county = model.createResource(uri + entity[0]);
			Resource date = model.createResource(uri + entity[1]);
			model.add(population, hasPopCounty, county);
			model.add(population, hasPopDate, date);
			model.add(population, hasPopAmount,
					model.createTypedLiteral(Double.parseDouble(entity[2])));
			model.add(population, RDF.type, popRes);
		}

		try {
			model.write(new FileOutputStream(
					"/Users/anmt/Desktop/semwebOntology.rdf"));

			System.out.println("..LOG_"
					+ new Date(System.currentTimeMillis()).getHours() + ":"
					+ new Date(System.currentTimeMillis()).getMinutes() + ":"
					+ new Date(System.currentTimeMillis()).getSeconds()
					+ ": Creating semwebOntology finished.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
