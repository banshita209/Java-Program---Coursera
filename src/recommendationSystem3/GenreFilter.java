package recommendationSystem3;

public class GenreFilter implements Filter {
	String genre;

	public GenreFilter(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean satisfies(String id) {
		if (MovieDatabase.getGenres(id).contains(genre)) {
			return true;
		}
		return false;
	}
}
