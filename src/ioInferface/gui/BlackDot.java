package ioInferface.gui;

import ch.aplu.jgamegrid.Actor;

public class BlackDot extends Actor{

	public BlackDot() {
		super(true, "sprites/black_dot.png");
		
		this.setCollisionImage();
	}
}
