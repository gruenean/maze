package ioInferface.gui;

import ch.aplu.jgamegrid.Actor;

public class Ghost extends Actor{

	public Ghost() {
		super(true, "sprites/black_dot.png");
		this.setCollisionImage();
	}
}
