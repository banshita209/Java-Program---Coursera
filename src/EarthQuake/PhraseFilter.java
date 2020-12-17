package EarthQuake;

public class PhraseFilter implements Filter {

	String where;
	String phrase;

	public PhraseFilter(String where, String phrase) {
		this.phrase = phrase;
		this.where = where;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		String title = qe.getInfo();

		if (where.equalsIgnoreCase("start")) {
			if (title.startsWith(phrase)) {
				return true;
			}
		} else if (where.equalsIgnoreCase("end")) {
			if (title.endsWith(phrase)) {
				return true;
			}
		} else if (where.equalsIgnoreCase("any")) {
			if (title.contains(phrase)) {
				return true;
			}
		}
		return false;
	}

}
