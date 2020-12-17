package assignment_string;

public class FindingGene_loop {

	public String findgenesimple(String dna) {
		String result = "";

		int startindex = dna.indexOf("ATG");
	/*	if (startindex == -1) { // -1 if no ATG is found
			return "";
		}*/
		int currindex = dna.indexOf("TAA", startindex + 3); // startindex+3= because atg has length of 3

		while(currindex != -1) {
			if (((currindex - startindex) % 3) == 0) {
				result = dna.substring(startindex,currindex+3);
				return result;
			}
			else {
				currindex =dna.indexOf("TAA",currindex+3);
			}
			
		}
		
		
		/*	if (stopindex == -1) { // -1 if no TAA is found
			return "";
		}
		if ((stopindex - startindex) % 3 != 0) {
			return "";
		}
*/
		//result = dna.substring(startindex, currindex + 3); // endsindex+3=beacuse taa has length of 3

		return "";
	}
	
	public static void main(String[] args) {
		FindingGene_loop test=new FindingGene_loop();
		
		String dna = "ATGCCTAACTAAGATTAAGAAACC";
		System.out.println("dna string is " + dna);
		System.out.println("gene string is " + test.findgenesimple(dna));

	}
	
}
