package Ceaser_Cipher;

public class WordPlay {

	boolean isVowel(char ch) {

		boolean ans;
		switch (ch) {
		case 'a':
			ans = true;
			break;
		case 'e':
			ans = true;
			break;
		case 'i':
			ans = true;
			break;
		case 'o':
			ans = true;
			break;
		case 'u':
			ans = true;
			break;
		case 'A':
			ans = true;
			break;
		case 'E':
			ans = true;
			break;
		case 'I':
			ans = true;
			break;
		case 'O':
			ans = true;
			break;
		case 'U':
			ans = true;
			break;
		default:
			ans = false;
			break;
		}
		return ans;
	}

	String replaceVowels(String str, char ch) {

		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {

			if (isVowel(str.charAt(i))) {
				ans.append(ch);
			} else {
				ans.append(str.charAt(i));
			}
		}

		return ans.toString();
	}

	String emphasize(String str, char ch) {
		StringBuilder ans = new StringBuilder(str);

		for (int i = 0; i < str.length(); i++) {

			int indx = str.indexOf(ch, i);
			//System.out.println(indx + " " + i);
			if (indx != -1) {
				if (indx % 2 == 0) {
					ans.setCharAt(indx, '*');
				} else {
					ans.setCharAt(indx, '+');
				}
			}
		}

		return ans.toString();
	}

	public static void main(String[] args) {
		WordPlay play = new WordPlay();
		System.out.println("1 :" + play.replaceVowels("Banshita Gangwar", '#'));
		System.out.println("2: " + play.emphasize("Banshita Gangwar", 'a'));

	}
}
