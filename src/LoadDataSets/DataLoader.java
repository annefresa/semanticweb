package LoadDataSets;

import java.util.ArrayList;
import java.util.Date;

import semweb.Resources;

public class DataLoader {

	public ArrayList<String[]> CrimeData = new ArrayList<>();
	public ArrayList<String[]> LocationRelationData = new ArrayList<>();
	public ArrayList<String[]> UnemploymentData = new ArrayList<>();
	public ArrayList<String[]> PopulationData = new ArrayList<>();
	public Resources res = new Resources();

	/**
	 * 0:Location, 1:Datum 2:Number
	 * 
	 * @return
	 */
	public ArrayList<String[]> loadCrimeData() {
		LoadCrimeData loadCrimeData = new LoadCrimeData();
		CrimeData = loadCrimeData.loadData(res.getCrimeDataResource());

		System.out.println("..LOG_"
				+ new Date(System.currentTimeMillis()).getHours() + ":"
				+ new Date(System.currentTimeMillis()).getMinutes() + ":"
				+ new Date(System.currentTimeMillis()).getSeconds()
				+ ": Loading CrimeData finished.");

		return CrimeData;
	}

	/**
	 * 0:City, 1:Region
	 * 
	 * @return
	 */
	public ArrayList<String[]> loadLocationRelationData() {
		LoadLocationRelationData loadLocationRelationData = new LoadLocationRelationData();
		LocationRelationData = loadLocationRelationData.loadData(res
				.getLocationRelationDataResource());

		System.out.println("..LOG_"
				+ new Date(System.currentTimeMillis()).getHours() + ":"
				+ new Date(System.currentTimeMillis()).getMinutes() + ":"
				+ new Date(System.currentTimeMillis()).getSeconds()
				+ ": Loading RegionCityRelationData finished.");

		return LocationRelationData;
	}

	/**
	 * 0:Region, 1:Date, 2:Number of Unemployment
	 * 
	 * @return
	 */
	public ArrayList<String[]> loadUnemploymentData() {
		LoadUnemploymentData loadUnemploymentData = new LoadUnemploymentData();
		UnemploymentData = loadUnemploymentData.loadData(res
				.getUnemploymentDataResource());

		System.out.println("..LOG_"
				+ new Date(System.currentTimeMillis()).getHours() + ":"
				+ new Date(System.currentTimeMillis()).getMinutes() + ":"
				+ new Date(System.currentTimeMillis()).getSeconds()
				+ ": Loading UnemploymentData finished.");

		return UnemploymentData;
	}

	/**
	 * 0:Location, 1:Date, 2:Number of Population, 3:Location Type
	 * 
	 * Information: Es werden nur Daten mit LocationType 'uacounty' uebergeben
	 * 
	 * @return
	 */
	public ArrayList<String[]> loadPopulationData() {
		LoadPopulationData loadPopulationData = new LoadPopulationData();
		PopulationData = loadPopulationData.loadData(res
				.getPopulationDataResource());

		System.out.println("..LOG_"
				+ new Date(System.currentTimeMillis()).getHours() + ":"
				+ new Date(System.currentTimeMillis()).getMinutes() + ":"
				+ new Date(System.currentTimeMillis()).getSeconds()
				+ ": Loading PopulationData finished.");

		return PopulationData;
	}
}
