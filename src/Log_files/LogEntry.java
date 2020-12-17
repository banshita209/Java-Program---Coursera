package Log_files;

import java.util.Date;

public class LogEntry {

	private String ipAddress;
	private Date accesstime;
	private String request;
	private int statusCode;
	private int bytesReturned;

	public LogEntry(String ip, Date time, String req, int status, int bytes) {
		ipAddress = ip;
		accesstime = time;
		request = req;
		statusCode = status;
		bytesReturned = bytes;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public Date getAccesstime() {
		return accesstime;
	}

	public String getRequest() {
		return request;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public int getBytesReturned() {
		return bytesReturned;
	}

	@Override
	public String toString() {
		return "LogEntry [ipAddress=" + ipAddress + ", accesstime=" + accesstime + ", request=" + request
				+ ", statusCode=" + statusCode + ", bytesReturned=" + bytesReturned + "]";
	}
	
	public static void main(String[] args) {
		LogEntry logEntry=new LogEntry("1.2.2.2", new Date(), "example request", 200, 100);
		System.out.println(logEntry);
	}
}
