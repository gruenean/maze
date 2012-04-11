package algorithms.generation.create;

import labyrinth.Maze;
import algorithms.generation.Algorithms;

abstract public class CreatingAlgorithms extends Algorithms implements
		ICreatingAlgorithms {

	/**
	 * Constructor for all Creating Algorithms
	 */
	public CreatingAlgorithms(Maze maze) {
		super(maze);

	}

	/**
	 * gives the specificied Class of the Algoritm. The extended Class is always
	 * Algorithms
	 */
	public Class<? extends CreatingAlgorithms> getInstance(String name) {
		return this.getClass();
	}

}
