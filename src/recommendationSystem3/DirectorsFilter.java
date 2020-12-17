package recommendationSystem3;

public class DirectorsFilter implements Filter {
	String[] directors;

	public DirectorsFilter(String director) {
		this.directors = director.split(",");
	}

	@Override
	public boolean satisfies(String id) {
		for (String director : directors) {
			if (MovieDatabase.getDirector(id).contains(director)) {
				return true;
			}
		}
		return false;
	}

}
