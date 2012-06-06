package Interfaces;

public interface IOutput {

	/**
	 * @param stream print the given stream w/o a NL/CR/CR-NL
	 */
	public void print(String stream);

	/**
	 * @param stream @param stream print the given stream w/ a NL/CR/CR-NL
	 */
	public void printLine(String stream);

}
