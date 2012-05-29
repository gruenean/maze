package ioInferface.gui;

import java.awt.Color;

import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

public class MyGameGrid extends GameGrid {

	private static final long serialVersionUID = 8486057990676537058L;
	private LittleBug littleBug;

	public MyGameGrid(int rows, int cols) {
		super(rows, cols, 30, Color.black,
				"sprites/white_background_601x601.png", false);
		

		show();

		doRun();
	}
	
	public void configLitteBug(int[] pos){
		littleBug = new LittleBug();
		addActor(littleBug, new Location(pos[1], pos[0]));
		addKeyListener(littleBug);
		
	}

	public void setWall(int row, int col) {
		addActor(new Wall(), new Location(row, col));
	}
	
	public void removeWall(int[] pos) {
		removeActorsAt(new Location(pos[1], pos[0]));
	}
	public void setGhost(int row, int col) {
		addActor(new Ghost(), new Location(row, col));
	}
	
	public void moveMovingDot(int[] oldPosition, int[] newPosition) {
		removeActorsAt(new Location(oldPosition[0], oldPosition[1]));
		addActor(new ioInferface.gui.Ghost(), new Location(newPosition[0], newPosition[1]));
	}

}
