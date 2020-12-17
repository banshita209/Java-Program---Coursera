package assignment_string;

public class FindString {
	
	int findindString(String str_destination, String find) {
		
		int startindex=0, count=0;
		
		int pos = str_destination.indexOf(find);
		while (pos >= 0) {
		  count = count + 1;
		  pos = str_destination.indexOf(find,pos+find.length());
		}
		System.out.println(count);
		return count;
				
	}
	
	public static void main(String[] args) {
		FindString string =new FindString();
		
		string.findindString("atgtsntatatatnkdcktdjubstatanccjndksbcsnvxjksbatmcdnvcatcjkcndgatnjdnat", "at");
	}

}
