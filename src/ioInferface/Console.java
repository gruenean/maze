package ioInferface;

import java.util.Scanner;

import Cell_OLDOLDOLD.Test;

public class Console extends AListener implements IListener {
	static Test _mytest;
	String[] stringArray;

	// private String[] inputArray = new String[] {};

	public Console() {
		super();
		_in = new Scanner(System.in);
		goingon = true;
		_mytest = null;
		startListening();
	}

	public void startListening() {
		while (goingon) {
			System.out.print("MAIN: ");
			inputString = _in.nextLine().toUpperCase();
			stringArray = inputString.split(" ");
			// printArray(stringArray);

			/**
			 * go to the next menue for creating a maze
			 */
			if (stringArray[0].equals(Commands.CREATE)) {
				_mytest = new Test();
				_mytest.createMaze();

				/**
				 * go to the next menue for solving a maze
				 */

			} else if (stringArray[0].equals(Commands.SOLVE)) {
				System.out.println("vor aufruf = " + _mytest);
				new solveListener(stringArray, _mytest);
//				_mytest = new Test();
			
			} else if (stringArray[0].equals(Commands.GUI)) {
				System.out.println("vor aufruf = " + _mytest);
				new GUIListener(stringArray, _mytest);

				/**
				 * go to the next menue for changing the log level
				 */
			} else if (stringArray[0].equals(Commands.LOG)) {
				new LogListener(stringArray);
				System.out.println(Commands.LOG + " Menu verlassen.");

				/**
				 * go to the help menu and give possibilities
				 */
			} else if (stringArray[0].contains(Commands.HELP)) {
				getHelp();
			}

			/**
			 * if quit... just do it
			 */
			else if (stringArray[0].equals(Commands.QUIT))
				quit();
		}

		_in.close();

	}

	@Override
	public void getHelp() {
		if (inputString.isEmpty()) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}
		System.out
				.println("*You can enter a prompt...\nFor example log. Then you enter the Log menu");
		System.out
				.println("*You can also use more than one prompt in a line.\nFor example write <<log set INFO>> and the Log Level will be set to INFO");
		System.out.println("QUIT:\t\t go back to main menu.");

	}

}