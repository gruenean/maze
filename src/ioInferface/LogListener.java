package ioInferface;

import java.util.logging.Level;

import logging.UseLogger;

public class LogListener extends AListener implements IListener {
	private String inputString = " ";
	private String[] possiblesLogs;

	public LogListener(String[] inputString) {
		super();
		stringArray = removeFirstCommand(inputString);

		// System.out.println("\nWelcome in the Log-Menu\n--------------------------");
		initPossibleLogLevels();
		startListening();
	}

	public void initPossibleLogLevels() {
		possiblesLogs = new String[] { "ALL", "SEVERE", "OFF", "INFO" };
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
			if (stringArray[0].equals(Commands.LOG)){
				stringArray = removeFirstCommand(stringArray);
			if(stringArray.length==0) reinitializeStringArray();	
			}

			if (stringArray[0].equals(Commands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(Commands.HELP))
				getHelp();

			else if (stringArray[0].equals(Commands.GET)) {
				getcurrentLogLevel();
				reinitializeStringArray();
			} else if (stringArray[0].equals(Commands.SET)
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

		System.out.println("The current Log Level is set to: " + getLogLevel());

	}

	public String getLogLevel() {
		return UseLogger.LOGGER.getLevel().toString();
	}

	public void setLogLevel(String level) {

		try {
			UseLogger.LOGGER.setLevel(Level.parse(level));

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Mšgliche Log-Level: ");
		}
		System.out.println("The log level has been set successfully to '"
				+ UseLogger.LOGGER.getLevel() + "' \n");
		// System.out.println("string = " + stringArray[0]);
	}

	@Override
	public void getHelp() {
		if (!isLogLevel(stringArray[0]) || inputString.isEmpty()) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}
		System.out.println("INFO:\t\t Log an INFO message.");
		System.out.println("ALL:\t\t Log all messages.");
		System.out.println("OFF:\t\t turn the Log off.");
		System.out.println("SEVERE:\t\t Log a SEVERE message..");
		System.out.println("QUIT:\t\t go back to main menu.");
		System.out.println("GET:\t\t Gives back the current Log Level");

		reinitializeStringArray();
	}

}