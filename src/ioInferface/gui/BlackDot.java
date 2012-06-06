package ioInferface.gui;

import ch.aplu.jgamegrid.Actor;

/**
 * @author green
 * This class just show a black dot in the maze
 */
public class BlackDot extends Actor{

	/**
	 * This class just show a black dot in the maze. 
	 */
	public BlackDot() {
		super(true, "sprites/black_dot.png");
		
		this.setCollisionImage();
	}
}
