package assignment_string;

public class FindingGeneSimpleandTest {

	public String findgenesimple(String dna, String startcodon,String stopcodon) {
		// start codon ATG
		// end codon TAA

		dna=dna.toUpperCase();
		startcodon=startcodon.toUpperCase();
		stopcodon=stopcodon.toUpperCase();
		String result = "";

		int startindex = dna.indexOf(startcodon);
		if (startindex == -1) { // -1 if no ATG is found
			return "";

		}
		int stopindex = dna.indexOf(stopcodon, startindex + 3); // startindex+3= because atg has length of 3
		if (stopindex == -1) { // -1 if no TAA is found
			return "";
		}
		if ((stopindex-startindex)%3 !=0) {
			return "";
		}
		
		result = dna.substring(startindex, stopindex + 3); // endsindex+3=beacuse taa has length of 3

		return result;
	}

	public static void main(String[] args) {

		FindingGeneSimpleandTest test = new FindingGeneSimpleandTest();

		String dna = "AAATGCCCTAACTAGATTAAGAAACC";
		System.out.println("dna string is " + dna);
		System.out.println("gene string is " + test.findgenesimple(dna,"atg","taa"));

	/*	dna = "ATGCGTCACTGACAGTAATACCAGG";
		System.out.println("dna string is " + dna);
		System.out.println("gene string is " + test.findgenesimple(dna));

		dna = "AGCTAGCAATGCGTCACTAGACAGTTAACGTCAATTAA";
		System.out.println("dna string is " + dna);
		System.out.println("gene string is " + test.findgenesimple(dna));

		dna = "ATGTGGATA";
		System.out.println("dna string is " + dna);
		System.out.println("gene string is " + test.findgenesimple(dna));
*/
	}

}
