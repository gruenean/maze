package ioInferface.console;

public interface IConsoleListener {

	public void getHelp();

	public void quit();

	public void reinitializeStringArray();

	public String[] removeFirstCommand(String[] args);
	
}
