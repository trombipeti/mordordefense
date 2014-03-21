package mordordefense.testing;

import java.io.*;

public class Logger {
	PrintWriter writer;

	String logFile;

	public Logger() {
		writer = new PrintWriter(new OutputStreamWriter(System.out));
		logFile = null;
	}

	public Logger(Writer out) {
		writer = new PrintWriter(out);
		logFile = null;
	}

	public Logger(String outFileName) {
		logFile = outFileName;
	}

	public void log(String message) throws IOException {
		if (logFile == null) {
			writer.println(message);
			writer.flush();
		} else {
			writer = new PrintWriter(new FileWriter(logFile));
			writer.println(message);
			writer.close();
		}
	}
}
