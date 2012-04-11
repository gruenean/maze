package algorithms.generation.solve;

import algorithms.generation.IAlgorithms;


public interface ISolvingAlgorithms extends IAlgorithms{

	
	
	public void resolveMaze();
	
	
	/**
	 * defines which at which cell the algorithmus will start to find a way and
	 * at which cell the end is.
	 */
	void defineStartandEndCell();
	
	
}
