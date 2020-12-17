package recommendationSystem3;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {

	private String myID;
	private HashMap<String, Double> myRatings;

	public EfficientRater(String id) {
		myID = id;
		myRatings = new HashMap<>();
	}

	public void addRating(String movieID, double rating) {
		myRatings.put(movieID, rating);
	}

	public boolean hasRating(String movieID) {

		if (myRatings.containsKey(movieID)) {
			return true;
		}

		return false;
	}

	public String getID() {
		return myID;
	}

	public double getRating(String movieID) {
		if (myRatings.containsKey(movieID)) {
			return myRatings.get(movieID);
		}
		return -1;
	}

	public int numRatings() {
		return myRatings.size();
	}

	public ArrayList<String> getItemsRated() {

		return new ArrayList<>(myRatings.keySet());
	}

	public HashMap<String, Double> returnMyRating() {
		return myRatings;
	}

	@Override
	public String toString() {
		return "Rater [myID=" + myID + ", myRatings=" + myRatings + "]";
	}

}
