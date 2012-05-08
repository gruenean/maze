package ioInferface;

import labyrinth.Cell;
import Cell_OLDOLDOLD.Test;

public class GUIListener extends AListener implements IListener {
	private String inputString = " ";
	private Test _mytest;

	public GUIListener(String[] inputString, Test test) {
		super();
		_mytest = test;
		stringArray = removeFirstCommand(inputString);
		System.out.println("MyTest = " + _mytest + test);
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
			if (stringArray[0].equals(Commands.GUI)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(Commands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(Commands.HELP))
				getHelp();

			else if (stringArray[0].equals(Commands.CREATE)) {
				_mytest.createGUI();
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