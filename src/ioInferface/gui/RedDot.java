package ioInferface.gui;

import ch.aplu.jgamegrid.Actor;

public class RedDot extends Actor{

	public RedDot() {
		//super(true, "sprites/red_dot.png");
		super(true, "sprites/tomato.gif");
		
		this.setCollisionImage();
	}
}
