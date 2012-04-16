package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import algorithms.generation.Algorithms;

abstract public class SolvingAlgorithms extends Algorithms implements
		ISolvingAlgorithms {

	protected Cell _startCell;
	protected Cell _endCell;
	protected Cell _currentCell;

	/**
	 * Constructor for all Solving Algorithms
	 */
	public SolvingAlgorithms(Maze maze, Cell startCell, Cell endCell) {
		super(maze);
		_startCell = startCell;
		_endCell = endCell;

	}

	/**
	 * gives the specificied Class of the Algoritm. The extended Class is always
	 * Algorithms
	 */
	public Class<? extends SolvingAlgorithms> getInstance(String name) {
		return this.getClass();
	}

}
