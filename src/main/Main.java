package main;

import Interfaces.IOutput;
import Interfaces.console.Console;
import Output.OutputConsole;

public class Main {

	static Console _myconsole = null;
	static Conf _globalConf = null;

	public static void main(String[] args) {
		_globalConf = new Conf();

//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		PrintStream ps = new PrintStream(baos);

		IOutput outputDevice = new OutputConsole(System.out);
//		IOutput outputDevice = new OutputConsole(ps);
		
		_globalConf.set_output(outputDevice);
		
//		_globalConf.get_output().print("hoidu");
//		System.out.println(baos);
		
		
		_myconsole = new Console(_globalConf);
	}

}
