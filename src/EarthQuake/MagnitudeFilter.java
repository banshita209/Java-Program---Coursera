package EarthQuake;

public class MagnitudeFilter implements Filter {

	private double minMagnitude;
	private double maxMagnitude;

	public MagnitudeFilter(double min, double max) {
		minMagnitude = min;
		maxMagnitude = max;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		double mang = qe.getMagnitude();
		if (minMagnitude <= mang && mang <= maxMagnitude) {
			return true;
		}
		return false;
	}

}
