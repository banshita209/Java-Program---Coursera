package RandomTextStarterProgram;

import java.io.File;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
	public void runModel(IMarkovModel markov, String text, int size) {
		
		markov.setTraining(text);
		markov.setRandom(42);
		System.out.println("running with " + markov);
		for (int k = 0; k < 1; k++) {
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}

	public static void main(String[] args) {

		MarkovRunnerWithInterface interface1 = new MarkovRunnerWithInterface();

		File file = new File("C:\\Users\\BANSHITA\\Downloads\\InterfaceAbstractStarterProgram\\data\\romeo.txt");
		FileResource fr = new FileResource(file);
		//String st = fr.asString();
		String st="yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		st = st.replace(' ', '_');
		int size = 50;

		EfficientMarkovModel efficientMarkovModel=new EfficientMarkovModel(2);
		interface1.runModel(efficientMarkovModel, st, size);

	}

	private void printOut(String s) {
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

}
