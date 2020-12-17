package Log_files;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.FileResource;

public class LogAnalyzer {

	ArrayList<LogEntry> logEntries;

	public LogAnalyzer() {
		logEntries = new ArrayList<>();
	}

	void readFiles() {
		File file = new File("C:\\Users\\BANSHITA\\Downloads\\WebLogProgram\\weblog2_log");
		FileResource resource = new FileResource(file);

		for (String data : resource.lines()) {
			LogEntry entry = WebLogParser.parseEntry(data);
			logEntries.add(entry);
		}
		// printAll();
	}

	void printAll() {
		for (LogEntry logEntry : logEntries) {
			System.out.println(logEntry.toString());
		}
	}

	ArrayList<LogEntry> countUniqueIPs() {
		ArrayList<String> ip = new ArrayList<>();
		ArrayList<LogEntry> ipBasedEntery = new ArrayList<>();

		for (LogEntry entry : logEntries) {
			String ipAdd = entry.getIpAddress();

			if (ip.contains(ipAdd)) {
				continue;
			} else {
				ip.add(ipAdd);
				ipBasedEntery.add(entry);
			}
		}

		System.out.println("uique ip address are" + ip.size());
		return ipBasedEntery;
	}

	void printAllHigherThanNum(int num) {

		int count = 0;
		for (LogEntry entry : logEntries) {
			int status = entry.getStatusCode();

			if (status > num) {
				System.out.println(entry);
				count++;
			}
		}
		System.out.println("higher than" + count);
	}

	void uniqueIPVisitsOnDay(String someday) {

		ArrayList<String> Ipaddress = new ArrayList<>();
		int count = 0;
		for (LogEntry entry : logEntries) {

			String date = entry.getAccesstime().toString();
			String ip = entry.getIpAddress();
			// date=date.substring(4, 11);
			// System.out.println(date);
			if (date.contains(someday) && !Ipaddress.contains(ip)) {
				Ipaddress.add(ip);
				System.out.println(entry);
				count++;
			}

		}
		System.out.println("Count of entries with date :" + count);

	}

	void countUniqueIPsInRange(int low, int high) {

		ArrayList<String> Ipaddress = new ArrayList<>();
		int count = 0;
		for (LogEntry logEntry : logEntries) {
			int code = logEntry.getStatusCode();
			String ip = logEntry.getIpAddress();

			if ((low <= code && high >= code) && !Ipaddress.contains(ip)) {
				System.out.println(logEntry);
				Ipaddress.add(ip);
				count++;
			}
		}
		System.out.println("Count of the between unique ip " + count);

	}

	HashMap<String, Integer> countVisitsPerIP() {

		System.out.println("countVisitsPerIP() method");
		HashMap<String, Integer> count = new HashMap<>();

		for (LogEntry logEntry : logEntries) {
			String ip = logEntry.getIpAddress();

			if (count.containsKey(ip)) {

				count.put(ip, count.get(ip) + 1);
			} else {
				count.put(ip, 1);
			}
		}

		for (String c : count.keySet()) {
			System.out.println(c + "\t" + count.get(c));
		}

		System.out.println("count " + count.size());
		return count;

	}

	void mostNumberVisitsByIP() {

		HashMap<String, Integer> counts = countVisitsPerIP();

		int max = 0;
		String maxIP = null;

		for (String count : counts.keySet()) {

			int times = counts.get(count);

			if (max < times) {
				max = times;
				maxIP = count;
			}
		}

		System.out.println("most occured IP is " + maxIP + " it occured " + max + "times. ");
	}

	HashMap<String, ArrayList<String>> iPsForDays() {
		HashMap<String, ArrayList<String>> dayToIP = new HashMap<>();

		for (LogEntry logEntry : logEntries) {
			String date = logEntry.getAccesstime().toString().substring(4, 11);

			if (dayToIP.containsKey(date)) {
				/*
				 * if (dayToIP.get(date).contains(logEntry.getIpAddress())) { continue; }
				 */

				dayToIP.get(date).add(logEntry.getIpAddress());

			} else {
				dayToIP.put(date, new ArrayList<>());
				dayToIP.get(date).add(logEntry.getIpAddress());
			}
		}

		for (String day : dayToIP.keySet()) {

			System.out.println(day + "\t" + dayToIP.get(day));
			System.out.println("no of ip:" + dayToIP.get(day).size());

		}
		System.out.println("Size of day to ip:" + dayToIP.size());

		return dayToIP;
	}

	void dayWithMostIPVisits() {
		HashMap<String, ArrayList<String>> daysToip = iPsForDays();

		int max = 0;
		String maxVisited = null;
		for (String day : daysToip.keySet()) {
			int size = daysToip.get(day).size();

			if (max < size) {
				max = size;
				maxVisited = day;
			}
		}

		System.out.println("Maximun visited day is " + maxVisited + " with " + max + " visits");
	}

	void iPsWithMostVisitsOnDay(String askedDay) {

		HashMap<String, ArrayList<String>> daysToip = iPsForDays();

		int count = 0;

		HashMap<String, Integer> ipCount = new HashMap<>();
		for (String day : daysToip.keySet()) {

			if (day.contains(askedDay)) {
				ArrayList<String> ips = daysToip.get(day);

				for (String ip : ips) {

					if (ipCount.containsKey(ip)) {
						int value = ipCount.get(ip) + 1;

						ipCount.put(ip, value);
						count = (count < value) ? value : count;
					} else {
						ipCount.put(ip, 1);
					}
				}
			}
		}
		System.out.println(count);

		// HashMap<String, Integer> ipss = countVisitsPerIP();
		for (String ip : ipCount.keySet()) {

			if (ipCount.get(ip) == count) {
				System.out.println(ip + "\t" + ipCount.get(ip));
			}
			// System.out.println(ip + "\t" + ipss.get(ip));
		}
	}

	public static void main(String[] args) {
		LogAnalyzer analyzer = new LogAnalyzer();
		analyzer.readFiles();
		// analyzer.printAllHigherThanNum(400);
		// analyzer.uniqueIPVisitsOnDay("Sep 27");
		// analyzer.countUniqueIPsInRange(400, 499);
		// analyzer.countVisitsPerIP();
		// analyzer.mostNumberVisitsByIP();
		// analyzer.iPsForDays();
		analyzer.iPsWithMostVisitsOnDay("Sep 29");

		// System.out.println("unquie ="+analyzer.countUniqueIPs().size());
	}
}
