package ioInferface.gui;

import ch.aplu.jgamegrid.Actor;

/**
 * @author green
 * This class just show a blue dot in the maze
 */
public class BlueDot extends Actor {

	/**
	 * This class just show a black blue in the maze 
	 */
	public BlueDot() {
		super(true,"sprites/blue_dot.png");
		this.setCollisionImage();
	}
}
