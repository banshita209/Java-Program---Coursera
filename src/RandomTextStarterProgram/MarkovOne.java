package RandomTextStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {

	/*
	 * private String myText; private Random myRandom;
	 * 
	 * public MarkovOne() { myRandom = new Random(); }
	 * 
	 * }
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
		int index = myRandom.nextInt(myText.length() - 1);
		String key = myText.substring(index, index + 1);
		sb.append(key);

		for (int k = 0; k < numChars - 1; k++) {

			ArrayList<String> follow = getFollows(key);
			
			if (follow.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follow.size());
			
			String next = follow.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}

		return sb.toString();
	}

}
