package recommendationSystem3;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

	FourthRatings fourthRatings = new FourthRatings();

	public void printAverageRatingsByYearAfterAndGenre() {

		Filter filter1 = new YearAfterFilter(2000);
		Filter filter2 = new GenreFilter("Comedy");
		AllFilters filter = new AllFilters();
		filter.addFilter(filter1);
		filter.addFilter(filter2);
		ArrayList<Rating> ratedMovieList = fourthRatings.getAverageRatingsByFilter(5, filter);
		Collections.sort(ratedMovieList);
		System.out.println("RATING \t TITLE");
		for (Rating rating : ratedMovieList) {
			System.out.println(rating.getValue() + " \t " + MovieDatabase.getTitle(rating.getItem()));
		}
		System.out.println("TOTAL COUNT=" + ratedMovieList.size());
	}

	public void printAverageRatings() {

		Filter filter1 = new YearAfterFilter(2000);
		Filter filter2 = new GenreFilter("Comedy");
		AllFilters filter = new AllFilters();
		filter.addFilter(filter1);
		filter.addFilter(filter2);

		ArrayList<Rating> ratedMovieList = fourthRatings.getAverageRatings(5);
		Collections.sort(ratedMovieList);
		System.out.println("RATING \t TITLE");
		for (Rating rating : ratedMovieList) {
			System.out.println(rating.getValue() + " \t " + MovieDatabase.getTitle(rating.getItem()));
		}
		System.out.println("TOTAL COUNT=" + ratedMovieList.size());
	}

	public void printSimilarRatings() {

		RaterDatabase.initialize();
		MovieDatabase.initialize();
		ArrayList<Rating> ratedMoviesBasiedsimlarity = fourthRatings.getSimilarRatings("71", 20, 5);

		System.out.println("ID \t\t MOVIE NAME \t\t RATING");
		for (Rating rating : ratedMoviesBasiedsimlarity) {
			System.out.println(
					rating.getItem() + " \t " + MovieDatabase.getTitle(rating.getItem()) + " \t " + rating.getValue());
		}
	}

	public void printSimilarRatingsByGenre() {

		RaterDatabase.initialize();
		MovieDatabase.initialize();
		ArrayList<Rating> ratedMoviesBasiedsimlarity = fourthRatings.getSimilarRatingsByFilter("964", 20, 5,
				new GenreFilter("Mystery"));

		System.out.println("ID \t\t MOVIE NAME \t\t RATING");
		for (Rating rating : ratedMoviesBasiedsimlarity) {
			System.out.println(
					rating.getItem() + " \t " + MovieDatabase.getTitle(rating.getItem()) + " \t " + rating.getValue());
		}
	}

	public void printSimilarRatingsByDirector() {

		RaterDatabase.initialize();
		MovieDatabase.initialize();
		ArrayList<Rating> ratedMoviesBasiedsimlarity = fourthRatings.getSimilarRatingsByFilter("120", 10, 2,
				new DirectorsFilter(
						"Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));

		System.out.println("ID \t\t MOVIE NAME \t\t RATING");
		for (Rating rating : ratedMoviesBasiedsimlarity) {
			System.out.println(
					rating.getItem() + " \t " + MovieDatabase.getTitle(rating.getItem()) + " \t " + rating.getValue());
		}
	}

	public void printSimilarRatingsByGenreAndMinutes() {

		RaterDatabase.initialize();
		MovieDatabase.initialize();
		AllFilters filter = new AllFilters();
		filter.addFilter(new GenreFilter("Drama"));
		filter.addFilter(new MinutesFilter(80, 160));
		ArrayList<Rating> ratedMoviesBasiedsimlarity = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, filter);

		System.out.println("ID \t\t MOVIE NAME \t\t RATING");
		for (Rating rating : ratedMoviesBasiedsimlarity) {
			System.out.println(
					rating.getItem() + " \t " + MovieDatabase.getTitle(rating.getItem()) + " \t " + rating.getValue());
		}
	}

	public void printSimilarRatingsByYearAfterAndMinutes() {

		RaterDatabase.initialize();
		MovieDatabase.initialize();
		AllFilters filter = new AllFilters();
		filter.addFilter(new YearAfterFilter(1975));
		filter.addFilter(new MinutesFilter(70, 200));
		ArrayList<Rating> ratedMoviesBasiedsimlarity = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, filter);

		System.out.println("ID \t\t MOVIE NAME \t\t RATING");
		for (Rating rating : ratedMoviesBasiedsimlarity) {
			System.out.println(
					rating.getItem() + " \t " + MovieDatabase.getTitle(rating.getItem()) + " \t " + rating.getValue());
		}
	}

	public static void main(String[] args) {
		MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
		// movieRunnerSimilarRatings.printSimilarRatingsByGenre();
		// movieRunnerSimilarRatings.printSimilarRatings();
		// movieRunnerSimilarRatings.printSimilarRatingsByDirector();
		// movieRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes();
		movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes();
	}
}
