package EarthQuake;

public class DistanceFilter implements Filter {

	Location location;
	double maximum_distance;

	public DistanceFilter(Location l, double max_d) {
		location = l;
		maximum_distance = max_d;

	}

	@Override
	public boolean satisfies(QuakeEntry qe) {

		if (qe.getLocation().distanceTo(location) < maximum_distance) {
			return true;
		}
		return false;
	}

}
