package notUsed;

import Interfaces.console.AConsoleListener;
import Interfaces.console.ConsoleCommands;
import main.Conf;
import main.MazeHandler;

public class StepModusListener extends AConsoleListener {
	private String inputString = " ";

	private String[] possiblesSolvingAlgo;
	private MazeHandler _mymaze;

	public StepModusListener(Conf globalConf, MazeHandler mymaze) {
		super(globalConf);
		_mymaze = mymaze;

		initPossibleAlgos();
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

	public void startListening(String[] input) {

		stringArray = input;

		while (goingon) {
			_myoutput.printLine("MAIN(SOLVE): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			if (stringArray[0].equals(ConsoleCommands.OWN)) {
				_mymaze.solveMaze(ConsoleCommands.OWN);
				goingon = false;
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

		_myoutput.printLine("SET STEP:\t\t change into the STEP Modus.");
		_myoutput.printLine("SET RUN:\t\t change into the RUNNING Modus.");
		_myoutput.printLine("GET:\t\t give back the current modus.");
		reinitializeStringArray();
	}

	@Override
	public String getCommand() {
		return "MODUS";
	}

}