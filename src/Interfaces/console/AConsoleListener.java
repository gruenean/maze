package Interfaces.console;

import java.util.Scanner;

import Interfaces.IOutput;

import main.Conf;

public abstract class AConsoleListener implements IConsoleListener {

	protected String inputString = " ";
	protected Scanner _in = null;
	protected boolean goingon;
	protected String[] stringArray;
	protected Conf _globalConf = null;
	protected IOutput _myoutput = null;
	protected String _inputString = null;

	public AConsoleListener() {
		// TODO Auto-generated constructor stub
	}

	public AConsoleListener(Conf globalConf) {
		_globalConf = globalConf;
		_myoutput = _globalConf.get_output();
		stringArray = new String[] {};
		_in = new Scanner(System.in);
		goingon = true;
	}


	public String[] removeFirstCommand(String[] args) {
		if (args == null || args.length < 1)
			return null;

		String[] params = new String[args.length - 1];

		for (int i = 1; i < args.length; i++) {
			params[i - 1] = args[i];
		}
		return params;
	}

	public String makeStringArrayToString(String[] array) {
		String tmp = " ";
		try {

			for (int i = 0; i < array.length; i++) {
				tmp = tmp + array[i] + " ";
			}
			return tmp;
		} catch (Exception e) {
			return " ";
		}
	}

	public void printArray(String[] thisArray) {
		_myoutput.printLine("verbleibende Zeichen");
		for (int i = 0; i < thisArray.length; i++) {

			_myoutput.printLine(thisArray[i] + " ");
		}
		_myoutput.printLine("\n");

	}

	public void reinitializeStringArray() {
		stringArray = new String[] {""};

	}

}
