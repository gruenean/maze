package ioInferface.gui;



import java.awt.Color;

import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

public class MyGameGrid extends GameGrid{

	private static final long serialVersionUID = 8486057990676537058L;
	private LittleBug littleBug;

	public MyGameGrid(int rows, int cols) {
		super(rows, cols, 30, Color.black,
				"sprites/white_background_601x601.png", false);
		littleBug = new LittleBug();

		addActor(littleBug, new Location(0, 1));

		addKeyListener(littleBug);

		show();

		doRun();
	}

	public void setWall(int row, int col) {

		addActor(new Cell(), new Location(row, col));

	}

}
