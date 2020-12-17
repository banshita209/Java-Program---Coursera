package Ceaser_Cipher;

public class CeaserCipher_oop {

	private String alphabet;
	private String shiftedAlphabet;

	public CeaserCipher_oop(int key) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
	}

	String encrypt(String input) {

		StringBuilder encrypted = new StringBuilder(input);

		for (int i = 0; i < input.length(); i++) {
			char currchar = encrypted.charAt(i);

			int indx = alphabet.indexOf(currchar);
			char en_ch = shiftedAlphabet.charAt(indx);
			if (indx != -1) {

				encrypted.setCharAt(i, en_ch);
			}
		}
		return encrypted.toString();
	}

}
