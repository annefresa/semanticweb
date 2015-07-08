package semweb;

public class Resources {

	/**
	 * 
	 * Quelle: http://data.police.uk/data/
	 * 
	 * CSV-Formate in komplexer Ordnerstruktur
	 * 
	 * @return
	 */
	public String getCrimeDataResource() {
		String CrimeDataResource = "src/data/crimedata";
		return CrimeDataResource;
	}

	public String getCrimeDataOriginalLink() {
		String CrimeDataResource = "/data.police.uk/data";
		return CrimeDataResource;
	}

	/**
	 * Quellen:
	 * 
	 * https://de.wikipedia.org/wiki/Verwaltungsgliederung_Englands
	 * 
	 * https://de.wikipedia.org/wiki/Verwaltungsgliederung_von_Wales
	 * 
	 * https://de.wikipedia.org/wiki/Verwaltungsgliederung_Schottlands
	 * 
	 * https://de.wikipedia.org/wiki/Verwaltungsgliederung_Nordirlands
	 * 
	 * Daten aus Browser (HTML)
	 * 
	 * @return
	 */
	public String[] getLocationRelationDataResource() {
		String EnglandLocationRelationDataResource = "https://de.wikipedia.org/wiki/Verwaltungsgliederung_Englands";
		String WalesLocationRelationDataResource = "https://de.wikipedia.org/wiki/Verwaltungsgliederung_von_Wales";
		String ScotlandLocationRelationDataResource = "https://de.wikipedia.org/wiki/Verwaltungsgliederung_Schottlands";
		String NorthIrelandLocationRelationDataResource = "https://de.wikipedia.org/wiki/Verwaltungsgliederung_Nordirlands";

		String[] LocationRelationDataResource = {
				EnglandLocationRelationDataResource,
				WalesLocationRelationDataResource,
				ScotlandLocationRelationDataResource,
				NorthIrelandLocationRelationDataResource };

		return LocationRelationDataResource;
	}

	public String[] getLocationRelationDataOriginalLink() {
		String EnglandLocationRelationDataResource = "/de.wikipedia.org/wiki/Verwaltungsgliederung_Englands";
		String WalesLocationRelationDataResource = "/de.wikipedia.org/wiki/Verwaltungsgliederung_von_Wales";
		String ScotlandLocationRelationDataResource = "/de.wikipedia.org/wiki/Verwaltungsgliederung_Schottlands";
		String NorthIrelandLocationRelationDataResource = "/de.wikipedia.org/wiki/Verwaltungsgliederung_Nordirlands";

		String[] LocationRelationDataOriginalLink = {
				EnglandLocationRelationDataResource,
				WalesLocationRelationDataResource,
				ScotlandLocationRelationDataResource,
				NorthIrelandLocationRelationDataResource };

		return LocationRelationDataOriginalLink;
	}

	/**
	 * Quelle:
	 * http://data.london.gov.uk/dataset/unemployment-rate-region/resource
	 * /8a29ec0c-9de3-4777-832f-49ef8c2b4d14
	 * 
	 * XLS-Format
	 * 
	 * @return
	 */
	public String getUnemploymentDataResource() {
		String UnemploymentDataResource = System.getProperty("user.dir")
				+ "/src/data/unemployment-region.xls";
		return UnemploymentDataResource;
	}

	public String getUnemploymentDataOriginalLink() {
		String UnemploymentDataResource = "/data.london.gov.uk/dataset/unemployment-rate-region/resource/8a29ec0c-9de3-4777-832f-49ef8c2b4d14";
		return UnemploymentDataResource;
	}

	/**
	 * Quelle: https://www.nomisweb.co.uk/query/advanced.aspx?resume=yes, API
	 * leider nicht mehr vorhanden
	 * 
	 * XLSX-Format
	 * 
	 * @return
	 */
	public String getPopulationDataResource() {
		String PopulationDataResource = System.getProperty("user.dir")
				+ "/src/data/nomis_2015_07_03_191012.xls";
		return PopulationDataResource;
	}

	public String getPopulationDataOriginalLink() {
		String PopulationDataResource = "/nomisweb.co.uk/query";
		return PopulationDataResource;
	}

}
