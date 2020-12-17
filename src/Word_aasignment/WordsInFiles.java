package Word_aasignment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.FileResource;

public class WordsInFiles {

	HashMap<String, Integer> words = new HashMap<>();
	HashMap<String, ArrayList<String>> wordInfile = new HashMap<>();

	void maxNumber() {
		String folder = "C:\\Users\\BANSHITA\\Downloads\\data";
		File directory = new File(folder);
		File[] contents = directory.listFiles();

		for (File f : contents) {

			System.out.println(f.getName());

			FileResource resource = new FileResource(f);
			ArrayList<String> filenames = new ArrayList<>();

			for (String str_word : resource.words()) {
				// System.out.println(str_word);
				if (wordInfile.keySet().contains(str_word)) {

					// filenames = wordInfile.get(str_word);
					// filenames.add(f.getName());
					// System.out.println(filenames.toString());
					if (wordInfile.get(str_word).contains(f.getName())) {
						continue;
					}
					wordInfile.get(str_word).add(f.getName());

				} else {
					// filenames.add(f.getName());

					// wordInfile.put(str_word, filenames);
					wordInfile.put(str_word, new ArrayList<String>());
					wordInfile.get(str_word).add(f.getName());
				}
				filenames.clear();
			}

		}
		int c = 0;
		for (String words : wordInfile.keySet()) {

			ArrayList<String> files = wordInfile.get(words);

			if (files.size() == 4) {
				c++;
				System.out.println(words + "" + files.toString() + " " + files.size());
			}
		}
		System.out.println(c);
	}

	void printFilesIn(String find) {
		for (String word : wordInfile.keySet()) {

			if (word.equals(find)) {
				System.out.println(word + " \t " + wordInfile.get(word));
			}
		}
	}

	void addWordsFromFile(FileResource resource) {

		for (String str_word : resource.words()) {
			if (words.keySet().contains(str_word)) {
				words.put(str_word, words.get(str_word) + 1);
			} else {
				words.put(str_word, 1);
			}
		}
	}

	void buildWordFileMap() {
		words.clear();
		String folder = "C:\\Users\\BANSHITA\\Downloads\\data";
		File directory = new File(folder);
		File[] contents = directory.listFiles();
		for (File f : contents) {
			System.out.println(f.getName());
			FileResource resource = new FileResource(f);
			addWordsFromFile(resource);
		}
		// System.out.println(words.size());
		// words.clear();
	}

	public static void main(String[] args) {

		WordsInFiles files = new WordsInFiles();
		files.buildWordFileMap();
		files.maxNumber();

		files.printFilesIn("laid");

	}

}
