package Word_aasignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.duke.FileResource;

public class CharactersInPlay {

	ArrayList<String> characters = new ArrayList<>();
	ArrayList<Integer> count = new ArrayList<>();

	void update(String person) {

		if (characters.indexOf(person) == -1) {
			characters.add(person);
			count.add(0);
		}

	}

	boolean isAllupperCase(String data) {

		char[] ch = data.toCharArray();

		for (char c : ch) {
			if (Character.isLetter(c)) {

				if (Character.isLowerCase(c)) {
					return false;

				}
			}
		}
		return true;
	}

	boolean isPerson(String data) {

		if (isAllupperCase(data) && data.endsWith(".")) {
			return true;
		}
		return false;

	}

	void findAllCharacters() {

		File file = new File("C:\\Users\\BANSHITA\\Downloads\\data\\errors.txt");
		FileResource resource = new FileResource(file);

		for (String word : resource.lines()) {
			int indx = word.indexOf(".");
			if (indx != -1) {
				String name = word.substring(0, indx);
				name = name.trim();
				if (name.startsWith("ACT") || name.startsWith("SCENE")) {
					continue;
				} else {
					int pos = characters.indexOf(name);
					if (pos == -1) {
						characters.add(name);
						count.add(1);
					} else {
						int data = count.get(pos);
						data+=1;
						count.set(pos, data);
					}
				}
			}
		}
		printALLCharacter();
	}

	void maxLine() {
		int max = 0;
		String name = null;
	
		for (int i = 0; i < characters.size(); i++) {
		
			if (count.get(i)>max) {
				//System.out.println(i);
				max = count.get(i);
				name = characters.get(i);
			}
		}

		System.out.println("MAximum line are spoken by " + name + " - " + max + " times.");
	}

	void printALLCharacter() {
		for (int i = 0; i < characters.size(); i++) {
			if (count.get(i)==1) {
				continue;
			}
			System.out.println(characters.get(i) + "\t \t" + count.get(i));
		}
	}
	
	void printCharacterBetween(int min, int max) {
		System.out.println(max+" "+min);
		
		int lines=0;
		for (int i = 0; i < characters.size(); i++) {
		
			lines=count.get(i);
		
			if ((min <= lines) && (lines <= max)) {
				//System.out.println(lines);
				if (lines==1) {
					continue;
				}
				System.out.println(characters.get(i)+" "+count.get(i)+" lines");
			}
		
		}
		
	}

	public static void main(String[] args) {

		CharactersInPlay play = new CharactersInPlay();
		play.findAllCharacters();
		play.maxLine();
		play.printCharacterBetween(10, 15);

	}

}
