package logging;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UseLogger {
	public final static Logger LOGGER = Logger.getLogger(UseLogger.class
			.getName());

	/**
	 * Deprecated IS THIS USED?! 28.05.2012 / Micha
	 */
	public void writeLog() {
		LOGGER.setLevel(Level.ALL);
		LOGGER.finest("First message \n --------------------------- \n");

	}

	public static void main(String[] args) {
		UseLogger logger = new UseLogger();
		try {
			MyLogger.setup();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problems with creating the log files");
		}
		logger.writeLog();
	}
}
