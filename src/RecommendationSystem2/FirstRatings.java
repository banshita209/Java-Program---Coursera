package RecommendationSystem2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class FirstRatings {

	ArrayList<Movie> movies = new ArrayList<>();
	ArrayList<EfficientRater> raters = new ArrayList<>();

	public ArrayList<Movie> loadMovies(String fileName) {
		String source = "C:\\Users\\BANSHITA\\Downloads\\StepOneStarterProgram\\data\\" + fileName;
		File file = new File(source);
		FileResource resource = new FileResource(file);
		CSVParser parser = resource.getCSVParser();
		// public Movie(String anID, String aTitle, String aYear, String theGenres,
		// String aDirector, String aCountry,String aPoster, int theMinutes)

		for (CSVRecord csvRecord : parser) {

			// System.out.println("movie name :" + csvRecord.get("title"));

			Movie movie = new Movie(csvRecord.get("id"), csvRecord.get("title"), csvRecord.get("year"),
					csvRecord.get("genre"), csvRecord.get("director"), csvRecord.get("country"),
					csvRecord.get("poster"), Integer.parseInt(csvRecord.get("minutes")));
			movies.add(movie);
			// System.out.println(movie.toString());

		}
		System.out.println(movies.toString());
		return movies;
	}

	private HashMap<String, Integer> genreCountinMovieList() {

		HashMap<String, Integer> genreCount = new HashMap<>();

		for (Movie movie : movies) {

			String genres = movie.getGenres();

			String[] genreList = genres.split(",");

			for (String genre : genreList) {

				if (genreCount.containsKey(genre.toLowerCase().trim())) {

					int count = genreCount.get(genre.toLowerCase().trim());
					count++;
					genreCount.put(genre.toLowerCase().trim(), count);
				} else {
					genreCount.put(genre.toLowerCase().trim(), 1);
				}
			}
		}
		return genreCount;
	}

	private int minGreaterthan(int min) {
		int count = 0;
		for (Movie movie : movies) {

			int movieMin = movie.getMinutes();
			/* System.out.println(movieMin); */
			if (movieMin > min) {
				count++;
			}
		}
		return count;
	}

	private HashMap<String, Integer> findManyDirector() {

		HashMap<String, Integer> directorsList = new HashMap<>();
		int max = 0;
		String directedMax = null;
		for (Movie movie : movies) {
			String director = movie.getDirector();
			String[] directorss = director.split(",");
			for (String direct : directorss) {

				direct = direct.trim().toLowerCase();

				if (directorsList.containsKey(direct)) {

					int count = directorsList.get(direct);
					count++;
					directorsList.put(direct, count);

					if (max < count) {
						max = count;
						directedMax = direct;
					}
				} else {
					directorsList.put(direct, 1);
				}
			}
		}
		System.out.println("largested no of movie directed by one directed is " + max + " by " + directedMax);
		return directorsList;
	}

	public void testLoadMovies() {
		// TODO Auto-generated method stub

		HashMap<String, Integer> genreCount = genreCountinMovieList();
		HashMap<String, Integer> directors = findManyDirector();

		System.out.println("comdey movie :" + genreCount.get("Comedy".toLowerCase()));
		System.out.println("no of movies greater than 150 = " + minGreaterthan(193));
		System.out.println("Total no of movies :" + movies.size());
		System.out.println("-----------------------------------------------------------------");

		/*
		 * for (String director : directors.keySet()) { System.out.println(director +
		 * " = " + directors.get(director).si); }
		 */

	}

	public ArrayList<EfficientRater> loadRaters(String fileName) {
		String source = "C:\\Users\\BANSHITA\\Downloads\\StepOneStarterProgram\\data\\" + fileName;
		File file = new File(source);
		FileResource resource = new FileResource(file);
		CSVParser parser = resource.getCSVParser();

		for (CSVRecord csvRecord : parser) {

			String curID = csvRecord.get("rater_id");
			String movieID = csvRecord.get("movie_id");
			Double valueRating = Double.valueOf(csvRecord.get("rating"));
			boolean found = false;

			for (EfficientRater data : raters) {
				
				if (curID.contentEquals(data.getID())) {
					// System.out.println("in condition:");
					data.addRating(movieID, valueRating);
					found = true;
					break;
				}
			}
			
			if (raters.isEmpty() || found == false) {
				EfficientRater rate = new EfficientRater(curID);
				rate.addRating(movieID, valueRating);
				raters.add(rate);
			}
		}

		/*
		 * for (Rater record : raters) { System.out.println(record.toString()); }
		 */
		// System.out.println("TOATAL RATERS ARE :" + raters.size());
		System.out.println(raters.toString());
		return raters;
	}

	public void findRater(String id) {

		for (Rater rater : raters) {

			if (rater.getID().equals(id)) {
				System.out.println(rater.toString());
				System.out.println("Rating Done by this user " + rater.numRatings());
			}
		}

	}

	public void maximumRatingByoneRater() {
		int max = 0;
		Rater maxRate = null;
		for (Rater rater : raters) {
			int len = rater.numRatings();
			if (max < len) {
				max = len;
				maxRate = rater;
			}
		}
		System.out.println("Maximum rating by one user are " + max + " by user id :" + maxRate.getID());
	}

	public void ratingforParticularMovie(String movieID) {

		ArrayList<Rater> desiredRater = new ArrayList<>();
		for (Rater rater : raters) {

			Double rating = rater.getRating(movieID);

			if (rating != -1) {
				desiredRater.add(rater);
			}
		}

		for (Rater rater : desiredRater) {
			System.out.println(rater);
		}
		System.out.println("total raters found =" + desiredRater.size());
	}

	public void uniqueMovieRated() {
		ArrayList<String> movielist = new ArrayList<>();

		for (Rater rater : raters) {
			ArrayList<String> ratings = rater.getItemsRated();

			for (String data : ratings) {
				System.out.println("data =" + data);
				if (movielist.contains(data)) {
					continue;
				} else {
					movielist.add(data);
				}
			}
		}
		for (String string : movielist) {
			System.out.println(string);
		}
		System.out.println("TOTAL UNIQUE MOVIES FOUND ARE :" + movielist.size());

	}

	public static void main(String[] args) {
		FirstRatings ratings = new FirstRatings();

		// ratings.loadMovies("ratedmoviesfull.csv");
		// ratings.testLoadMovies();
		ratings.loadRaters("ratings.csv");
		// ratings.ratingforParticularMovie("1798709");
		//ratings.findRater("193");
		// ratings.maximumRatingByoneRater();
		// ratings.uniqueMovieRated();
	}
}
