package Mini_project_babynames;

import java.io.File;
import java.util.Scanner;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class BabyBirths {

	void printNames() {
		FileResource resource = new FileResource();
		for (CSVRecord record : resource.getCSVParser(false)) {

			System.out.println("Name :" + record.get(0));
			System.out.println("gender :" + record.get(1));

		}
	}

	public void totalBirth(FileResource f) {
		int total = 0, total_Boys = 0, totalGirls = 0;
		int girls_name = 0, boys_name = 0;
		for (CSVRecord record : f.getCSVParser(false)) {

			int num = Integer.parseInt(record.get(2));
			total += num;
			if (record.get(1).equals("F")) {
				totalGirls += Integer.parseInt(record.get(2));
				girls_name++;
			} else {
				total_Boys += Integer.parseInt(record.get(2));
				boys_name++;
			}
		}
		System.out.println("total no of babies :" + total);
		System.out.println("total no girls :" + totalGirls);
		System.out.println("total no boys :" + total_Boys);

		System.out.println("Total girls' name :" + girls_name);
		System.out.println("Total boys' name :" + boys_name);
	}

	public int rankOntheBasisName(String name, String gender, FileResource resource) {
		int count = 0;
		// FileResource resource=new FileResource();

		for (CSVRecord record : resource.getCSVParser(false)) {
			if (gender.equals(record.get(1))) {
				count++;
				if (name.equals(record.get(0))) {
					break;
				}
			}
		}
		System.out.println("Rank of " + name + " is " + count);
		return count;

	}

	public String nameOnbasisOfRank(int rank, String gender, FileResource resource) {

		int count = 0;
		String name = null;
		for (CSVRecord record : resource.getCSVParser(false)) {

			if (count < rank && record.get(1).equals(gender)) {
				count++;
			}
			if (count == rank) {
				name = record.get(0);
				System.out.println("name on rank " + rank + " is " + name);
				break;
			}
		}
		return name;

	}

	public void whatsThename() {

		String path = "C:\\Users\\BANSHITA\\Downloads\\us_babynames_by_year\\yob";
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter name :");
		String name = scanner.next();
		System.out.println("Enter gender (F/M):");
		String gender = scanner.next();
		System.out.println("Enter the year of birth :");
		String yr_birth = scanner.next();

		FileResource resource = new FileResource(new File(path + yr_birth + ".csv"));

		int rank = rankOntheBasisName(name, gender, resource);

		System.out.println("Enter the year in which u want your name :");
		String new_yrs = scanner.next();

		resource = new FileResource(new File(path + new_yrs + ".csv"));

		String new_name = nameOnbasisOfRank(rank, gender, resource);

		System.out.println("new Name :" + new_name);

	}

	void whats_rank_in_years() {
		int highest = 0, rank = 0, year = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter begining years :");
		int beg_yrs = scanner.nextInt();
		System.out.println("Enter ending years :");
		int end_yrs = scanner.nextInt();
		System.out.println("Enter name:");
		String name = scanner.next();
		System.out.println("Enter gender :");
		String gender = scanner.next();

		String path = "C:\\Users\\BANSHITA\\Downloads\\us_babynames_by_year\\yob";
		FileResource resource;
		for (int i = beg_yrs; i <= end_yrs; i++) {
			resource = new FileResource(new File(path) + String.valueOf(i) + ".csv");
			// resource = new FileResource(new
			// File("C:\\Users\\BANSHITA\\Downloads\\us_babynames_test\\example-small.csv"));
			rank = rankOntheBasisName(name, gender, resource);
			if (highest == 0) {
				highest = rank;
				continue;
			}

			if (rank < highest) {
				// System.out.println("ranK:"+rank);
				highest = rank;
				// System.out.println(highest+" "+i);
				year = i;
			}
		}
		System.out.println("name : " + name + " have highest rank of " + highest + " on year " + year);
	}

	public void averageRank() {

		int rank = 0, count = 0;
		double avg, total = 0;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter begining years :");
		int beg_yrs = scanner.nextInt();
		System.out.println("Enter ending years :");
		int end_yrs = scanner.nextInt();
		System.out.println("Enter name:");
		String name = scanner.next();
		System.out.println("Enter gender :");
		String gender = scanner.next();

		String path = "C:\\Users\\BANSHITA\\Downloads\\us_babynames_by_year\\yob";
		FileResource resource;
		for (int i = beg_yrs; i <= end_yrs; i++) {
			resource = new FileResource(new File(path) + String.valueOf(i) + ".csv");
			rank = rankOntheBasisName(name, gender, resource);

			count++;
			total += rank;

		}
		avg = total / count;
		System.out.println("average rank of " + name + " is " + avg);

	}

	void rankHigherthan(String name, String gender, String yrs) {

		FileResource fileResource = new FileResource(
				new File("C:\\Users\\BANSHITA\\Downloads\\us_babynames_by_year\\yob" + yrs + ".csv"));
		// FileResource fileResource=new FileResource(new
		// File("C:\\Users\\BANSHITA\\Downloads\\us_babynames_test\\example-small.csv"));
		int rank = rankOntheBasisName(name, gender, fileResource);

		int count = 0;
		int curr_rank=0;
		for (CSVRecord record : fileResource.getCSVParser(false)) {
			//System.out.println(curr_rank);
			
			//System.out.println(Integer.parseInt(record.get(2))+" "+record.get(1));
			String g=record.get(1);
			if (gender.equals(g)) {
				curr_rank=curr_rank+1;
				//System.out.println(rank+" "+r);
				if (curr_rank < rank) {
				//	System.out.println(Integer.parseInt(record.get(2))+" "+record.get(1));
					count = count + Integer.parseInt(record.get(2));
				} /*else {
					continue;
				}*/
			}
		}
		System.out.println("born with rank higher than " + rank + " are " + count);

	}

	public static void main(String[] args) {

		BabyBirths births = new BabyBirths();
		// births.printNames();

		// FileResource fileResource = new
		// FileResource("C:\\Users\\BANSHITA\\Downloads\\us_babynames_by_year\\yob1982.csv");
		// births.totalBirth(fileResource);

		// births.rankOntheBasisName("Emily", "F", fileResource);

		// births.nameOnbasisOfRank(450, "M", fileResource);

		// births.whatsThename();
		births.whats_rank_in_years();

		// births.averageRank();

		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Enter name:");
		// String name = scanner.next();
		 //System.out.println("Enter gender :");
		// String gender = scanner.next();
		// System.out.println("Enter born years :");
		 //String yrs = scanner.next();

		// births.rankHigherthan(name, gender, yrs);
	}

}
