package EarthQuake;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		// TODO Auto-generated method stub

		int result = q1.getInfo().compareTo(q2.getInfo());

		if (result == 0) {
			return new DepthCompartor().compare(q1, q2);
		} else {

			return result;
		}
	}

}
