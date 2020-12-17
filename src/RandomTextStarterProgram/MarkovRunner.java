package RandomTextStarterProgram;

import java.io.File;
import java.util.ArrayList;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import edu.duke.FileResource;

public class MarkovRunner {

	public static void main(String[] args) {

		File file = new File("C:\\Users\\BANSHITA\\Downloads\\RandomTextStarterProgram\\data\\confucius.txt");
		FileResource fr = new FileResource(file);
		String st = fr.asString();
		st = st.replace('\n', ' ');

		/*
		 * MarkovRunner runner = new MarkovRunner(); runner.testGetFollowsWithFile(st,
		 * "he");
		 */

		MarkovZero markov = new MarkovZero();
		// MarkovModel markov = new MarkovModel(7);

		// MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		markov.setRandom(1024);

		MarkovRunner runner = new MarkovRunner();

		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			runner.printOut(text);
		}

	}

	void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (int k = 0; k < words.length; k++) {
			System.out.print(words[k] + " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	void testGetFollowsWithFile(String str, String find) {
		str = str.trim();

		ArrayList<String> follows = new ArrayList<>();
		System.out.println("lenght = " + str.length());
		int start = 0;
		while (start < str.length() - 1) {

			int index = str.indexOf(find, start);
			// System.out.println("indx" + index);

			if (index + find.length() >= str.length() || index == -1) {
				System.out.println("breaking.....");
				break;
			}
			char ch = str.charAt(index + find.length());
			start = index + find.length();
			// System.out.println(follows.toString());
			follows.add(String.valueOf(ch));
			System.out.println("start" + start);
		}

		System.out.println(follows.toString());

		System.out.println("size = " + follows.size());

	}

}
