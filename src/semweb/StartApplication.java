package semweb;

/**
 * 
 * Das Programm ist im Rahmen des Moduls "Semantic Web" an der HTWK Leipzig entstanden. 
 * Ziel war es, aus oeffentlichen Datenquellen Daten zu exportieren und als Triples in
 * ein geeignetes Format zu uebertragen (z.B. hier: RDF mithilfe des Jena-Frameworks von 
 * Apache). Anschliessend sollen die Daten in einen Triple Store (hier: Fuseki) 
 * importiert und per SPARQL ausgewertet werden.
 * @author anmt
 * 
 */

import java.util.ArrayList;
import java.util.Date;

import CreateOntologies.OntologyBuilder;
import LoadDataSets.DataLoader;

public class StartApplication {

	public static ArrayList<String[]> CrimeData = new ArrayList<>();
	public static ArrayList<String[]> LocationRelationData = new ArrayList<>();
	public static ArrayList<String[]> UnemploymentData = new ArrayList<>();
	public static ArrayList<String[]> PopulationData = new ArrayList<>();

	public static void main(String[] args) {

		System.out.println("LOG_"
				+ new Date(System.currentTimeMillis()).getHours() + ":"
				+ new Date(System.currentTimeMillis()).getMinutes() + ":"
				+ new Date(System.currentTimeMillis()).getSeconds()
				+ ": Start Application...");

		DataLoader data = new DataLoader();
		CrimeData = data.loadCrimeData();
		LocationRelationData = data.loadLocationRelationData();
		UnemploymentData = data.loadUnemploymentData();
		PopulationData = data.loadPopulationData();

		OntologyBuilder ontBuilder = new OntologyBuilder();
		ontBuilder.createOntology(CrimeData, LocationRelationData,
				UnemploymentData, PopulationData);

		// ChartDataCreator chart = new ChartDataCreator();
		// chart.createChartData();

		System.out.println("LOG_"
				+ new Date(System.currentTimeMillis()).getHours() + ":"
				+ new Date(System.currentTimeMillis()).getMinutes() + ":"
				+ new Date(System.currentTimeMillis()).getSeconds()
				+ ": Finished.");
	}
}
