package algorithms.generation;

import labyrinth.Maze;

public interface IAlgorithms {

	public void setName(String algoName);
	public String getName();
	// public void createMaze(int rows, int cols);

	/**
	 * defines which at which cell the algorithmus will start to find a way and
	 * at which cell the end is.
	 */
	void defineStartandEndCell();

	/**
	 * will calcing maze
	 * 
	 * @param maze
	 *            this is the maze which should be calced
	 */
	public void calc(Maze maze);
	
	
	
	public void createMaze();
	
	
	public void resolveMaze();
	
	public Class<? extends Algorithms> getInstance(String name);

}
