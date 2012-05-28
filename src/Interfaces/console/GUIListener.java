package Interfaces.console;

import logging.UseLogger;
import main.Conf;
import main._mazeHandler;

public class GUIListener extends AConsoleListener {
	private String inputString = " ";

	private _mazeHandler _mymaze;

	public GUIListener(Conf globalConf, _mazeHandler mymaze) {
		super(globalConf);
		_mymaze = mymaze;
	}

	public void startListening(String[] string) {
		stringArray = string;

		while (goingon) {
			_myoutput.printLine("MAIN(GUI): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(ConsoleCommands.HELP))
				getHelp();

			else if (stringArray[0].equals(ConsoleCommands.CREATE)) {
				//_mymaze.getMaze().createEinundAusgang();
				try {

					_mymaze.createGUI();
				} catch (Exception e) {
					Conf.LOGSTRING = "FEHLER: GUI kann nicht erstellt werden, da es noch kein Labyrinth gibt.";
					_myoutput.printLine(Conf.LOGSTRING);
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

			_myoutput
					.printLine("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");

		}

		_myoutput.printLine("create:\t\t creates a new gui.");
		_myoutput.printLine("QUIT:\t\t go back to main menu.");

		reinitializeStringArray();
	}

	@Override
	public String getCommand() {
		return "GUI";
	}

}