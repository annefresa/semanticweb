package LoadDataSets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoadPopulationData {

	public ArrayList<String[]> PopulationData = new ArrayList<>();
	public String[] splittedString;
	public String Location;
	public String Date;
	public String PopulationNumber;

	/**
	 * Laden des Datasets der Daten der Bevoelkerung (XSL) 0: Location 1: Date
	 * 2: Number Of Population 3: Location Type
	 * 
	 * Information: Es werden nur Daten mit LocationType 'uacounty' uebergeben
	 * 
	 * @param res
	 * @return
	 */
	public ArrayList<String[]> loadData(String res) {
		File inputWorkbook = new File(res);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);

			for (int i = 8; i < sheet.getRows() - 2; i++) {
				Location = sheet.getCell(0, i).getContents().split(":")[1];

				for (int j = 2; j < sheet.getColumns(); j += 2) {

					if (sheet.getCell(j, i).getContents().equals("-"))
						continue;

					for (int k = 1; k < 13; k++) {
						if (k < 10) {
							Date = sheet.getCell(j, 6).getContents() + "-0" + k;
						} else {
							Date = sheet.getCell(j, 6).getContents() + "-" + k;
						}

						PopulationNumber = sheet.getCell(j, i).getContents();

						String[] DataArray = new String[4];

						DataArray[0] = formatLocation(Location);
						DataArray[1] = Date;
						DataArray[2] = formatNumbers(PopulationNumber);
						DataArray[3] = getLocationType(sheet.getCell(0, i)
								.getContents().split(":")[0].substring(0, 3));

						if (DataArray[3].equals("uacounty"))
							PopulationData.add(DataArray);
					}
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return PopulationData;
	}

	/**
	 * Entfernen der Namenszusaetze (South, West etc. zum besseren Matching)
	 * 
	 * @param Location
	 * @return
	 */
	public String getLocation(String Location) {

		if (Location.contains("South ")) {
			Location = Location.substring(6, Location.length());
		}
		if (Location.contains("West ")) {
			Location = Location.substring(5, Location.length());
		}
		if (Location.contains("East ")) {
			Location = Location.substring(5, Location.length());
		}
		if (Location.contains("North ")) {
			Location = Location.substring(6, Location.length());
		}

		return Location;
	}

	/**
	 * Erfassen des LocationTypes, da nur Counties von Interesse sind.
	 * 
	 * @param LocationType
	 * @return
	 */
	public String getLocationType(String LocationType) {
		if (LocationType.equals("cou"))
			LocationType = "country";
		else if (LocationType.equals("uac"))
			LocationType = "uacounty";
		else if (LocationType.equals("ual"))
			LocationType = "ualad";
		else if (LocationType.equals("lep"))
			LocationType = "lep";
		else if (LocationType.equals("mco"))
			LocationType = "mcounty";
		else if (LocationType.equals("nil"))
			LocationType = "nilgd";
		else if (LocationType.equals("gor"))
			LocationType = "gor";
		else {
			LocationType = "unknownLocationType";
		}
		return LocationType;
	}

	/**
	 * Entfernen der Punkte aus den Zahlen zur besseren weiteren Verarbeitung
	 * (Umwandlund in double und Visualisierung)
	 * 
	 * @param number
	 * @return
	 */
	public String formatNumbers(String number) {

		if (number.contains(".")) {
			String[] split = number.split("\\.");
			number = "";
			for (int i = 0; i < split.length; i++) {
				number = number + split[i];
			}
		}

		return number;
	}

	/**
	 * Anpassen der Location-Bezeichnung (Ersetzen der Leerzeichen durch
	 * Unterstriche)
	 * 
	 * @param Location
	 * @return
	 */
	public String formatLocation(String Location) {
		StringBuilder sb = new StringBuilder(Location);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

		for (int j = 0; j < Location.length(); j++) {
			char c = sb.charAt(j);
			if (c == ' ') {
				sb.setCharAt((j), '_');
			}
		}
		Location = sb.toString();

		return Location;
	}

}
