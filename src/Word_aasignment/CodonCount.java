package Word_aasignment;

import java.io.File;
import java.util.HashMap;

import edu.duke.FileResource;

public class CodonCount {

	HashMap<String, Integer> codon;

	public CodonCount() {
		codon = new HashMap<>();
	}

	void buildCodonMap(int start, String dna) {

		int unquie = 0;
		int end = 0;
		String str_codon;
		while (true) {
			end = start + 3;

			if (end >= dna.length()) {
				System.out.println("Break");
				break;
			}
			str_codon = dna.substring(start, end);
			if (codon.containsKey(str_codon)) {

				codon.replace(str_codon, codon.get(str_codon) + 1);

			} else {
				codon.put(str_codon, 1);
			}
			// System.out.println(start + " " + end);
			start = end;
		}

		for (String str : codon.keySet()) {
			// if (codon.get(str) == 1) {
			unquie++;
			// }
			//System.out.println(str + " \t " + codon.get(str));
			//if (codon.get(str) == 7) {
				System.out.println(str + " \t " + codon.get(str));
		//	}
		}
		System.out.println("unique =" + unquie);
		codon.clear();
	}

	public static void main(String[] args) {

		File file = new File("C:\\Users\\BANSHITA\\Downloads\\dnaMystery2.txt");
		FileResource fileResource = new FileResource(file);
		String dna = fileResource.asString();
		CodonCount codonCount = new CodonCount();
		System.out.println(dna + " " + dna.length());
		codonCount.buildCodonMap(0, dna);
		// codonCount.buildCodonMap(1, dna);
		// codonCount.buildCodonMap(2, dna);
	}
}
