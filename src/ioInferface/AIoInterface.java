package ioInferface;

import main.Conf;

public class AIoInterface implements IIoInterface {
	protected Conf globalConf;

	public AIoInterface(Conf globalConf) {
		this.globalConf = globalConf;
	}

}
