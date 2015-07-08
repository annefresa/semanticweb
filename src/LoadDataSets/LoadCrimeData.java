package LoadDataSets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class LoadCrimeData {

	public ArrayList<String[]> CrimeData = new ArrayList<>();

	/**
	 * 
	 * 0: Location
	 * 
	 * 1: Date
	 * 
	 * 2: Number Of Crime
	 * 
	 * @param res
	 * @return
	 */
	public ArrayList<String[]> loadData(String res) {

		File d = new File(res);
		File[] dirArray = d.listFiles();

		for (File dir : dirArray) {
			File f = new File(dir.toString());
			File[] fileArray = f.listFiles();

			for (File file : fileArray) {
				String[] DataArray = new String[3];

				DataArray[0] = getLocation(file);
				DataArray[1] = getDate(file);
				DataArray[2] = getCrimeNumber(file);

				CrimeData.add(DataArray);
			}
		}

		return CrimeData;
	}

	public String getLocation(File file) {
		String Location = file.toString().substring(35,
				file.toString().length() - 11);

		StringBuilder sb = new StringBuilder(Location);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

		for (int j = 0; j < Location.length(); j++) {
			char c = sb.charAt(j);
			if (c == '-') {
				sb.setCharAt((j), Character.toUpperCase('_'));
				sb.setCharAt((j + 1), Character.toUpperCase(sb.charAt(j + 1)));
			}
		}
		Location = sb.toString();

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

	public String getDate(File file) {
		String Date = file.toString().substring(19, 26);
		return Date;
	}

	public String getCrimeNumber(File file) {
		String CrimeNumber = null;
		int ctr = 0;

		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			try {
				while (reader.readNext() != null) {
					ctr++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		CrimeNumber = String.valueOf(ctr - 2);
		return CrimeNumber;
	}
}
