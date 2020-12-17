package CSV_programs;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class CSV_export_country {

	public void listExporter(CSVParser parser, String exportOfInterset) {
int count=0;
		for (CSVRecord csvRecord : parser) {
			String export = csvRecord.get("Exports");

			if (export.contains(exportOfInterset)) // return true or false
			{
				count++;
				String country = csvRecord.get("Country");
				System.out.println(country);
			}
		}
		System.out.println("count = "+count);

	}

	public void countryinfo(CSVParser parser, String country) {

		for (CSVRecord csvRecord : parser) {
			String coun = csvRecord.get("Country");
			if (coun.equals(country)) {
				System.out.println("Export Deatils" + csvRecord.get("Exports"));
				System.out.println("Dolar " + csvRecord.get("Value (dollars)"));

			}

		}
	}

	void listExportersTwoProducts(CSVParser parser, String item1, String intem2) {

int count=0;
		for (CSVRecord csvRecord : parser) {
			String export = csvRecord.get("Exports");
			//String export2 = csvRecord.get("Exports");

			if (export.contains(item1) && export.contains(intem2)) // return true or false
			{
				String country = csvRecord.get("Country");
				count++;
				System.out.println(country);
			}
		}
		System.out.println("TOTAL country :"+count);
	}
void bigExporters(CSVParser parser, String value) {
	
	for (CSVRecord csvRecord : parser) {
		
		String money=csvRecord.get("Value (dollars)");
		//int m=money.compareTo(value);
		//System.out.println(m);
		if (money.length() > value.length()) {
			String country = csvRecord.get("Country");
			System.out.println(country);
			String money1=csvRecord.get("Value (dollars)");
			System.out.println(money1);
		}
		
	}
}
	public static void main(String[] args) {

		CSV_export_country export_country = new CSV_export_country();

		FileResource resource = new FileResource(
				new File("C:\\Users\\BANSHITA\\Downloads\\exports\\exportdata.csv"));
		CSVParser parser = resource.getCSVParser();
		//export_country.countryinfo(parser, "Nauru");
		//export_country.listExportersTwoProducts(parser, "cotton", "flowers");
		
		export_country.bigExporters(parser, "$999,999,999,999");
		//export_country.listExporter(parser, "cocoa");
	}

}
