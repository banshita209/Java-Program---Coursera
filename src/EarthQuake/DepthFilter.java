package EarthQuake;

public class DepthFilter implements Filter {
	private double minDepth;
	private double maxDepth;

	public DepthFilter(double min, double max) {

		minDepth = min;
		maxDepth = max;

	}

	@Override
	public boolean satisfies(QuakeEntry qe) {

		double dept = qe.getDepth();

		if (minDepth <= dept && dept <= maxDepth) {
			return true;
		}
		return false;
	}
}
