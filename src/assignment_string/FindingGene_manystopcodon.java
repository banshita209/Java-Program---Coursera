package assignment_string;

public class FindingGene_manystopcodon {

	int findStopCodon(String dnastr, int startindex, String stopcodon) {

		int currindex = dnastr.indexOf(stopcodon, startindex + 3);
		//System.out.println(currindex);
		while (currindex != -1) {

			if ((currindex - startindex) % 3 == 0) {

				return currindex;
				
			} else {
				currindex = dnastr.indexOf(stopcodon, currindex + 1);
			}
		}
		return -1;
	}

	String findGene(String dna) {

		

		int startindex = dna.indexOf("ATG");

		if (startindex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startindex, "TAA");
		int tgaIndex = findStopCodon(dna, startindex, "TGA");
		int tagIndex = findStopCodon(dna, startindex, "TAG");

		//System.out.println(taaIndex + " " + tagIndex + " " + tgaIndex);
/*
		int stopindex = Math.min(Math.min(taaIndex, tgaIndex), tagIndex);
		if (stopindex == dna.length()) {
			return "";
		}*/
		int stopindex ;
		if (taaIndex ==-1 || tgaIndex !=-1 && tagIndex < taaIndex) {
			stopindex=tgaIndex;
		} else {
			stopindex =taaIndex;
		}
		if (stopindex ==-1|| tagIndex !=-1 && tagIndex < stopindex)
		{
			stopindex=tagIndex;
		}
		return dna.substring(startindex, stopindex + 3);
	}

	public static void main(String[] args) {
		FindingGene_manystopcodon manystopcodon = new FindingGene_manystopcodon();

		String dna = "AATGCCTAACGTAATATAGTAAATATGAC";

		System.out.println("Gene is :" + manystopcodon.findGene(dna));

	}

}
