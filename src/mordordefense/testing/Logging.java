package mordordefense.testing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logging {

	private static String logFileName = null;

	private static int logLevel;

	private static int indentLevel = 0;

	public static void log(int level, String message, String file)
			throws IOException {
		if (message.startsWith("<<")) {
			--indentLevel;
		}
		if (level <= logLevel) {
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			pw.println(message);
			pw.close();
		}
		if (message.startsWith(">>")) {
			++indentLevel;
		}
	}

	public static void log(int level, String message) {
		if (message.startsWith("<<")) {
			--indentLevel;
		}
		if (level <= logLevel) {
			if (logFileName == null) {
				for (int i = 0; i < indentLevel; ++i) {
					System.out.print(" ");
				}
				System.out.println(message);
			} else {
				try {
					Logging.log(level, message, logFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (message.startsWith(">>")) {
			++indentLevel;
		}
	}

	public static String getLogFileName() {
		return logFileName;
	}

	public static void setLogFileName(String logFileName) {
		Logging.logFileName = logFileName;
	}

	public static void setLogLevel(int l) {
		logLevel = l;
	}
}
