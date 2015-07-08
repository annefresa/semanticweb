package LoadDataSets;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LoadLocationRelationData {

	public ArrayList<String[]> LocationData = new ArrayList<>();
	public String[] splittedHtml;
	public String[] splittedLine;
	public String County;
	public String Region;

	/**
	 * Laden des Datasets der Daten fuer die Zuordnung der Counties zu den
	 * Regions (HTML) 0: County 1: Region
	 * 
	 * @param strings
	 * @return
	 */
	public ArrayList<String[]> loadData(String[] res) {

		// England
		try {
			Document doc = Jsoup.connect(res[0]).get();
			splittedHtml = doc.body().toString().split("\n");

			for (int i = 131; i < 343; i++) { // TODO: dynamisch
				if (splittedHtml[i].contains("<h3>")) {
					Region = formRegionName(splittedHtml[i].substring(38, // TODO:
																			// dynamisch
							splittedHtml[i].indexOf("\">")));
				}

				if (splittedHtml[i].contains("<li>")) {
					County = formCountyName(splittedHtml[i].substring(
							splittedHtml[i].indexOf("\">") + 2,
							splittedHtml[i].indexOf("</a>")));

					String[] DataArray = new String[2];

					DataArray[0] = County;
					DataArray[1] = Region;

					LocationData.add(DataArray);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Wales TODO: erweiterbar

		// Scotland TODO: erweiterbar

		// North Ireland TODO: erweiterbar

		return LocationData;
	}

	/**
	 * Anpassen der Regionsbezeichnung (ohne "England" etc.)
	 * 
	 * @param Region
	 * @return
	 */
	public String formRegionName(String Region) {

		String[] splittedRegion = Region.split("_");
		Region = "";
		for (String string : splittedRegion) {
			if (!string.equals("Region") && !string.equals("England")) {
				Region = Region + " " + string;
			}
			if (string.equals("of")) {
				Region = Region + " England";
			}
		}

		StringBuilder sb = new StringBuilder(Region);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

		for (int j = 0; j < Region.length(); j++) {
			char c = sb.charAt(j);
			if (c == ' ') {
				sb.setCharAt((j), Character.toUpperCase('_'));
				sb.setCharAt((j + 1), Character.toUpperCase(sb.charAt(j + 1)));
			}
		}
		Region = sb.toString();

		Region = Region.substring(1, Region.length());

		return Region;
	}

	/**
	 * Anpassen der Countybezeichnung (Leerzeichen durch Unterstrich ersetzen
	 * und entfernen des Wortes "County" aus der Bezeichnung)
	 * 
	 * @param County
	 * @return
	 */
	public String formCountyName(String County) {
		boolean change = false;

		String[] splittedCounty = County.split(" ");
		County = "";
		for (String string : splittedCounty) {
			if (!string.equals("County") && !string.endsWith(" ")) {
				County = County + "_" + string;
			} else {
				change = true;
			}
		}

		if (change) {
			splittedCounty = null;
			splittedCounty = County.split(" ");
			County = "";
			for (String string : splittedCounty) {
				if (!string.equals("of")) {
					County = County + " " + string;
				}
			}
		}

		County = County.substring(1, County.length());

		return County;
	}
}
