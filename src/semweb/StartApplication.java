package semweb;

/**
 * 
 * Das Programm ist im Rahmen des Moduls "Semantic Web" an der HTWK Leipzig entstanden. 
 * Ziel war es, aus oeffentlichen Datenquellen Daten zu exportieren und als Triples in
 * ein geeignetes Format zu uebertragen (z.B. hier: RDF mithilfe des Jena-Frameworks von 
 * Apache). Anschliessend sollen die Daten in einen Triple Store (hier: Fuseki) 
 * importiert und per SPARQL ausgewertet werden.
 * 
 * Die Fragestellung fuer den konkreten Fall lautet:
 * Einfl�sse auf die Kriminalit�t in britischen Gro�st�dten
 * Welche sozialen und lokalen Kriterien nehmen Einfluss auf die H�ufigkeit von 
 * Kriminalit�tnin britischen St�dten oder Regionen? Untersucht werden sollen dabei
 * verschiedene Orte (St�dte, Regionen). Es ist zu �berpr�fen, ob eine Abh�ngigkeit 
 * zur Jahreszeit, Arbeitslosigkeit und Population besteht und wie sich diese �ber 
 * einem bestimmten Zeitraum entwickelt und ver�ndert hat. M�glichst viele Kriterien 
 * sollen im Umfang der Arbeit umgesetzt werden. (Fokus: Britische St�dte)
 * 
 * Zielerfuellung (selbst gesetzt):
 * Im Rahmen des Projekts war es aus zeitlichen Gruenden nur moeglich, die Staedte Englands
 * zu untersuchen. Schnittstellen fuer die Erweiterungen f�r Wales, North Ireland und Scotland
 * sind aber bereits vorhanden und koennen jederzeit nachgetragen werden.
 * Auf monatsbasis wurden pro County Kriminalitaetsvorkommen, Bevoelkerung und Arbeitslosikgeit
 * untersucht. Analysen von Abhaengigkeiten zur Jahreszeit koennen ebenfalls vorgenommen werden. * 
 * 
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
