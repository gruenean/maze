package Interfaces;

import java.io.PrintStream;


public class AOutput implements IOutput {
	PrintStream _mystream = null;

	public AOutput(PrintStream mystream) {
		_mystream = mystream;

	}

	public void print(String print) {
		_mystream.print(print);

	}

	public void printLine(String print) {
		_mystream.println(print);

	}

}
