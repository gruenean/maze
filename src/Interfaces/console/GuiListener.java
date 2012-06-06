package Interfaces.console;

import main.Conf;

public class GuiListener extends AConsoleListener {

	public GuiListener(Conf globalConf) {
		super(globalConf);
	}

	/* (non-Javadoc)
	 * @see Interfaces.console.IConsoleListener#startListening(java.lang.String[])
	 */
	public void startListening(String[] input) {
		goingon = true;
		stringArray = input;
		//TODO: this is redundant, isn't it?!  
		//_globalConf.getMazeHandler().createNewGui();
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.console.IConsoleListener#getHelp()
	 */
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

	/* (non-Javadoc)
	 * @see Interfaces.console.IConsoleListener#getCommand()
	 */
	@Override
	public String getCommand() {
		return "GUI";
	}
}