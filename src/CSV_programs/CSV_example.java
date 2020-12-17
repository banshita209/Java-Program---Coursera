package CSV_programs;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class CSV_example {

	void readFood() {
		FileResource fileResource = new FileResource(new File("C:\\Users\\BANSHITA\\Downloads\\firstCSV\\foods.csv"));
		CSVParser parser = fileResource.getCSVParser();

		for (CSVRecord csvRecord : parser) {
			System.out.print(csvRecord.get("Name")+ " ");
			System.out.print(csvRecord.get("Favorite Color") + " ");
			System.out.println(csvRecord.get("Favorite Food"));
		}

	}

	public static void main(String[] args) {
		CSV_example example = new CSV_example();
		example.readFood();
	}
}
