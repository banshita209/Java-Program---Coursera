package EarthQuake;

import java.util.Comparator;

public class MagnitudeandDeptCompartor implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry o1, QuakeEntry o2) {
		// TODO Auto-generated method stub
		int result = new MagnitudeComparator().compare(o1, o2);
		
		if (result == 0) {
			double d1 = o1.getDepth();
			double d2 = o2.getDepth();

			if (d1 > d2) {
				return -1;
			} else if (d1 < d2) {
				return 1;
			} else {
				return 0;
			}
		}
		return result;
	}

}
