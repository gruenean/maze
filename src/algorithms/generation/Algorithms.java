package algorithms.generation;


import labyrinth.Maze;

abstract public class Algorithms implements IAlgorithms {

	// protected int rows;
	// protected int cols;
	// protected Cell[][] map;
	// protected String algoName;
	protected String _algoName;
	protected Maze _maze;
	protected Conf _configs;

	// /**
	// * if boolean isCreating = true, then the Algo is a Algo to create a maze
	// if
	// * boolean isCreating = false, then the Algo is a Algo to resolve the maze
	// * (to find a way)
	// */
	// protected boolean isCreating;

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
		//Conf config = new Conf();
		// rows = 0;
		// cols = 0;
		// map = null;
		// algoName = "";

	}

	public void setName(String name) {
		_algoName = name;

	}

	public String getName() {
		return _algoName;

	}

	// /**
	// * gives the specificied Class of the Algoritm. The extended Class is
	// always
	// * Algorithms
	// */
	// public Class<? extends Algorithms> getInstance(String name) {
	// return this.getClass();
	// }

}
