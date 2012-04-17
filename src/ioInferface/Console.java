package ioInferface;

import java.util.Scanner;
import java.util.logging.Level;

import logging.UseLogger;
import Cell.Test;

public class Console extends AListener implements IListener {
	static Test _mytest;

	public Console() {
		super();
		_in = new Scanner(System.in);
		goingon = true;
		_mytest = null;

		// UseLogger.LOGGER.setLevel(Level.SEVERE);

		startListening();
	}

	public void startListening() {

		while (goingon) {
			System.out.print("main: ");
			inputString = _in.nextLine();

			if (inputString.contains("create")) {
				_mytest = new Test();
				_mytest.createMaze();
			} else if (inputString.contains("solve"))
				_mytest.solveMaze();
			else if (inputString.contains("log level")) {
				new LogListener();

				// goingon = false;
				System.out.println(inputString);
				System.out.println("Log  verlassen.");
			} else if (inputString.contains("quit") || inputString.isEmpty())
				goingon = false;
			inputString = "create";
		}

		_in.close();

	}

	public static void setLogLevel(String level) {

		try {
			UseLogger.LOGGER.setLevel(Level.parse(level));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Mšgliche Log-Level: ");
		}

		System.out.println(UseLogger.LOGGER.getLevel());

	}

	@Override
	public void getHelp() {
		// TODO Auto-generated method stub

	}

}