package EarthQuake;

/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {

	String source = "C:\\\\Users\\\\BANSHITA\\\\Downloads\\\\EfficientSortStarterProgram\\\\data\\\\earthQuakeDataWeekDec6sample1.atom";

	public void sortWithCompareTo() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);

		Collections.sort(list, new MagnitudeandDeptCompartor());

		for (QuakeEntry qe : list) {
			System.out.println(qe);
		}

		System.out.println("\n\n\n"+list.get(600));
	}

	public void sortByMagnitude() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		Collections.sort(list, new MagnitudeComparator());
		for (QuakeEntry qe : list) {
			System.out.println(qe);
		}

	}

	public void sortByDistance() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		// Location is Durham, NC
		Location where = new Location(35.9886, -78.9072);
		Collections.sort(list, new DistanceComparator(where));
		for (QuakeEntry qe : list) {
			System.out.println(qe);
		}

	}

	public void sortByTitleAndDepth() {

		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		// Location is Durham, NC
		Collections.sort(list, new TitleAndDepthComparator());

		// Filter deptF= new DepthFilter(0, 10000);

		int count = 0;
		/*
		 * for (QuakeEntry qe : list) { if (count == 49) {
		 * 
		 * System.out.println(qe); } count++; }
		 */

		System.out.println(list.get(500));

	}

	void sortByLastWordInTitleThenByMagnitude() {

		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		// Location is Durham, NC

		Collections.sort(list, new TitleLastAndMagnitudeComparator());

		int count = 0;

		/*
		 * for (QuakeEntry qe : list) { if (count == 49) {
		 * 
		 * System.out.println(qe); } count++; }
		 */
		System.out.println(list.get(500));
	}

	public static void main(String[] args) {
		DifferentSorters sorters = new DifferentSorters();

		sorters.sortWithCompareTo();

		//sorters.sortByTitleAndDepth();

		 //sorters.sortByLastWordInTitleThenByMagnitude();
	}
}
