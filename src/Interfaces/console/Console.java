package Interfaces.console;

import java.util.Scanner;

import notUsed.GUIListener;

import main.Conf;
import main._mazeHandler;

public class Console extends AConsoleListener {
	static _mazeHandler _mymaze;
	String[] stringArray;
	IConsoleListener[] _listenerCommands = null;

	public Console(Conf globalConf) {
		super(globalConf);
		_in = new Scanner(System.in);
		goingon = true;
		_mymaze = new _mazeHandler(globalConf);

		_listenerCommands = new IConsoleListener[] {
				new LogListener(_globalConf),
				new solveListener(_globalConf, _mymaze),
				new createListener(_globalConf, _mymaze),
				new ModusListener(_globalConf) };

		startListening();
	}

	public void startListening() {
		while (goingon) {
			_myoutput.print("MAIN: ");
			inputString = _in.nextLine().toUpperCase();
			stringArray = inputString.split(" ");

			if (stringArray == null || stringArray.length == 0
					|| !isValidCommand(stringArray[0])) {
				getHelp();
				continue;
			}

			String firstCommand = stringArray[0];

			for (IConsoleListener listener : _listenerCommands) {
				if (listener.getCommand().equals(firstCommand)) {
					String[] newString = removeFirstCommand(stringArray);
					listener.startListening(newString);
					reinitializeStringArray();
					// stringArray = new String[] {""};
				}
			}

			/**
			 * go to the help menu and give possibilities
			 */
			if (stringArray[0].contains(ConsoleCommands.HELP)) {
				getHelp();
			}

			/**
			 * if quit... just do it
			 */
			if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;
		}

		_in.close();

	}

	@Override
	public void getHelp() {
		// String wrongString = makeStringArrayToString(stringArray);
		if (inputString.isEmpty() || !isValidCommand(stringArray[0])) {

			_myoutput
					.printLine("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");

			_myoutput
					.printLine("*You can enter a prompt...\nFor example log. Then you enter the Log menu");

			_myoutput
					.printLine("*You can also use more than one prompt in a line.\nFor example write <<log set INFO>> and the Log Level will be set to INFO");
			_myoutput.printLine("QUIT:\t\t will terminate the program.\n");

		}

	}

	@Override
	public String getCommand() {
		return null;
	}

	@Override
	public void startListening(String[] input) {
		// TODO Auto-generated method stub

	}

	private boolean isValidCommand(String command) {
		if (command == null || command.length() == 0)
			return false;

		for (IConsoleListener listener : _listenerCommands) {
			if (listener.getCommand().equals(command)) {
				return true;
			}

		}
		if (command.contains("QUIT"))
			return true;
		if (command.contains("HELP"))
			return true;
		return false;

	}

}