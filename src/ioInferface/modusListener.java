package ioInferface;

import Cell_OLDOLDOLD.Test;
import algorithms.generation.Conf;

public class ModusListener extends AListener implements IListener {
	private String inputString = " ";

//	private String[] possiblesmodus;
	private Test _mytest;

	public ModusListener(String[] inputString, Test test) {
		super();
		_mytest = test;

		stringArray = removeFirstCommand(inputString);

		startListening();
	}

	// public boolean containsAlgo(String loglevel) {
	// for (int i = 0; i < possiblesmodus.length; i++) {
	// if (possiblesmodus[i].contains(loglevel.toUpperCase()))
	// return true;
	// }
	// return false;
	// }

	public void startListening() {
		String modusString = null;
		while (goingon) {
			System.out.print("MAIN(MODUS): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				// stringArray = inputString.toUpperCase().split(" ");
			}

			/**
			 * if the input is MODUS, just clear ist
			 */
			if (stringArray[0].equals(Commands.MODUS)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(Commands.SET)) {
				if (stringArray[1].equals(Commands.STEP))
					Conf.STEPMODUS = true;
				if (stringArray[1].equals(Commands.RUN))
					Conf.STEPMODUS = false;
			}

			if (stringArray[0].equals(Commands.GET)) {
				if (Conf.STEPMODUS) {
					modusString = "STEP";
					goingon = false;
				} else {
					modusString = "RUN";
					goingon = false;
				}
				System.out.println("The current MODUS is : " + modusString);
			}

			else if (stringArray[0].equals(Commands.HELP)
					|| stringArray.length == 0)
				getHelp();

			else
				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	@Override
	public void getHelp() {
		if (inputString.isEmpty() || !stringArray[0].equals(Commands.HELP)) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}
		System.out.println("NOT IMPLEMENTED YET THE HELP OF\n\nSOLVING ALGO");

		reinitializeStringArray();
		// System.out.println(goingon);
	}

}