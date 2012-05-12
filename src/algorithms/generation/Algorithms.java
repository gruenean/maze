package algorithms.generation;

import labyrinth.Maze;
import main.Conf;

abstract public class Algorithms implements IAlgorithms {

	protected String _algoName;
	protected Maze _maze;
	protected Conf _configs;

	/**
	 * default constructor (is needed for Solving Algos. The solving Algos
	 * mustn't know the maze!
	 */
	public Algorithms() {
	}

	/**
	 * Constructor for all Algorithms
	 */
	public Algorithms(Maze maze) {
		_maze = maze;
	}

	public void setName(String name) {
		_algoName = name;
	}

	public String getName() {
		return _algoName;

	}

}
