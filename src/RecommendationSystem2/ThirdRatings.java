package RecommendationSystem2;

import java.util.ArrayList;
import java.util.HashMap;

public class ThirdRatings {

	private ArrayList<EfficientRater> myRaters;

	public ThirdRatings() {
		// default constructor
		this("ratings.csv");
	}

	public ThirdRatings(String ratingFile) {

		FirstRatings firstRatings = new FirstRatings();
		myRaters = firstRatings.loadRaters(ratingFile);
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

		//Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
		// Filter filter = new YearAfterFilter(2000);
		// Filter filter = new GenreFilter("Comedy");
		// Filter filter = new MinutesFilter(105, 135);
		 AllFilters filter = new AllFilters();
		 filter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		 filter.addFilter(new MinutesFilter(90, 180));
		ArrayList<Rating> ratedMovieList = getAverageRatingsByFilter(5, filter);
		ratedMovieList = sortRatingList(ratedMovieList);

		System.out.println("RATING \t TITLE");
		for (Rating rating : ratedMovieList) {
			System.out.println(rating.getValue() + " \t " + MovieDatabase.getTitle(rating.getItem()));
		}
		System.out.println("TOTAL COUNT=" + ratedMovieList.size());
	}

	public static void main(String[] args) {
		// "ratedmovies_short.csv", "ratings_short.csv"
		ThirdRatings thirdRatings = new ThirdRatings();
		thirdRatings.printAverageRatings();

	}
}
