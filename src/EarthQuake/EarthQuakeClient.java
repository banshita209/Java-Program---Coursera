package EarthQuake;

import java.util.ArrayList;

public class EarthQuakeClient {

	public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for (QuakeEntry qe : list) {
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
					qe.getMagnitude(), qe.getInfo());
		}

	}

	public void createCSV() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "C:\\Users\\BANSHITA\\Downloads\\EarthquakeMagnitudeDistanceDemo\\data\\nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		dumpCSV(list);
		System.out.println("# quakes read: " + list.size());
	}

	public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		// TODO
		for (QuakeEntry qe : quakeData) {
			if (qe.getLocation().distanceTo(from) < distMax) {
				answer.add(qe);
			}
		}
		return answer;
	}

	public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		// TODO
		for (QuakeEntry qe : quakeData) {
			if (qe.getMagnitude() > magMin) {
				answer.add(qe);
			}
		}
		return answer;
	}

	public void bigQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "C:\\Users\\BANSHITA\\Downloads\\EarthquakeMagnitudeDistanceDemo\\data\\nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		/*
		 * for (QuakeEntry qe : list) { if (qe.getMagnitude() > 5.0) {
		 * System.out.println(qe); } }
		 */
		ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
		for (QuakeEntry qe : listBig) {
			System.out.println(qe);
		}
	}

	public void closeToMe() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "data/nov20quakedata.atom";
		String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("# quakes read: " + list.size());

		// Durham, NC
		// Location city = new Location(35.988, -78.907);
		// Bridgeport, CA
		Location city = new Location(38.17, -118.82);
		ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000 * 1000, city);
		for (int k = 0; k < close.size(); k++) {
			QuakeEntry entry = close.get(k);
			double distanceInMeters = city.distanceTo(entry.getLocation());
			System.out.println(distanceInMeters / 1000 + " " + entry.getInfo());
		}

	}

	public void filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
		int count = 0;
		for (QuakeEntry quakeEntry : quakeData) {
			double dept = quakeEntry.getDepth();

			if (minDepth < dept && dept < maxDepth) {
				count++;
				System.out.println(quakeEntry.getDepth());
			}
		}
		System.out.println("count min < dept < max =" + count);

	}

	public ArrayList<QuakeEntry> quakesByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {

		ArrayList<QuakeEntry> desiredEntry = new ArrayList<>();
		for (QuakeEntry quakeEntry : quakeData) {
			String title = quakeEntry.getInfo();

			if (where.equalsIgnoreCase("start")) {
				if (title.startsWith(phrase)) {
					desiredEntry.add(quakeEntry);
				}
			} else if (where.equalsIgnoreCase("end")) {
				if (title.endsWith(phrase)) {
					desiredEntry.add(quakeEntry);
				}
			} else if (where.equalsIgnoreCase("any")) {
				if (title.contains(phrase)) {
					desiredEntry.add(quakeEntry);
				}
			}

		}
		System.out.println("count quakesby phrase =" + desiredEntry.size());
		return desiredEntry;

	}

	public void findLargestQuakes(ArrayList<QuakeEntry> quakeEntries) {

		ArrayList<QuakeEntry> desiredEnteries=new ArrayList<>();

		
		
		for (QuakeEntry quakeEntry : quakeEntries) {
			
		}
	}

	public static void main(String[] args) {
		EarthQuakeClient quakeClient = new EarthQuakeClient();
		// quakeClient.createCSV();
		// quakeClient.bigQuakes();

		EarthQuakeParser parser = new EarthQuakeParser();

		String source = "C:\\Users\\BANSHITA\\Downloads\\EarthquakeMagnitudeDistanceDemo\\data\\nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		 //quakeClient.filterByDepth(list,-4000.0 , -2000.0);
		quakeClient.quakesByPhrase(list, "any", "Can");

	}
}
