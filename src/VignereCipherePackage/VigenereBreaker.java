package VignereCipherePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.duke.FileResource;

public class VigenereBreaker {

	// -------------------------------------sliceString------------------------------------------------
	public String sliceString(String message, int whichSlice, int totalSlices) {

		// System.out .println("-----------------------------calling
		// sliceString-------------------------------------------");
		StringBuilder str = new StringBuilder();

		for (int i = whichSlice; i < message.length(); i = i + totalSlices) {

			char ch = message.charAt(i);
			// System.out.println(ch);
			str.append(ch);
		}
		return str.toString();
	}

	// -------------------------------------------------tryKeyLength--------------------------------------
	public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {

		// System.out.println("-----------------------------calling
		// tryKeyLength-------------------------------------------");
		int[] key = new int[klength];

		ArrayList<String> strList = new ArrayList<>();

		for (int i = 0; i < klength; i++) {
			strList.add(sliceString(encrypted, i, klength));
		}
		int i = 0;
		for (String strEncrypted : strList) {
			CaesarCracker cracker = new CaesarCracker();

			key[i] = cracker.getKey(strEncrypted);
			// System.out.println(key[i]);
			i++;
		}

		return key;
	}

	// -----------------------------------------------------breakVigenere------------------------------------------------------------
	public void breakVigenere() {

		System.out.println(
				"-----------------------------calling breakVigenere-------------------------------------------");

		File file = new File("C:\\Users\\BANSHITA\\Downloads\\VigenereProgram\\messages\\secretmessage4.txt");
		// URLResource resource=new
		// URLResource("https://www.dukelearntoprogram.com//java/secretmessage1.txt");
		FileResource resource = new FileResource(file);
		String encrypted = resource.asString();

		

		HashMap<String, HashSet<String>> languages= readDictionary();

		
			
		String message = breakForAllLangs(encrypted, languages);
		
		
		
		// System.out.println(message);
		 String[] lines=message.split("\n");
		System.out.println(lines[0]);

		/*
		 * int[] key = tryKeyLength(encrypted, 4, 'e');
		 * 
		 * VigenereCipher cipher = new VigenereCipher(key); String message =
		 * cipher.decrypt(encrypted); System.out.println(message);
		 */

	}

	// -----------------------------------------------------readDictionary-----------------------------------
	public HashMap<String, HashSet<String>> readDictionary() {

		System.out.println(
				"-----------------------------calling readDictionary-------------------------------------------");

		HashMap<String, HashSet<String>> languages = new HashMap<>();

		String folder = "C:\\Users\\BANSHITA\\Downloads\\VigenereProgram\\dictionaries";
		File directory = new File(folder);
		File[] contents = directory.listFiles();

		for (File file : contents) {
			HashSet<String> set = new HashSet<>();

			FileResource fr = new FileResource(file);
			for (String line : fr.lines()) {
				line = line.toLowerCase().trim();
				set.add(line);
			}

			languages.put(file.getName(), set);
		}

		return languages;
	}

	// ---------------------------------------------------------countWords----------------------------------------------------------------------
	public int countWords(String message, HashSet<String> dictionary) {
		{
			int sum = 0;
			String[] words = message.split("\\W+");
			for (String word : words) {
				word = word.toLowerCase().trim();
				if (dictionary.contains(word)) {
					sum++;
				}
			}
			return sum;
		}
	}

	// --------------------------------------------------------breakForLanguage--------------------------------------------------------
	public String breakForLanguage(String encrypted, HashSet<String> dictionary) {

		System.out.println(
				"-----------------------------calling breakForLanguage-------------------------------------------");
		int max = 0;
		String maxMessage = null;
		int key[] = null;

		char common = mostCommonCharIn(dictionary);

		for (int i = 1; i <= 100; i++) {
			int[] keyLen = tryKeyLength(encrypted, i, common);
			VigenereCipher cipher = new VigenereCipher(keyLen);
			String message = cipher.decrypt(encrypted);
			int noRealwords = countWords(message, dictionary);

			if (max < noRealwords) {
				max = noRealwords;
				maxMessage = message;
				key = keyLen;
			}
		}

		System.out.println("******key*******");
		System.out.println("key");

		System.out.println("decrypted message : no of real words " + max + " keylength " + key.length);
		return maxMessage;
	}

	// --------------------------------------------------mostCommonCharIn---------------------------------------------------
	public Character mostCommonCharIn(HashSet<String> dictionary) {

		HashMap<Character, Integer> characters = new HashMap<>();

		for (String string : dictionary) {
			char[] ch = string.toCharArray();

			for (char c : ch) {

				if (characters.containsKey(c)) {
					characters.put(c, characters.get(c) + 1);
				} else {

					characters.put(c, 1);
				}
			}
		}
		int maxCh = 0;
		Character commonChar = null;
		for (Character key : characters.keySet()) {
			int value = characters.get(key);
			if (maxCh < value) {
				maxCh = value;
				commonChar = key;
			}
		}
		return commonChar;
	}

	// -----------------------------------------------------------breakForAllLangs---------------------------------------------------------------------------
	public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {

		int max = 0;
		String maxMessage = null;
		String lang = null;

		for (String language : languages.keySet()) {

			HashSet<String> dictionary = languages.get(language);

			System.out.println(language);

			String message = breakForLanguage(encrypted, dictionary);
			int norealwords = countWords(message, dictionary);

			if (max < norealwords) {
				max = norealwords;
				maxMessage = message;
				lang = language;
			}
		}

		System.out.println("langauge of message " + lang + " no of realwords contain " + max);
		return maxMessage;

	}
	// ----------------------------------------------------main--------------------------------------------------------------

	public static void main(String[] args) {
		VigenereBreaker breaker = new VigenereBreaker();

		// breaker.sliceString("abcdefghijklm", 0, 3);
		//breaker.breakVigenere();

		//File file = new File("C:\\Users\\BANSHITA\\Downloads\\secretmessage1.txt");
		// URLResource resource=new
		// URLResource("https://www.dukelearntoprogram.com//java/secretmessage1.txt");
		/*
		 * FileResource resource = new FileResource(file);
		 * breaker.readDictionary(resource);
		 */

		
		breaker.breakVigenere();
	}
}
