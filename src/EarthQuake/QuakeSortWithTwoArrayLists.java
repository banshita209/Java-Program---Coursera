package EarthQuake;

import java.util.ArrayList;

public class QuakeSortWithTwoArrayLists {

	public QuakeEntry getSmallestByMagnitude(ArrayList<QuakeEntry> entries) {

		QuakeEntry min = entries.get(0);

		for (QuakeEntry quakeEntry : entries) {
			if (quakeEntry.getMagnitude() > min.getMagnitude()) {
				min = quakeEntry;
			}
		}
		System.out.println(min);
		return min;
	}

	public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {

		ArrayList<QuakeEntry> out = new ArrayList<>();

		while (!in.isEmpty()) {
			QuakeEntry minElement = getSmallestByMagnitude(in);
			in.remove(minElement);
			out.add(minElement);
		}
		return out;
	}

	public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
		int minIndx = from;

		for (int i = from + 1; i < quakes.size(); i++) {
			if (quakes.get(i).getMagnitude() < quakes.get(minIndx).getMagnitude()) {
				minIndx = i;
			}
		}
		return minIndx;
	}

	public void sortByMagnitudeinSamelist(ArrayList<QuakeEntry> in) {
		System.out.println("total round " + in.size());
		for (int i = 0; i < in.size(); i++) {

			int minIndx = getSmallestMagnitude(in, i);
			QuakeEntry qi = in.get(i);
			QuakeEntry qmin = in.get(minIndx);
			in.set(i, qmin);
			in.set(minIndx, qi);
			if (checkInSortedOrder(in)) {
				System.out.println("sort breaking i =" + (i + 1));
				break;
			}
		}
	}

	public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {

		int minIndx = from;

		for (int i = from+1; i < quakes.size(); i++) {
			
			if (quakes.get(i).getDepth() < quakes.get(minIndx).getDepth()) {
				minIndx = i;
			}
		}
		return minIndx;
	}

	public ArrayList<QuakeEntry> sortByDeptinSamelist(ArrayList<QuakeEntry> in) {
		for (int i = 0; i < 70; i++) {

			int minIndx = getLargestDepth(in, i);

			QuakeEntry qi = in.get(i);
			QuakeEntry qmax = in.get(minIndx);

			in.set(i, qmax);
			in.set(minIndx, qi);
		}
	
		return in;
	}

	public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {

		System.out.println("numsort " + numSorted);

		for (int i = 0; i < quakeData.size(); i++) {

			if (i == quakeData.size() - 1) {

				continue;
			}

			QuakeEntry qi = quakeData.get(i);
			QuakeEntry qj = quakeData.get(i + 1);

			if (qi.getMagnitude() > qj.getMagnitude()) {
				quakeData.set(i, qj);
				quakeData.set(i + 1, qi);
			}

		}
	}

	public void sortByMagnitudeBubbleSort(ArrayList<QuakeEntry> in) {
		System.out.println("total round " + in.size());
		for (int i = 0; i < in.size(); i++) {
			onePassBubbleSort(in, i);
			if (checkInSortedOrder(in)) {
				System.out.println("sort breaking i" + (i+1));
				break;
			}
		}
		System.out.println("done sorting");
	}

	public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {

		boolean sorted = true;

		for (int i = 0; i < quakeData.size() - 1; i++) {

			if (i == quakeData.size() - 1) {
				System.out.println("breaking i" + i);
				continue;
			}

			QuakeEntry qi = quakeData.get(i);
			QuakeEntry qj = quakeData.get(i + 1);

			if (qi.getMagnitude() > qj.getMagnitude()) {
				return false;

			}
		}

		return true;
	}

	public static void main(String[] args) {

		EarthQuakeParser parser = new EarthQuakeParser();
		QuakeSortWithTwoArrayLists arrayLists = new QuakeSortWithTwoArrayLists();
		String source = "C:\\Users\\BANSHITA\\Downloads\\EfficientSortStarterProgram\\data\\earthQuakeDataWeekDec6sample2.atom";
		ArrayList<QuakeEntry> list = parser.read(source);

	//	arrayLists.sortByMagnitudeBubbleSort(list);
		// arrayLists.sortByDeptinSamelist(list);
		// arrayLists.getSmallestByMagnitude(list);

		 arrayLists.sortByMagnitudeinSamelist(list);

		// list=arrayLists.sortByDeptinSamelist(list);
	/*		int entry= arrayLists.getLargestDepth(list);
			System.out.println("largest \n "+list.get(entry));*/
		// arrayLists.sortByMagnitudeBubbleSort(list);
		/*for (QuakeEntry quakeEntry : list) {
			System.out.println(quakeEntry);
		}*/

	}
}
