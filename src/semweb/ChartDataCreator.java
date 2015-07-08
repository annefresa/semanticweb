package semweb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Erstellen eines angepassten CSV-Files fuer die Visualisierung der Daten.
 * 
 * fuer https://www.meta-chart.com/scatter-plot oder
 * http://www.livephysics.com/tools/mathematical-tools/calculate-linear-
 * regression-graph-scatter-plot-line-fit/
 * 
 * @author anmt
 *
 */
public class ChartDataCreator {

	public static ArrayList<String[]> Data = new ArrayList<>();
	public static ArrayList<String> rawData = new ArrayList<>();

	/**
	 * Erstellen eines angepassten CSV-Files fuer die Visualisierung der Daten.
	 */
	public void createChartData() {
		String inputFile = "/Users/anmt/Desktop/queryResults.xls";// System.getProperty("user.dir")
																	// +
																	// "/src/queryResults.xls";
		String outputFile = "/Users/anmt/Desktop/convQueryResults.csv";

		ChartDataCreator cdc = new ChartDataCreator();
		rawData = cdc.readCSV(inputFile);
		Data = cdc.convertData(rawData);

		cdc.writeCSV(inputFile, Data, outputFile);
	}

	public ArrayList<String> readCSV(String file) {
		File inputWorkbook = new File(file);
		Workbook w;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);

			for (int i = 1; i < sheet.getRows(); i++) {
				rawData.add(sheet.getCell(0, i).getContents());
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rawData;
	}

	public ArrayList<String[]> convertData(ArrayList<String> rawData) {

		String helpString;
		int exp;

		for (int i = 0; i < rawData.size(); i++) {

			String[] splittedData = rawData.get(i).split(",");

			splittedData[0] = splittedData[0].replace("\"", "");
			splittedData[0] = splittedData[0].replace(" ", "");
			splittedData[1] = splittedData[1].replace("\"", "");
			splittedData[1] = splittedData[1].replace(" ", "");

			if (splittedData[0].contains("E") || splittedData[0].contains("e")) {
				helpString = splittedData[0];
				exp = Integer.parseInt(helpString.substring(
						helpString.indexOf('E') + 1,
						helpString.indexOf('E') + 2));

				helpString = helpString.substring(0, helpString.indexOf('E'));

				for (int j = 0; j < exp; j++) {
					helpString = helpString + "0";
				}

				splittedData[0] = helpString;
			}

			if (splittedData[1].contains("E") || splittedData[1].contains("e")) {
				helpString = splittedData[1];
				exp = Integer.parseInt(helpString.substring(
						helpString.indexOf('E') + 1,
						helpString.indexOf('E') + 2));

				helpString = helpString.substring(0, helpString.indexOf('E'));

				for (int j = 0; j < exp; j++) {
					helpString = helpString + "0";
				}

				splittedData[1] = helpString;
			}

			String[] DataArray = new String[2];
			DataArray[0] = splittedData[0];
			DataArray[1] = splittedData[1];
			Data.add(DataArray);
		}

		return Data;
	}

	public void writeCSV(String file, ArrayList<String[]> Data,
			String outputFile) {
		try {
			FileWriter writer = new FileWriter(outputFile);

			for (String[] entity : Data) {
				writer.append(entity[0]);
				writer.append(',');
				writer.append(entity[1]);
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
