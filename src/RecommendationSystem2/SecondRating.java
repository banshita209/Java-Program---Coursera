package RecommendationSystem2;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondRating {
	private ArrayList<Movie> myMovies;
	private ArrayList<EfficientRater> myRaters;

	public SecondRating() {
		// default constructor
		this("ratedmoviesfull.csv", "ratings.csv");
	}

	public SecondRating(String movieFile, String ratingFile) {

		FirstRatings firstRatings = new FirstRatings();
		myMovies = firstRatings.loadMovies(movieFile);
		myRaters = firstRatings.loadRaters(ratingFile);
	}

	public int getMovieSize() {
		return myMovies.size();
	}

	public int getRaterSize() {
		return myRaters.size();
	}

	public double getAverageByID(String movieID, Integer minimalRaters) {

		Double sum = 0.0;
		int count = 0;

		for (EfficientRater rater : myRaters) {
			HashMap<String, Double> ratings = rater.returnMyRating();

			if (ratings.containsKey(movieID)) {
				sum = sum + ratings.get(movieID);
				count = count + 1;
			}
		}

		if (count >= minimalRaters) {

			return sum / (double) count;

		} else {
			return 0.0;
		}
	}

	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		System.out.println("minrater " + minimalRaters);

		ArrayList<Rating> avgMovieRating = new ArrayList<>();

		for (int i = 0; i < myMovies.size(); i++) {
			String movieID = myMovies.get(i).getID();

			double ratingsTotal = 0;
			int count = 0;
			for (EfficientRater rater : myRaters) {
				double ratingValue = rater.getRating(movieID);
				if (ratingValue > 0) {
					ratingsTotal = ratingsTotal + ratingValue;
					count = count + 1;
				}
			}
			if (count >= minimalRaters) {

				double avgRating = ratingsTotal / count;
				avgMovieRating.add(new Rating(movieID, avgRating));
			}
		}
		return avgMovieRating;
	}

	public String getTitle(String movieID) {

		for (Movie movie : myMovies) {
			if (movie.getID().equals(movieID)) {
				return movie.getTitle();
			}
		}
		return "Title not found";
	}

	private ArrayList<Rating> sortRatingList(ArrayList<Rating> ratings) {

		for (int i = 0; i < ratings.size(); i++) {
			for (int j = i + 1; j < ratings.size(); j++) {
				if (ratings.get(i).compareTo(ratings.get(j)) == 1) {
					Rating r = ratings.get(i);
					ratings.set(i, ratings.get(j));
					ratings.set(j, r);
				}
			}
		}
		return ratings;
	}

	public void printAverageRatings() {

		ArrayList<Rating> ratedMovieList = getAverageRatings(12);
		ratedMovieList = sortRatingList(ratedMovieList);

		System.out.println("RATING \t TITLE");
		for (Rating rating : ratedMovieList) {
			System.out.println(rating.getValue() + " \t " + getTitle(rating.getItem()));
		}
		System.out.println("TOTAL COUNT=" + ratedMovieList.size());
	}

	public String getID(String title) {
		for (Movie movie : myMovies) {
			if (movie.getTitle().equals(title)) {
				System.out.println("found id" + movie.getID());
				return movie.getID();
			}
		}
		return "NO ID FOR THE MOVIE FOUND";
	}

	public static void main(String[] args) {
		// "ratedmovies_short.csv", "ratings_short.csv"
		SecondRating secondRating = new SecondRating();
		secondRating.printAverageRatings();
		// String id = secondRating.getID("Vacation");
		// System.out.println("ans " + secondRating.getAverageByID(id, 0));
	}
}
