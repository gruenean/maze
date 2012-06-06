package ioInferface.gui;

import java.awt.Color;

import main.Conf;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

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

	public void configLitteBug(int[] pos) {
		littleBug = new LittleBug(_globalConf);
		addActor(littleBug, new Location(pos[1], pos[0]));
		addKeyListener(littleBug);

	}

	public void setWall(int row, int col) {
		addActor(new Wall(), new Location(row, col));
	}

	public void removeWall(int[] pos) {
		removeActorsAt(new Location(pos[1], pos[0]));
	}

	public void setDot(int row, int col, String color) {
		if (color == "red")
			addActor(new RedDot(), new Location(row, col));
		else if (color == "blue")
			addActor(new BlueDot(), new Location(row, col));
		else
			addActor(new BlackDot(), new Location(row, col));
	}

	public void setDot(int row, int col) {
		addActor(new BlackDot(), new Location(row, col));
	}

	public void moveMovingDot(int[] oldPosition, int[] newPosition) {
		removeActorsAt(new Location(oldPosition[0], oldPosition[1]));
		addActor(new ioInferface.gui.BlackDot(), new Location(newPosition[0],
				newPosition[1]));
	}

}
