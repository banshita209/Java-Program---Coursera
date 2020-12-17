package recommendationSystem3;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

	public double getAverageByID(String movieID, Integer minimalRaters) {

		Double sum = 0.0;
		int count = 0;

		for (Rater rater : RaterDatabase.getRaters()) {

			if (rater.hasRating(movieID)) {
				sum = sum + rater.getRating(movieID);
				count = count + 1;
			}
		}

		if (count >= minimalRaters) {
			double avg = sum / count;
			return avg;
		} else {
			return 0.0;
		}
	}

	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filter) {

		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

		ArrayList<Rating> avgMovieRating = new ArrayList<>();

		for (int i = 0; i < movies.size(); i++) {
			String movieID = movies.get(i);
			if (filter.satisfies(movieID)) {
				double avg = getAverageByID(movieID, minimalRaters);
				if (avg > 0.0) {

					avgMovieRating.add(new Rating(movieID, avg));
				}
			}
		}
		return avgMovieRating;
	}

	public ArrayList<Rating> getAverageRatings(int minimalRaters) {

		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

		ArrayList<Rating> avgMovieRating = new ArrayList<>();
		for (int i = 0; i < movies.size(); i++) {
			String movieID = movies.get(i);

			double avg = getAverageByID(movieID, minimalRaters);
			if (avg > 0.0) {

				avgMovieRating.add(new Rating(movieID, avg));
			}

		}
		return avgMovieRating;
	}

	private double dotProduct(Rater me, Rater r) {

		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		double distance = 0.0;

		for (String movie : movies) {

			if (me.hasRating(movie) && (r.hasRating(movie))) {

				double product = (me.getRating(movie) - 5.0) * (r.getRating(movie) - 5.0);
				distance = distance + product;

			}
		}
		return distance;
	}

	private ArrayList<Rating> getSimilarities(String id) {

		ArrayList<Rating> ratings = new ArrayList<Rating>();

		Rater meRater = RaterDatabase.getRater(id);

		for (Rater rater : RaterDatabase.getRaters()) {
			double distance = 0.0;

			if (!rater.getID().equals(id)) {
				distance = dotProduct(meRater, rater);

				if (distance > 0.0) {
					Rating rating = new Rating(rater.getID(), distance);

					ratings.add(rating);
				}
			}
		}
		Collections.sort(ratings, Collections.reverseOrder());
		/*
		 * for (Rating rating : ratings) { System.out.println(rating); }
		 */ return ratings;
	}

	public ArrayList<Rating> getSimilarRatings(String raterID, Integer numSimilarRaters, Integer minimalRaters) {

		ArrayList<Rating> weightedRaters = getSimilarities(raterID);
		ArrayList<Rating> MovieRatings = new ArrayList<Rating>();

		for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
			double sum = 0.0;
			int count = 0;

			for (int i = 0; i < numSimilarRaters; i++) {

				Rating raterWithweight = weightedRaters.get(i);

				Rater r = RaterDatabase.getRater(raterWithweight.getItem());

				if (r.hasRating(movieID)) {
					count = count + 1;
					double ratingValue = r.getRating(movieID);
					double temp = raterWithweight.getValue() * ratingValue;
					sum = sum + temp;

				}

			}
			if (count >= minimalRaters) {
				double weightedAverage = sum / count;
				Rating rating = new Rating(movieID, weightedAverage);
				MovieRatings.add(rating);
			}
		}
		Collections.sort(MovieRatings, Collections.reverseOrder());
		return MovieRatings;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, Integer numSimilarRaters, Integer minimalRaters,
			Filter f) {

		ArrayList<Rating> weightedRaters = getSimilarities(raterID);
		ArrayList<Rating> MovieRatings = new ArrayList<Rating>();

		for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
			double sum = 0.0;
			int count = 0;

			if (f.satisfies(movieID)) {

				for (int i = 0; i < numSimilarRaters; i++) {

					Rating raterWithweight = weightedRaters.get(i);

					Rater r = RaterDatabase.getRater(raterWithweight.getItem());

					if (r.hasRating(movieID)) {
						count = count + 1;
						double ratingValue = r.getRating(movieID);
						double temp = raterWithweight.getValue() * ratingValue;
						sum = sum + temp;

					}

				}
				if (count >= minimalRaters) {
					double weightedAverage = sum / count;
					Rating rating = new Rating(movieID, weightedAverage);
					MovieRatings.add(rating);
				}
			}
		}
		Collections.sort(MovieRatings, Collections.reverseOrder());
		return MovieRatings;
	}

	public static void main(String[] args) {
		FourthRatings fourthRatings = new FourthRatings();

		fourthRatings.getSimilarities("65");
	}
}
