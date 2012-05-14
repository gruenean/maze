package Interfaces.console;

public interface IConsoleListener {

	public void getHelp();

	public void reinitializeStringArray();

	public String[] removeFirstCommand(String[] args);

	/**
	 * 
	 * @return the current Command of the Class (e.g. SOLVE for the
	 *         solveListener)
	 */
	public String getCommand();

	public void startListening(String[] input);

}
