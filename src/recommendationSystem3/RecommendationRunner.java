package recommendationSystem3;

import java.util.ArrayList;

public class RecommendationRunner implements Recommender {

	@Override
	public ArrayList<String> getItemsToRate() {

		ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());

		ArrayList<String> sendMovie = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			sendMovie.add(movieList.get(i));
		}
		return sendMovie;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {

		FourthRatings fourthRatings = new FourthRatings();
		ArrayList<Rating> list = fourthRatings.getSimilarRatings(webRaterID, 10, 2);
		int count = 1;
		System.out.println(
				"<html><style>th{background-color: #0d0033;  font-size: 15px; color: white;font-weight: bold;font-family:Times New Roman, Times, serif;}td { background-color: #cce0ff;font-family:Times New Roman, Times, serif;}</style><body><table><tr><th>RANKING</th><th>Movie Name</th><th>Average Rating</th><th>Year</th><th>Genre</th><tr>");
		for (

		Rating rating : list) {
			System.out.println("<tr><td>" + count + "</td><td>" + MovieDatabase.getTitle(rating.getItem()) + "</td><td>"
					+ rating.getValue() + "</td><td>" + MovieDatabase.getYear(rating.getItem()) + "</td><td>"
					+ MovieDatabase.getGenres(rating.getItem()) + "</td><tr>");
			count++;
		}
		System.out.println("</table></body></html>");
	}

	public static void main(String[] args) {

	}
}
