package Interfaces.console;

import main.Conf;

public class ModusListener extends AConsoleListener {
	private String inputString = " ";

	public ModusListener(Conf globalConf) {
		super(globalConf);

	}

	public void startListening(String[] input) {
		goingon = true;
		stringArray = input;
		String modusString = null;

		while (goingon) {
			_myoutput.print("MAIN(MODUS): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			if (stringArray[0].equals(ConsoleCommands.SET)) {
				_myoutput.printLine(stringArray[1]);
				if (stringArray[1].equals(ConsoleCommands.STEP))
					_globalConf.setSTEPMODUS(true);
				if (stringArray[1].equals(ConsoleCommands.RUN))
					_globalConf.setSTEPMODUS(false);
				goingon = false;
			}

			if (stringArray[0].equals(ConsoleCommands.GET)) {
				if (_globalConf.isSTEPMODUS()) {
					modusString = "STEP";
					goingon = false;
				} else {
					modusString = "RUN";
					goingon = false;
				}
				_myoutput.printLine("The current MODUS is : " + modusString);
			}

			else if (stringArray[0].equals(ConsoleCommands.HELP)
					|| stringArray.length == 0 || !isvalidInput())
				getHelp();

			stringArray = new String[] {};

		}
	}

	@Override
	public void getHelp() {
		if (inputString.isEmpty()
				|| !stringArray[0].equals(ConsoleCommands.HELP)) {

			_myoutput
					.printLine("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");

		}
		_myoutput
				.printLine(ConsoleCommands.SET
						+ " "
						+ ConsoleCommands.STEP
						+ ":\t changes the modus to STEP. This means every step will be shown.");
		_myoutput
				.printLine(ConsoleCommands.SET
						+ " "
						+ ConsoleCommands.RUN
						+ ":\t changes the modus to RUN. This means just the result will be shown.");
		_myoutput.printLine(ConsoleCommands.GET
				+ ":\t\t will show you the current Modus");

		_myoutput.printLine(ConsoleCommands.QUIT
				+ ":\t\t go back to main menu.");
		goingon = true;
		reinitializeStringArray();
	}

	private boolean isvalidInput() {

		if (stringArray[0].equals(ConsoleCommands.GET))
			return true;
		if (stringArray[0].equals(ConsoleCommands.SET)) {

			if (stringArray[1].equals(ConsoleCommands.STEP))
				return true;
			if (stringArray[1].equals(ConsoleCommands.RUN))
				return true;

		}

		return false;
	}

	{

	}

	@Override
	public String getCommand() {
		return "MODUS";
	}

}