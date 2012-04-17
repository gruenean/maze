package ioInferface;

import java.util.logging.Level;

import logging.UseLogger;
import Cell.Test;

public class solveListener extends AListener implements IListener {
	private String inputString = " ";

	private String[] possiblesSolvingAlgo;
	private Test _mytest;

	public solveListener(String[] inputString, Test test) {
		super();
		_mytest = test;
		stringArray = removeFirstCommand(inputString);

		// System.out.println("\nWelcome in the Log-Menu\n--------------------------");
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
			if (stringArray[0].equals(Commands.SOLVE)){
				stringArray = removeFirstCommand(stringArray);
			if(stringArray.length==0) reinitializeStringArray();	
			}

			if (stringArray[0].equals(Commands.OWN))
				_mytest.solveMaze();
			// goingon = false;

			if (stringArray[0].equals(Commands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(Commands.HELP) || stringArray.length==0)
				getHelp();

			else
				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	@Override
	public void getHelp() {
		if (!containsAlgo(stringArray[0]) || inputString.isEmpty()
				|| !stringArray[0].equals(Commands.HELP)) {
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