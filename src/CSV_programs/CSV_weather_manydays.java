package CSV_programs;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class CSV_weather_manydays {

	public CSVRecord hottestHourInFile(CSVParser parser) {
		// start with largestSoFar as nothing
		CSVRecord largestSofar = null;
		double count = 0;
		double total = 0;
		// For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			if (largestSofar == null) {
				largestSofar = currentRow;

			} else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSofar.get("TemperatureF"));

				if (currentTemp > largestTemp) {
					largestSofar = currentRow;
				}

			}
			double humi = Double.parseDouble(currentRow.get("Humidity"));
			if (humi >= 80) {

				total = Double.parseDouble(currentRow.get("TemperatureF"));

				count++;
			}
		}
		double ans = total / count;
		System.out.println("avg" + ans);
		return largestSofar;
	}

	public CSVRecord coldestHourInFile(CSVParser parser) {
		// start with largestSoFar as nothing
		CSVRecord smallestSofar = null;
		double smallestTemp = 0;
		// For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			if (smallestSofar == null) {
				smallestSofar = currentRow;
			} else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				smallestTemp = Double.parseDouble(smallestSofar.get("TemperatureF"));

				if (currentTemp < smallestTemp) {
					smallestSofar = currentRow;

				}
			}
		}
		System.out.println(smallestTemp);
		return smallestSofar;
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser) {

		String time = null;
		double smallestHum = 0;
		// start with largestSoFar as nothing
		CSVRecord smallestSofar = null;
		// For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			if (currentRow.get("Humidity").equals("N/A")) {
				continue;
			} else {
				if (smallestSofar == null) {
					smallestSofar = currentRow;
				} else {
					double currentHum = Double.parseDouble(currentRow.get("Humidity"));
					smallestHum = Double.parseDouble(smallestSofar.get("Humidity"));

					if (currentHum < smallestHum) {
						smallestSofar = currentRow;
						time = currentRow.get("DateUTC");

					}
				}
			}
		}
		System.out.println(smallestHum + "" + time);
		return smallestSofar;
	}

	public void listFiles(String folder) {

		double coolest = 100;
		String time = null;

		File directory = new File(folder);
		File[] contents = directory.listFiles();
		for (File f : contents) {

			FileResource resource = new FileResource(f);
			CSVParser parser = resource.getCSVParser();

			CSVRecord data = coldestHourInFile(parser);
			double temp = Double.parseDouble(data.get("TemperatureF"));
			if (coolest > temp) {
				coolest = Double.parseDouble(data.get("TemperatureF"));
				time = data.get("DateUTC");
			}

		}
		System.out.println("coolest humi " + coolest + " time " + time);
	}

	public static void main(String[] args) {

		CSV_weather_manydays weather_manydays = new CSV_weather_manydays();

		FileResource resource = new FileResource(
				new File("C:\\Users\\BANSHITA\\Downloads\\nc_weather\\2013\\weather-2013-09-02.csv"));
		CSVParser parser = resource.getCSVParser();

		//CSVRecord largest = weather_manydays.hottestHourInFile(parser);
		//System.out.println("lowest "+largest.get("TemperatureF"));
		// System.out.println("hottest temperature was " + largest.get("TemperatureF") +
		// " at " + largest.get("TimeEST"));
		// CSVRecord coldest = weather_manydays.lowestHumidityInFile(parser);
		// System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
		// " at " + coldest.get("DateUTC"));

		// weather_manydays.listFiles("C:\\Users\\BANSHITA\\Downloads\\nc_weather\\2013");
		
		int arr[]=new int[5];
		System.out.println(arr);
	}

}
