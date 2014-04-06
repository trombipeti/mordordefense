package mordordefense.testing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logging {

	private static String logFileName = null;

	public static void log(String message, String file) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(file,true));
		pw.println(message);
		pw.close();
	}

	public static void log(String message) {
		if (logFileName == null) {
			System.out.println(message);
		} else {
			try {
				Logging.log(message, logFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getLogFileName() {
		return logFileName;
	}

	public static void setLogFileName(String logFileName) {
		Logging.logFileName = logFileName;
	}
}
