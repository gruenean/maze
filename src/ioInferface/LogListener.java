package ioInferface;

import java.util.logging.Level;

import logging.UseLogger;

public class LogListener extends AListener implements IListener {
	private String inputString = " ";

	public LogListener() {
		super();
		System.out.println("Welcome in the Log-Menu");
		startListening();
	}

	public void startListening() {

		while (goingon) {
			inputString = _in.nextLine();
			if (inputString.contains("quit")) {
				quit();
				System.out.println(goingon);
			}

			else if (inputString.contains("help"))
				getHelp();

			else
				setLogLevel(inputString);

		}
		// _in.close();

	}

	public static void setLogLevel(String level) {

		try {
			UseLogger.LOGGER.setLevel(Level.parse(level));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Mšgliche Log-Level: ");
		}
		System.out.println(UseLogger.LOGGER.getLevel());

	}

	@Override
	public void getHelp() {
		System.out.println("Mšgliche LOG Level:\n SEVERE, INFO, ALL, OFF...");

	}

}