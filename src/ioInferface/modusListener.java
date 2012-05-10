package ioInferface;

import algorithms.generation.Conf;

public class ModusListener extends AListener implements IListener {
	private String inputString = " ";

	public ModusListener(String[] inputString, Conf globalConf) {
		super(globalConf);

		stringArray = removeFirstCommand(inputString);

		startListening();
	}

	public void startListening() {
		String modusString = null;
		while (goingon) {
			System.out.print("MAIN(MODUS): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			/**
			 * if the input is MODUS, just clear ist
			 */
			if (stringArray[0].equals(ConsoleCommands.MODUS)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(ConsoleCommands.SET)) {
				System.out.println(stringArray[1]);
				if (stringArray[1].equals(ConsoleCommands.STEP))
					globalConf.setSTEPMODUS(true);
				if (stringArray[1].equals(ConsoleCommands.RUN))
					globalConf.setSTEPMODUS(false);
				goingon = false;
			}

			if (stringArray[0].equals(ConsoleCommands.GET)) {
				if (globalConf.isSTEPMODUS()) {
					modusString = "STEP";
					goingon = false;
				} else {
					modusString = "RUN";
					goingon = false;
				}
				System.out.println("The current MODUS is : " + modusString);
			}

			else if (stringArray[0].equals(ConsoleCommands.HELP)
					|| stringArray.length == 0 || !isvalidInput())
				getHelp();

			// else
			// getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	@Override
	public void getHelp() {
		if (inputString.isEmpty()
				|| !stringArray[0].equals(ConsoleCommands.HELP)) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}
		System.out
				.println(ConsoleCommands.SET
						+ " "
						+ ConsoleCommands.STEP
						+ ":\t changes the modus to STEP. This means every step will be shown.");
		System.out
				.println(ConsoleCommands.SET
						+ " "
						+ ConsoleCommands.RUN
						+ ":\t changes the modus to RUN. This means just the result will be shown.");
		System.out.println(ConsoleCommands.GET
				+ ":\t\t will show you the current Modus");
		System.out
				.println(ConsoleCommands.QUIT + ":\t\t go back to main menu.");
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

}