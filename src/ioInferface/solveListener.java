package ioInferface;

import Cell_OLDOLDOLD.Test;
import algorithms.generation.Conf;


public class solveListener extends AListener implements IListener {
	private String inputString = " ";

	private String[] possiblesSolvingAlgo;
	private Test _mytest;

	public solveListener(String[] inputString, Test test, Conf globalConf) {
		super(globalConf);
		_mytest = test;
		
		stringArray = removeFirstCommand(inputString);

		initPossibleAlgos();
		startListening();
	}

	public void initPossibleAlgos() {
		possiblesSolvingAlgo = new String[] { "OWN", "HELP" };
	}

	public boolean containsAlgo(String loglevel) {
		for (int i = 0; i < possiblesSolvingAlgo.length; i++) {
			if (possiblesSolvingAlgo[i].contains(loglevel.toUpperCase()))
				return true;
		}
		return false;
	}

	public void startListening() {

		while (goingon) {
			System.out.print("MAIN(SOLVE): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			/**
			 * if the input is SOLVE, just clear ist
			 */
			if (stringArray[0].equals(ConsoleCommands.SOLVE)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(ConsoleCommands.OWN))
				_mytest.solveMaze();

			if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(ConsoleCommands.HELP)
					|| stringArray.length == 0)
				getHelp();

			else
				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	@Override
	public void getHelp() {
		if (!containsAlgo(stringArray[0]) || inputString.isEmpty()
				|| !stringArray[0].equals(ConsoleCommands.HELP)) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}
		System.out.println("SET STEP:\t\t change into the STEP Modus.");
		System.out.println("SET RUN:\t\t change into the RUNNING Modus.");
		System.out.println("GET:\t\t give back the current modus.");
		reinitializeStringArray();
		// System.out.println(goingon);
	}

}