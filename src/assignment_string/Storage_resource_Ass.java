package assignment_string;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Gene {

	int findStopCodon(String dnastr, int startindex, String stopcodon) {

		int currindex = dnastr.indexOf(stopcodon, startindex + 3);

		//System.out.println(currindex);

		while (currindex != -1) {
			int diff = currindex - startindex;
			if (diff % 3 == 0) {
				return currindex;
			} else {
				currindex = dnastr.indexOf(stopcodon, currindex + 1);
			}
		}
		return dnastr.length();
	}

	String findGene(String dna, int where) {

		int startindex = dna.indexOf("atg", where);
		
		//System.out.println("start" + startindex);
		if (startindex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startindex, "taa");
		int tgaIndex = findStopCodon(dna, startindex, "tga");
		int tagIndex = findStopCodon(dna, startindex, "tag");

		int stopindex = 0;

		/*if (taaIndex == -1 || tgaIndex != -1 && tgaIndex < taaIndex) {
			stopindex = tgaIndex;
		} else {
			stopindex = taaIndex;
		}
		if (stopindex == -1 || tagIndex != -1 && tagIndex < stopindex) {
			stopindex = tagIndex;
		}*/
		stopindex=Math.min(tagIndex, Math.min(tgaIndex, taaIndex));

		if (stopindex == dna.length()) {
			return "";
		}
		return dna.substring(startindex, stopindex + 3);
	}

	// --------------------

	ArrayList<String> printAllGene(String dna) {
		ArrayList<String> gene = new ArrayList<>();
		int startIndex = 0, count = 0;
		while (true) {
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty()) {
				System.out.println("total gene found " + count);
				break;
			}
			count++;
			gene.add(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();

		}
		return gene;
	}
}

public class Storage_resource_Ass {
	public static void main(String[] args) {

		Gene gene = new Gene();
		ArrayList<String> genelist = new ArrayList<>();
		File file = new File("C:\\Users\\BANSHITA\\Downloads\\GRch38dnapart.fa");

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			System.out.println("opeining file");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;

		// while (scanner.hasNext()) {

		// System.out.println(scanner.next());
		// count++;
		genelist = gene.printAllGene(scanner.nextLine());
		System.out.println("Complete");
		// }

		// System.out.println(count);

		for (String string : genelist) {
			System.out.println(string);
		}

	}
}
