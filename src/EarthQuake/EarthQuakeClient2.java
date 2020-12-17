package EarthQuake;

import java.util.ArrayList;

public class EarthQuakeClient2 {
	public EarthQuakeClient2() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe : quakeData) {
			if (f.satisfies(qe)) {
				answer.add(qe);
			}
		}

		return answer;
	}

	public void quakesWithFilter() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

		String source = "C:\\Users\\BANSHITA\\Downloads\\EarthquakeFilterStarterProgram\\data\\nov20quakedata.atom";

		ArrayList<QuakeEntry> list = parser.read(source);

		System.out.println("read data for " + list.size() + " quakes");

		/*
		 * Filter magnitudeFilter = new MagnitudeFilter(3.5, 4.5); Filter deptfilter =
		 * new DepthFilter(-55000.0 , -20000.0 );
		 */

		Filter distanceFilter = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
		Filter phraseFilter = new PhraseFilter("end", "a");

		ArrayList<QuakeEntry> m7 = filter(list, distanceFilter);
		ArrayList<QuakeEntry> m8 = filter(m7, phraseFilter);

		for (QuakeEntry qe : m8) {
			System.out.println(qe);
		}
		System.out.println("Data found  :" + m8.size());
	}

	public void createCSV() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "../data/nov20quakedata.atom";
		String source = "data/nov20quakedatasmall.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		dumpCSV(list);
		System.out.println("# quakes read: " + list.size());
	}

	public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for (QuakeEntry qe : list) {
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
					qe.getMagnitude(), qe.getInfo());
		}
	}

	void testMatchAllFilter() {

		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "C:\\Users\\BANSHITA\\Downloads\\EarthquakeFilterStarterProgram\\data\\nov20quakedata.atom";

		ArrayList<QuakeEntry> list = parser.read(source);
		ArrayList<QuakeEntry> answer = new ArrayList<>();

		Filter magnitudeFilter = new MagnitudeFilter(0.0, 5.0);

		Filter deptfilter = new DepthFilter(-180000.0, -30000.0);

		Filter distanceFilter = new DistanceFilter(new Location(55.7308, 9.1153), 3000000);

		Filter phraseFilter = new PhraseFilter("any", "e");

		MatchAllFilter matchAll = new MatchAllFilter();

		matchAll.addFilter(phraseFilter);
		matchAll.addFilter(distanceFilter);
		matchAll.addFilter(magnitudeFilter);
		// matchAll.addFilter(deptfilter);

		answer = filter(list, matchAll);

		for (QuakeEntry quakeEntry : answer) {
			System.out.println(quakeEntry);
		}
		System.out.println("data found  " + answer.size());
	}

	public static void main(String[] args) {

		EarthQuakeClient2 quakeClient2 = new EarthQuakeClient2();
		quakeClient2.quakesWithFilter();
		// quakeClient2.testMatchAllFilter();
	
	}
}
