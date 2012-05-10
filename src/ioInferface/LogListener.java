package ioInferface;

import java.util.logging.Level;

import algorithms.generation.Conf;

import logging.UseLogger;

public class LogListener extends AListener implements IListener {
	private String inputString = " ";
	private String[] possiblesLogs;

	public LogListener(String[] inputString, Conf globalConf) {
		super(globalConf);
		stringArray = removeFirstCommand(inputString);

		initPossibleLogLevels();
		startListening();
	}

	/**
	 * Possible Log shades: FINEST, FINER, FINE, CONFIG, INFO, WARNING, SEVERE
	 */
	public void initPossibleLogLevels() {
		possiblesLogs = new String[] { "OFF", "FINEST", "FINER", "FINE",
				"CONFIG", "INFO", "WARNING", "SEVERE", "ALL" };
	}

	public boolean isLogLevel(String loglevel) {
		for (int i = 0; i < possiblesLogs.length; i++) {
			if (possiblesLogs[i].contains(loglevel.toUpperCase()))
				return true;
		}
		return false;
	}

	public void startListening() {

		while (goingon) {
			System.out.print("MAIN(LOG): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			/**
			 * if the input is LOG, just clear ist
			 */
			if (stringArray[0].equals(ConsoleCommands.LOG)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(ConsoleCommands.HELP))
				getHelp();

			else if (stringArray[0].equals(ConsoleCommands.GET)) {
				getcurrentLogLevel();
				reinitializeStringArray();
			} else if (stringArray[0].equals(ConsoleCommands.SET)
					&& isLogLevel(stringArray[1])) {
				String logLevel = removeFirstCommand(stringArray)[0];
				setLogLevel(logLevel);
				stringArray = removeFirstCommand(stringArray);
			}

			else
				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	public void getcurrentLogLevel() {
		Conf.LOGSTRING = "The current Log Level is set to: " + getLogLevel();

		System.out.println(Conf.LOGSTRING);
		UseLogger.LOGGER.info(Conf.LOGSTRING);

	}

	public String getLogLevel() {
		return UseLogger.LOGGER.getLevel().toString();
	}

	public void setLogLevel(String level) {

		try {
			UseLogger.LOGGER.setLevel(Level.parse(level));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("The log level has been set successfully to '"
				+ UseLogger.LOGGER.getLevel() + "' \n");

		goingon = false;
	}

	@Override
	public void getHelp() {
		if (!isLogLevel(stringArray[0]) || inputString.isEmpty()) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}
		
		System.out.println("SET OFF:\t\t turn the Log off.");
		System.out.println("SET FINEST:\t\t Log on the FINEST Log.");
		System.out.println("SET FINER:\t\t Log on the FINERI log.");
		System.out.println("SET FINER:\t\t Log on the FINE log.");
		System.out.println("SET INFO:\t\t Log an INFO message.");
		System.out.println("SET SEVERE:\t\t Log a SEVERE message..");
		System.out.println("SET ALL:\t\t Log all messages.");
		System.out.println("QUIT:\t\t go back to main menu.");
		System.out.println("GET:\t\t Gives back the current Log Level");

		reinitializeStringArray();
	}

}