package RecommendationSystem2;

public class MinutesFilter implements Filter {
	int min = 0;
	int max = 0;

	public MinutesFilter(int min, int max) {
		// TODO Auto-generated constructor stub
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean satisfies(String id) {
		int minutes = MovieDatabase.getMinutes(id);

		if (min <= minutes && minutes <= max) {
			return true;
		}
		return false;
	}

}
