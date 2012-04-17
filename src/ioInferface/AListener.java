package ioInferface;

import java.util.Scanner;

public abstract class AListener {

	protected String inputString = " ";
	protected Scanner _in = null;
	protected boolean goingon;
	public String[] stringArray;

	// protected Commands _mycommands;

	public AListener() {
		// _mycommands = new Commands();
		stringArray = new String[] {};
		_in = new Scanner(System.in);
		goingon = true;
	}

	public void quit() {
		goingon = false;
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
		String tmp = "";
		for (int i = 0; i < array.length; i++) {
			tmp = tmp + stringArray[i] + " ";
		}
		return tmp;

	}

	public void printArray(String[] thisArray) {
		System.out.println("verbleibende Zeichen");
		for (int i = 0; i < thisArray.length; i++) {

			System.out.print(thisArray[i] + " ");
		}
		System.out.print("\n");

	}

	public void reinitializeStringArray() {
		stringArray = new String[] { "" };

	}

}
