package Interfaces;

import java.io.PrintStream;


public class AOutput implements IOutput {
	PrintStream _mystream = null;

	/**
	 * @param Output Stream (e.g. console)
	 */
	public AOutput(PrintStream mystream) {
		_mystream = mystream;
	}

	/* (non-Javadoc)
	 * @see Interfaces.IOutput#print(java.lang.String)
	 */
	public void print(String print) {
		_mystream.print(print);
	}

	/* (non-Javadoc)
	 * @see Interfaces.IOutput#printLine(java.lang.String)
	 */
	public void printLine(String print) {
		_mystream.println(print);
	}
}
