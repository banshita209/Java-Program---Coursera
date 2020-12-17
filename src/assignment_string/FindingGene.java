package assignment_string;

public class FindingGene {
	
	public String findgenesimple(String dna) {
		String result = "";

		int startindex = dna.indexOf("ATG");
		if (startindex == -1) { // -1 if no ATG is found
			return "";
		}
		int stopindex = dna.indexOf("TAA", startindex + 3); // startindex+3= because atg has length of 3
		if (stopindex == -1) { // -1 if no TAA is found
			return "";
		}
		if ((stopindex - startindex) % 3 != 0) {
			return "";
		}

		result = dna.substring(startindex, stopindex + 3); // endsindex+3=beacuse taa has length of 3

		return result;
	}

}
