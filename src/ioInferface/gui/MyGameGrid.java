package ioInferface.gui;

import java.awt.Color;

import main.Conf;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

/**
 * @author green
 *
 */
public class MyGameGrid extends GameGrid {

	private static final long serialVersionUID = 8486057990676537058L;
	private LittleBug littleBug;
	private Conf _globalConf;

	public MyGameGrid(int rows, int cols, Conf globalConf) {
		super(rows, cols, 22, Color.black,
				"sprites/white_background_601x601.png", false);
		this._globalConf = globalConf;

		show();

		doRun();

	}

	/**
	 * @param pos
	 */
	public void configLitteBug(int[] pos) {
		littleBug = new LittleBug(_globalConf);
		addActor(littleBug, new Location(pos[0], pos[0]));
		addKeyListener(littleBug);

	}

	/**
	 * brings the little bug actor to front
	 */
	public void setLittleBugOnTop() {
		littleBug.setOnTop();
		setPaintOrder(LittleBug.class);  // Rocket in foreground
	}

	/**
	 * Adds a GUI wall at the given position. This is for displaying purposes only!
	 * This method does not change anything in the maze itself.
	 * 
	 * @param row	row of the wall that should be added
	 * @param col	column of the wall that should be added
	 */
	public void setWall(int row, int col) {
		addActor(new Wall(), new Location(row, col));
	}

	/**
	 * Removes a GUI wall at the given position. This is for displaying purposes only!
	 * This method does not change anything in the maze itself.
	 * 
	 * @param pos	position of the wall that should be removed
	 */
	public void removeWall(int[] pos) {
		removeActorsAt(new Location(pos[1], pos[0]));
	}

	/**
	 * Sets a colored dot into the maze
	 * 
	 * @param row	destination row in maze
	 * @param col	destination column in maze
	 * @param color	the dot's color (currently only blue or black (default))
	 */
	public void setDot(int row, int col, String color) {
		if (color == "blue") {
			BlueDot blue = new BlueDot();
			addActor(blue, new Location(row, col));
			blue.setOnBottom();
		} else
			addActor(new BlackDot(), new Location(row, col));
	}

	/**
	 * Sets a black dot into the maze
	 * 
	 * @param row	destination row in maze
	 * @param col	destination column in maze	 
	 * */
	public void setDot(int row, int col) {
		addActor(new BlackDot(), new Location(row, col));
	}

	 /**
	  * Simulates a moving dot 
	 * @param oldPosition position of the current/ old cell
	 * @param newPosition position of the next/new cell
	 */
	public void moveMovingDot(int[] oldPosition, int[] newPosition) {
	 removeActorsAt(new Location(oldPosition[0], oldPosition[1]));
	 addActor(new ioInferface.gui.BlackDot(), new Location(newPosition[0],
	 newPosition[1]));
	 }
}
