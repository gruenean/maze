package logging;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UseLogger {
	// Always use the classname, this way you can refactor
	public final static Logger LOGGER = Logger.getLogger(UseLogger.class
			.getName());

	
	public void writeLog() {
		// Set the LogLevel to Severe, only severe Messages will be written
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
