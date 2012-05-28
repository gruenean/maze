package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import main.Conf;
import algorithms.generation.Algorithms;

abstract public class ASolvingAlgorithms extends Algorithms implements
		ISolvingAlgorithms {

	protected Cell _currentCell;
	protected static int _solvingCounter = 0;

	/**
	 * Constructor for all Solving Algorithms.
	 */
	public ASolvingAlgorithms(Maze maze, Cell startCell, Cell endCell, Conf configs) {
		super(maze);
_configs = configs;
		_startCell = startCell;
		_endCell = endCell;

	}

	/**
	 * gives the specificied Class of the Algoritm. The extended Class is always
	 * Algorithms
	 */
	public Class<? extends ASolvingAlgorithms> getInstance(String name) {
		return this.getClass();
	}

}
