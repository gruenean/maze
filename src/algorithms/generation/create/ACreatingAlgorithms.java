package algorithms.generation.create;

import labyrinth.Maze;
import algorithms.generation.Algorithms;

abstract public class ACreatingAlgorithms extends Algorithms implements ICreatingAlgorithms{

	/**
	 * Constructor for all Creating Algorithms
	 */
	public ACreatingAlgorithms(Maze maze) {
		super(maze);

	}

	/**
	 * gives the specificied Class of the Algoritm. The extended Class is always
	 * Algorithms
	 */
	public Class<? extends ACreatingAlgorithms> getInstance(String name) {
		return this.getClass();
	}

}
