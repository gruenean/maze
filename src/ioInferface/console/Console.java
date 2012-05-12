package ioInferface.console;



import java.util.Scanner;

import main.Conf;
import main.mazeHandler;


public class Console extends AConsoleListener implements IConsoleListener {
	static mazeHandler _mytest;
	String[] stringArray;

	public Console(Conf globalConf) {
		super(globalConf);
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

			/**
			 * go to the next menue for creating a maze
			 */
			if (stringArray[0].equals(ConsoleCommands.CREATE)) {
				_mytest = new mazeHandler();
				_mytest.createMaze();

				/**
				 * go to the next menue for solving a maze
				 */

			} else if (stringArray[0].equals(ConsoleCommands.SOLVE)) {
				new solveListener(stringArray, _mytest, globalConf);

			} else if (stringArray[0].equals(ConsoleCommands.GUI)) {
				new GUIListener(stringArray, _mytest, globalConf);

				/**
				 * go to the next menue for changing the log level
				 */
			} else if (stringArray[0].equals(ConsoleCommands.LOG)) {
				new LogListener(stringArray, globalConf);
				System.out.println(ConsoleCommands.LOG + " Menu verlassen.");

			} else if (stringArray[0].equals(ConsoleCommands.MODUS)) {
				new ModusListener(stringArray, globalConf);
				System.out.println(ConsoleCommands.MODUS + " Menu verlassen.");

				/**
				 * go to the help menu and give possibilities
				 */
			} else if (stringArray[0].contains(ConsoleCommands.HELP)) {
				getHelp();
			}

			/**
			 * if quit... just do it
			 */
			else if (stringArray[0].equals(ConsoleCommands.QUIT))
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