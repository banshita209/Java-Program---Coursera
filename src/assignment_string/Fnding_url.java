package assignment_string;

import edu.duke.*;

public class Fnding_url {

	public static void main(String[] args) {
		URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		for (String item : file.words()) {
			String itemLower = item.toLowerCase();

			int pos = itemLower.indexOf("youtube.com");
			if (pos != -1) {
				// System.out.println(itemLower);
				int beg = itemLower.lastIndexOf("\"",pos);
				int end = itemLower.indexOf("\"", pos+1);
				System.out.println(itemLower.substring(beg+1,end));

				//System.out.println(itemLower.substring(beg + 1, end));
			}
		}
	}
}
