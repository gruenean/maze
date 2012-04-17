package ioInferface;

public interface IListener {

	public void getHelp();

	public void quit();

	public void reinitializeStringArray();

	public String[] removeFirstCommand(String[] args);
}
