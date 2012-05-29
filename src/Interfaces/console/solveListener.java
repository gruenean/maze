package Interfaces.console;

import main.Conf;
import main._mazeHandler;

public class solveListener extends AConsoleListener {
	private String inputString = " ";

	private String[] possiblesSolvingAlgo;
	private _mazeHandler _mymaze;

	public solveListener(Conf globalConf, _mazeHandler mymaze) {
		super(globalConf);
		_mymaze = mymaze;

		initPossibleAlgos();
	}

	public void initPossibleAlgos() {
		possiblesSolvingAlgo = new String[] { "OWN", "WALLFOLLOWER", "HELP" };
	}

	public boolean containsAlgo(String loglevel) {
		for (int i = 0; i < possiblesSolvingAlgo.length; i++) {
			if (possiblesSolvingAlgo[i].contains(loglevel.toUpperCase()))
				return true;
		}
		return false;
	}

	public void startListening(String[] input) {
		goingon = true;
		stringArray = input;

		while (goingon) {
			_myoutput.printLine("MAIN(SOLVE): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			if (stringArray[0].equals(ConsoleCommands.OWN)){ 
				_mymaze.solveMaze(ConsoleCommands.OWN);
				System.out.println("1st string array = " + stringArray[0]);
				reinitializeStringArray();
				stringArray = new String[] {"QUIT"};
				System.out.println("1st string array after calcing = " + stringArray[0]);
				goingon = false;	
			} else if  (stringArray[0].equals(ConsoleCommands.WALLFOLLOWER)) {
				_mymaze.solveMaze(ConsoleCommands.WALLFOLLOWER);
				reinitializeStringArray();
				goingon = false;	
				printArray(stringArray);
			} else if (stringArray[0].equals(ConsoleCommands.QUIT))
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

			_myoutput
					.printLine("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");

		}

		_myoutput.printLine("Algorithms");
		_myoutput.printLine("**********");
		_myoutput.printLine("OWN:\t\t\t solve maze with our OWN algorithm.");
		_myoutput.printLine("WALLFOLLOWER:\t\t solve maze with WALL FOLLOWER algorithm.\n");
		_myoutput.printLine("SET STEP:\t\t change into the STEP Modus.");
		_myoutput.printLine("SET RUN:\t\t change into the RUNNING Modus.");
		_myoutput.printLine("GET:\t\t\t give back the current modus.");
		reinitializeStringArray();
	}

	@Override
	public String getCommand() {
		return "SOLVE";
	}

}