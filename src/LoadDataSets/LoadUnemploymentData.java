package LoadDataSets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoadUnemploymentData {

	public ArrayList<String[]> UnemploymentData = new ArrayList<>();
	public String[] splittedString;
	public String Region;
	public String Date;
	public String UnemploymentNumber;

	/**
	 * Laden des Datasets der Daten der Arbeitslosigkeit (XSL) 0: Region 1: Date
	 * 2: Number Of Unemployment
	 * 
	 * @param res
	 * @return
	 */
	public ArrayList<String[]> loadData(String res) {
		File inputWorkbook = new File(res);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(1);

			for (int i = 2; i < 16; i++) {
				Region = sheet.getCell(0, i).getContents();

				for (int j = 1; j < sheet.getColumns() - 2; j++) {

					Date = sheet.getCell(j, 0).getContents();
					UnemploymentNumber = sheet.getCell(j, i).getContents();

					String[] DataArray = new String[3];

					DataArray[0] = formatRegion(Region);
					DataArray[1] = convertDate(Date);
					DataArray[2] = formatNumbers(UnemploymentNumber);

					UnemploymentData.add(DataArray);
				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return UnemploymentData;
	}

	/**
	 * Anpassen der Regionsbezeichnungen (Ersetzen der Leerzeichen durch
	 * Unterstriche)
	 * 
	 * @param Region
	 * @return
	 */
	public String formatRegion(String Region) {
		StringBuilder sb = new StringBuilder(Region);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

		for (int j = 0; j < Region.length(); j++) {
			char c = sb.charAt(j);
			if (c == ' ') {
				sb.setCharAt((j), '_');
			}
		}
		Region = sb.toString();

		return Region;
	}

	/**
	 * 
	 * Wandelt Datum (z.B. Apr-12) in YYYY-MM um
	 * 
	 * @param date
	 * @return
	 */
	public String convertDate(String date) {

		splittedString = date.split("-");

		switch (splittedString[0]) {
		case "Jan":
			splittedString[0] = "01";
			break;
		case "Feb":
			splittedString[0] = "02";
			break;
		case "Mär":
			splittedString[0] = "03";
			break;
		case "Apr":
			splittedString[0] = "04";
			break;
		case "Mai":
			splittedString[0] = "05";
			break;
		case "Jun":
			splittedString[0] = "06";
			break;
		case "Jul":
			splittedString[0] = "07";
			break;
		case "Aug":
			splittedString[0] = "08";
			break;
		case "Sep":
			splittedString[0] = "09";
			break;
		case "Okt":
			splittedString[0] = "10";
			break;
		case "Nov":
			splittedString[0] = "11";
			break;
		case "Dez":
			splittedString[0] = "12";
			break;
		default:
			System.out
					.println("Datum nicht erkannt. [LoadUnemploymentData.java - convertData]");
		}

		date = "20" + splittedString[1] + "-" + splittedString[0];

		return date;
	}

	/**
	 * Entfernen der Kommas aus den Zahlen zur besseren weiteren Verarbeitung
	 * (Umwandlund in double und Visualisierung)
	 * 
	 * @param number
	 * @return
	 */
	public String formatNumbers(String number) {

		if (number.contains(",")) {
			String[] split = number.split(",");
			number = "";
			number = split[0] + split[1];
		}

		return number;
	}
}
