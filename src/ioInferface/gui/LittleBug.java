package ioInferface.gui;

import java.awt.event.KeyEvent;

import logging.UseLogger;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GGKeyListener;

class LittleBug extends Actor implements GGKeyListener {
	public LittleBug() {
		super(true, "sprites/smallbug.gif");
	}

	/**
	 * key event for the "littlebug":
	 * 
	 * UP = 270 / RIGHT = 0 / LEFT = 180 / DOWN = 90
	 */
	public boolean keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_UP:
			setDirection(270);
			break;
		case KeyEvent.VK_RIGHT:
			setDirection(0);
			break;
		case KeyEvent.VK_LEFT:
			setDirection(180);
			break;
		case KeyEvent.VK_DOWN:
			setDirection(90);
			break;
		}
		canIgoOn();

		return true;
	}

	public boolean keyReleased(KeyEvent evt) {
		return true;
	}

	public void canIgoOn() {

		/**
		 * get the actor of the cell you want to move to
		 */
		Actor actor = gameGrid.getOneActorAt(getNextMoveLocation(), Cell.class);

		UseLogger.LOGGER.finer("aktor = " + actor);
		/**
		 * if there is not a actor, it is allowed to move
		 */
		if (actor == null) {
			move();
			/**
			 * oterwhise go back (if move left, go right... and so on)
			 */
		} else
			setHorzMirror(!isHorzMirror());
	}

	public void reset() {
		setHorzMirror(false);
	}
}