package Interfaces.console;

import main.Conf;
import main.MazeHandler;

public class createListener extends AConsoleListener {

	private MazeHandler _mymaze;

	public createListener(Conf globalConf, MazeHandler mymaze) {
		super(globalConf);
		_mymaze = mymaze;

	}

	public void startListening(String[] input) {
		goingon = true;
		stringArray = input;

		while (goingon) {
			_myoutput.print("MAIN(CREATE): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			if (stringArray[0].equals(ConsoleCommands.OWN)){ 
				_mymaze.createMaze(ConsoleCommands.OWN);
				System.out.println("1st string array = " + stringArray[0]);
				reinitializeStringArray();
				stringArray = new String[] {"QUIT"};
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
		if (inputString.isEmpty()
				|| !stringArray[0].equals(ConsoleCommands.HELP)) {

			_myoutput
					.printLine("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
			_myoutput
					.printLine("OWN:\t\t creates a new maze with the 'own' algorithm.");
			_myoutput.printLine("QUIT:\t\t go back to the main menu. \n");

			reinitializeStringArray();
		}
	}

	@Override
	public String getCommand() {
		return "CREATE";
	}

}