package RandomTextStarterProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
	private int myOrder;
	private HashMap<String, ArrayList<String>> map;

	public EfficientMarkovModel(int order) {
		myOrder = order;
		map = new HashMap<String, ArrayList<String>>();
	}

	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	public void buildMap() {
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		/*while (pos < this.myText.length()) {
			int start = this.myText.indexOf(key, pos);
			if (start == -1) {
				break;
			}
			if (start + key.length() >= myText.length() - myOrder) {
				break;
			}
			String next = myText.substring(start + key.length(), start + key.length() + 1);
			follows.add(next);
			map.put(key, follows);
			pos = start + key.length();
		}*/
		while (pos<  this.myText.length()) {
			String key=this.myText.substring(pos, pos+myOrder);
			int index=this.myText.indexOf(key);
			
			if (index == -1 || (index + key.length() >= myText.length())) {
				break;
			}
			String next = this.myText.substring(index+key.length(), index+key.length()+1);
			if (map.containsKey(key)) {
				map.get(key).add(next);
			}else {
				ArrayList<String> lst=new ArrayList<>();
				lst.add(next);
				map.put(key,lst);
			}
		pos=pos+key.length();
		}
		
		System.out.println("size ="+map.size());
	}

	public ArrayList<String> getFollows2(String key) {

		
		if (!map.containsKey(key) || map.containsKey(key)) {
			// System.out.println("key : "+key+" key size"+key.length());
			
		}
		ArrayList<String> follows = map.get(key);
		return follows;
	}

	public String getRandomText(int numChars) {
		StringBuilder sb = new StringBuilder();
		
		/*int index = myRandom.nextInt(myText.length() - myOrder);*/
		int index =0;
		String key = myText.substring(index, index + myOrder);
		System.out.println("index="+index);
		
		
		sb.append(key);
		
		buildMap();
		/*for (int i = 0; i < numChars-myOrder; i++) {
			//if (!map.containsKey(key) || map.containsKey(key)) {
				// System.out.println("key : "+key+" key size"+key.length());
				//buildMap(key);
			//}
			System.out.println("key ="+key);
			ArrayList<String> follows = getFollows2(key);
			System.out.println("follow ="+follows);
			if (follows == null || follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;

		}
		printHashMapInfo();
		System.out.println("size of hashmap = " + map.size());*/
		return sb.toString();
	}

	public void printHashMapInfo() {
		for (String key : map.keySet()) {
			// System.out.println(key + ": " + map.get(key));
		}
		System.out.println("number of keys :" + map.size());

		int max = 0;
		for (String key : map.keySet()) {
			int count = map.get(key).size();
			if (count > max) {
				max = count;
			}
		}
		System.out.println("Maximum key" + max);

		for (String key : map.keySet()) {
			int count = map.get(key).size();
			if (count == max) {
				System.out.println(key + " = " + map.get(key));
			}
		}

	}

	public String toString() {
		return "MarkovModel of order " + String.valueOf(myOrder);
	}
}