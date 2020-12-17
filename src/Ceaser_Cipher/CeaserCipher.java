package Ceaser_Cipher;

import edu.duke.FileResource;

public class CeaserCipher {

	String encrypt(String input, int key) {

		StringBuilder encrypted = new StringBuilder(input);

		for (int i = 0; i < input.length(); i++) {
			char currchar = encrypted.charAt(i);
			char en_ch = encryptChar(currchar, key);

			encrypted.setCharAt(i, en_ch);
		}
		return encrypted.toString();
	}

	char encryptChar(char input, int key) {

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

		String lower_alphabet = "abcdefghijklmnopqrstuvwxyz";
		String lower_shiftedAlphabet = lower_alphabet.substring(key) + lower_alphabet.substring(0, key);

		if (Character.isUpperCase(input)) {
			int indx = alphabet.indexOf(input);
			input = shiftedAlphabet.charAt(indx);
		} else if (Character.isLowerCase(input)) {
			int indx = lower_alphabet.indexOf(input);
			input = lower_shiftedAlphabet.charAt(indx);
		}
		return input;
	}

	String encryptTwokey(String input, int key1, int key2) {

		int key = 0;
		StringBuilder encrypted = new StringBuilder(input);
		for (int i = 0; i < input.length(); i++) {
			key = (i % 2 == 0) ? key1 : key2;
			char currchar = encrypted.charAt(i);
			char en_ch = encryptChar(currchar, key);

			encrypted.setCharAt(i, en_ch);
		}
		return encrypted.toString();
	}

	int[] countLetter(String message) {

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int[] count = new int[26];

		for (int i = 0; i < message.length(); i++) {
			char ch = Character.toLowerCase(message.charAt(i));
			int dex = alphabet.indexOf(ch);
			if (dex != -1) {
				count[dex] += 1;
			}
		}
		// for (int i = 0; i < count.length; i++) { System.out.println(i+"-"+count[i]);}

		return count;

	}

	int maxIndex(int[] freq) {
		int max = 0;
		int maxIn = 0;

		for (int i = 0; i < freq.length; i++) {
			if (freq[i] > max) {
				max = freq[i];
				maxIn = i;
			}
		}
		return maxIn;
	}

	int Find_key(String encrypted) {
		int freq[] = countLetter(encrypted);
		int maxInd = maxIndex(freq);
		System.out.println("maxIndex" + maxInd);
		int Dkey = maxInd - 4; 							// 4 is index no of e and e is the most alphabet in the English

		if (maxInd < 4) {
			Dkey = 26 - (4 - maxInd);
		}
		System.out.println("key:" + Dkey);
		return Dkey;
	}

	String decrypt_unknown(String encrypted) {
		int Dkey = Find_key(encrypted);
		return encrypt(encrypted, 26 - Dkey);
	}

	String decrypt(String encrypted, int key) {
		return encrypt(encrypted, 26 - key);
	}

	String decrypt(String encrypted, int key1, int key2) {
		return encryptTwokey(encrypted, 26 - key1, 26 - key2);
	}

	String halfOfString(String str, int pos) {
		StringBuilder builder = new StringBuilder();
		for (int i = pos; i < str.length(); i+=2) {
			builder.append(str.charAt(i));
		}

		System.out.println(builder);
		return builder.toString();
	}

	String decryptTwoKey_unknown(String encrypted) {
		int len = encrypted.length();
		System.out.println(encrypted);
		String half1 = halfOfString(encrypted, 0);
		String half2 = halfOfString(encrypted, 1);
		System.out.println(half1 + "-" + half2);

		int key1 = Find_key(half1);
		int key2 = Find_key(half2);
		System.out.println("key===="+Find_key(encrypted));
		System.out.println("key1: " + key1 + " key2:" + key2);

		/*
		 * String msg_1 = decrypt_unknown(half1); String msg_2 = decrypt_unknown(half2);
		 * System.out.println(msg_1 + "-" + msg_2); StringBuilder builder_msg = new
		 * StringBuilder();
		 * 
		 * for (int i = 0; i < msg_1.length() || i < msg_2.length(); i++) {
		 * 
		 * char ch1 = msg_1.charAt(i); char ch2 = msg_2.charAt(i); if (ch1 != -1) {
		 * builder_msg.append(msg_1.charAt(i)); } else if (ch2 != -1) {
		 * builder_msg.append(msg_2.charAt(i)); } }
		 */
		String builder_msg = encryptTwokey(encrypted, 26 - key1, 26 - key2);
		System.out.println(builder_msg);
		return builder_msg;
	}

	public static void main(String[] args) {

		CeaserCipher cipher = new CeaserCipher();

		String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		//jSystem.out.println(message);
		//System.out.println(cipher.encrypt(message,15));
		//String decrypt = cipher.encryptTwokey(message, 2, 20);
		// System.out.println(cipher.encryptTwokey(message,21,8));
		// System.out.println(cipher.maxIndex(cipher.countLetter(message)));
		// System.out.println(cipher.decrypt_unknown(decrypt));

		 //System.out.println(cipher.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.", 14, 24));
		
		//String decrypt="Akag tjw Xibhr awoa aoee xakex znxag xwko";
		
		FileResource resource=new FileResource();
		String decrypt=resource.asString();

		System.out.println(cipher.decryptTwoKey_unknown(decrypt));
	//	System.out.println(cipher.decrypt_unknown(decrypt));

	}
}
