package Word_aasignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFrequencies {

	ArrayList<String> myWords;
	ArrayList<Integer> myFreqs;

	public WordFrequencies() {
		// TODO Auto-generated constructor stub
		myWords = new ArrayList<>();
		myFreqs = new ArrayList<>();
	}

	void findUnique() {

		File file = new File("C:\\Users\\BANSHITA\\Downloads\\data\\errors.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNext()) {
			String word = (String) scanner.next();
			int indx = myWords.indexOf(word.toLowerCase());

			if (indx == -1) {
				myWords.add(word.toLowerCase());
				myFreqs.add(1);
			} else {
				int freq = myFreqs.get(indx);
				freq++;
				myFreqs.set(indx, freq);
			}

		}
		// int wordlen = myWords.size();
		int often = 0;
		String word = null;
		for (int i = 0; i < myWords.size(); i++) {
			System.out.println(myWords.get(i) + " " + myFreqs.get(i));
			if (often < myFreqs.get(i)) {
				often = myFreqs.get(i);
				word = myWords.get(i);
			}
		}
		System.out.println("unique word " + myWords.size());
		System.out.println("most often occured = " + word + " " + often + " times");

		myFreqs.clear();
		myWords.clear();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WordFrequencies frequencies = new WordFrequencies();
		frequencies.findUnique();

	}

}
