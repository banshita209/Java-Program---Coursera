package EarthQuake;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry o1, QuakeEntry o2) {
		// TODO Auto-generated method stub

		/*StringBuilder b1 = new StringBuilder(o1.getInfo());
		StringBuilder b2 = new StringBuilder(o2.getInfo());

		b1 = b1.reverse();
		b2 = b2.reverse();*/
		
		String[] b1 = o1.getInfo().split(" ");
		String[] b2 = o2.getInfo().split(" ");

		System.out.println(b1.length +" "+ b2.length);
		
		int result = b1[b1.length-1].compareTo(b2[b2.length-1]);

		if (result == 0) {
			return new MagnitudeComparator().compare(o1, o2);
		} else {
			return result;
		}
	}

}
