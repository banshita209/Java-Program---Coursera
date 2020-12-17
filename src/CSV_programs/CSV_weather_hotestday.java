package CSV_programs;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class CSV_weather_hotestday {

	public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSofar=null;
        //For each row (currentRow) in the CSV File
        for(CSVRecord currentRow: parser){
            if(largestSofar == null){
                largestSofar=currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp=Double.parseDouble(largestSofar.get("TemperatureF"));
            
                if(currentTemp >largestTemp){
                    largestSofar=currentRow;
                }
            }
        }
        return largestSofar;
    }

	
	public static void main(String[] args) {
		
		CSV_weather_hotestday weather_hotestday=new CSV_weather_hotestday();
		
		FileResource resource=new FileResource(new File("C:\\Users\\BANSHITA\\Downloads\\hottestTempPseudo\\data\\2012\\weather-2012-01-01.csv"));
		CSVParser parser=resource.getCSVParser();
		CSVRecord largest=weather_hotestday.hottestHourInFile(parser);
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
		
	}
	
}
