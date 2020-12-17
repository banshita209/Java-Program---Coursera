package RecommendationSystem2;

public class YearFilter implements Filter {
	int year;

	public YearFilter(int year) {
		// TODO Auto-generated constructor stub
		this.year = year;
	}

	@Override
	public boolean satisfies(String id) {
		if (MovieDatabase.getYear(id) >= year) {
			return true;
		}
		return false;
	}

}
