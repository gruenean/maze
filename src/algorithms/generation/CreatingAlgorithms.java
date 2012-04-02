package algorithms.generation;

import labyrinth.Maze;



abstract public class CreatingAlgorithms implements ICreatingAlgorithms{

	protected String _algoName;
	protected Maze _maze;
	
	/**
	 * Constructor for all Algorithms
	 */
	public CreatingAlgorithms(Maze maze) {
		_maze = maze;	
		_algoName = "";
		
	}

	
	/**
	 * gives the specificied Class of the Algoritm. The extended Class is always
	 * Algorithms
	 */
	public Class<? extends CreatingAlgorithms> getInstance(String name) {
		return this.getClass();
	}

	
	public void setName(String algoName) {
		this._algoName = algoName;

	}
	
}
