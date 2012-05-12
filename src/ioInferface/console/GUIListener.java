package ioInferface.console;


import logging.UseLogger;
import main.Conf;
import main.mazeHandler;

public class GUIListener extends AConsoleListener implements IConsoleListener {
	private String inputString = " ";
	private mazeHandler _mytest;

	public GUIListener(String[] inputString, mazeHandler test, Conf globalConf) {
		super(globalConf);
		_mytest = test;
		stringArray = removeFirstCommand(inputString);
		startListening();
	}

	public void startListening() {

		while (goingon) {
			System.out.print("MAIN(GUI): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			/**
			 * if the input is LOG, just clear ist
			 */
			if (stringArray[0].equals(ConsoleCommands.GUI)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(ConsoleCommands.HELP))
				getHelp();

			else if (stringArray[0].equals(ConsoleCommands.CREATE)) {

				try {
					_mytest.createGUI();
				} catch (Exception e) {
					Conf.LOGSTRING = "FEHLER: GUI kann nicht erstellt werden, da es noch kein Labyrinth gibt.";
					System.out.println(Conf.LOGSTRING);
					UseLogger.LOGGER.warning(Conf.LOGSTRING);

				}

			} else
				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	@Override
	public void getHelp() {
		if (inputString.isEmpty()) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}

		System.out.println("create:\t\t creates a new gui.");
		System.out
				.println("start FINEST:\t\t use the defined maze with the gui.");
		System.out.println("QUIT:\t\t go back to main menu.");
		System.out.println("GET:\t\t Gives back the current Log Level");

		reinitializeStringArray();
	}

}