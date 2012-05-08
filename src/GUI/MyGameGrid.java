package GUI;

// MyGameGrid.java

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import GUI.Cell;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

public class MyGameGrid extends GameGrid {

	private List<Cell> _myCells = null;

	private static final long serialVersionUID = 8486057990676537058L;
	private LittleBug littleBug;

	// private Cell myCell;

	public MyGameGrid(int rows, int cols) {
		// super(10, 10, 34, Color.green, false);
		super(rows, cols, 30, Color.black,
				"sprites/white_background_601x601.png", false);
		littleBug = new LittleBug();
		_myCells = new ArrayList<Cell>();

		// myCell = new Cell();

		addActor(littleBug, new Location(0, 0));

		// for (int i = 0; i < 10; i++)
		// addActor(new Fly(), getRandomEmptyLocation());
		addKeyListener(littleBug);

		show();

		doRun();
	}

	public void setWall(int row, int col) {

//		_myCells.add(new Cell());
		addActor(new Cell(), new Location(row, col));

	}

	// --------------------- class Fly ---------------------------
	// class Fly extends Actor
	// {
	//
	// public Fly()
	// {
	// super("sprites/smallbug.gif");
	// }
	// }

}
