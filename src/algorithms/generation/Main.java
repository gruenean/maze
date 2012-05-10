package algorithms.generation;

import ioInferface.Console;

public class Main {

	static Console _myconsole = null;
	static Conf _globalConf = null;

	public static void main(String[] args) {
		_globalConf = new Conf();
		
		_myconsole = new Console(_globalConf);

	}

}
