package RandomTextStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {

	private int charN;

	public MarkovModel(int n) {
		myRandom = new Random();
		charN = n;
	}

	/*
	 * public void setRandom(int seed) { myRandom = new Random(seed); }
	 * 
	 * public void setTraining(String s) { myText = s.trim(); }
	 */
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - charN);
		String key = myText.substring(index, index + charN);
		sb.append(key);

		for (int k = 0; k < numChars - charN; k++) {

			ArrayList<String> follow = getFollows(key);
			if (follow.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follow.size());
			if (index >= myText.length()) {
				break;
			}
			String next = follow.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}

		return sb.toString();
	}

	/*
	 * public ArrayList<String> getFollows(String key) { ArrayList<String> follows =
	 * new ArrayList<>(); int pos = 0; while (pos < myText.length()) { int start =
	 * myText.indexOf(key, pos); if (start == -1) { break; } if (start +
	 * key.length() >= myText.length() - 1) { break; } String next =
	 * myText.substring(start + key.length(), start + key.length() + 1);
	 * follows.add(next); pos = start + key.length(); } return follows; }
	 */
}
