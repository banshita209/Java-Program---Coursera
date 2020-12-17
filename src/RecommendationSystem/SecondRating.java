package RecommendationSystem;

import java.util.ArrayList;

public class SecondRating {
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

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

		for (Rater rater : myRaters) {
			ArrayList<Rating> ratings = rater.returnMyRating();
			for (Rating rating : ratings) {

				if (rating.getItem().equals(movieID)) {

					sum = sum + rater.getRating(movieID);
					count = count + 1;
				}
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

			for (int j = 0; j < myRaters.size(); j++) {
				double ratingValue = myRaters.get(j).getRating(movieID);

				if (ratingValue > 0) {
					ratingsTotal = ratingsTotal + ratingValue;
					count = count + 1;
				}
				// System.out.println("i=" + i + "movie=" + movieID + "j=" + j + "rating=" +
				// ratingValue + "total=" + ratingsTotal + "count=" + count);
			}
			if (count >= minimalRaters) {

				// System.out.println("total rating---" + ratingsTotal);
				// System.out.println("count===" + count);
				double avgRating = ratingsTotal / count;
				// System.out.println("avverage ---" + avgRating);
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
		System.out.println("TOTAL COUNT="+ratedMovieList.size());

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
		//String id = secondRating.getID("Vacation");
		//System.out.println("ans " + secondRating.getAverageByID(id, 0));
	}
}
