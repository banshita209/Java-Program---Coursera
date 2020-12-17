package EarthQuake;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class QuakeSortInPlace {

	public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {

		int maxIndx = from;

		for (int i = from + 1; i < quakeData.size(); i++) {

			double d1 = quakeData.get(i).getDepth();
			double dmax = quakeData.get(maxIndx).getDepth();

			if (d1 > dmax) {
				maxIndx = i;
			}
		}
		return maxIndx;

	}

	public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

		for (int i = 0; i < 70; i++) {

			int maxIndx = getLargestDepth(in, i);

			QuakeEntry qi = in.get(i);
			QuakeEntry qmax = in.get(maxIndx);

			in.set(i, qmax);
			in.set(maxIndx, qi);
		}

		for (QuakeEntry quakeEntry : in) {
			System.out.println(quakeEntry);
		}
	}

	public static void main(String[] args) {
		EarthQuakeParser parser = new EarthQuakeParser();

		QuakeSortInPlace inPlace = new QuakeSortInPlace();

		String source = "C:\\Users\\BANSHITA\\Downloads\\EfficientSortStarterProgram\\data\\earthQuakeDataDec6sample2.atom";
		ArrayList<QuakeEntry> list = parser.read(source);

		 inPlace.sortByLargestDepth(list);

		//Collections.sort(list);
		/*for (QuakeEntry quakeEntry : list) {
			System.out.println(quakeEntry);
		}*/
		//System.out.println(list.get(600));
	}

}
