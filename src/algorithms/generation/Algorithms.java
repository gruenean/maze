package algorithms.generation;

import labyrinth.Cell;
import labyrinth.Maze;



abstract public class Algorithms {

	protected int rows;
	protected int cols;
	protected Cell[][] map;
	protected String algoName;
	protected Maze mymaze;
	/**
	 * if boolean isCreating = true, then the Algo is a Algo to create a maze if
	 * boolean isCreating = false, then the Algo is a Algo to resolve the maze
	 * (to find a way)
	 */
	protected boolean isCreating;

	/**
	 * Constructor for all Algorithms
	 */
	public Algorithms(boolean isCreating) {
		mymaze = null;
		rows = 0;
		cols = 0;
		map = null;
		algoName = "";
		this.isCreating = isCreating;

	}

	public void createMaze() {
		if (isCreating) {

			// TODO create the maze

		} else
			new java.lang.Exception(
					"I can not create this maze. I'm a resolving algo");
		;

	}

	public void resolveMaze() {
		if (!isCreating) {

			// TODO resolve the maze

		} else
			new java.lang.Exception(
					"I can not resolve this maze. I'm a Creating Algo");

	}

	/**
	 * gives the specificied Class of the Algoritm. The extended Class is always
	 * Algorithms
	 */
	public Class<? extends Algorithms> getInstance(String name) {
		return this.getClass();
	}

}
