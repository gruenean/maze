package algorithms.generation;

import labyrinth.Cell;
import labyrinth.Maze;

public class ownAlgo extends CreatingAlgorithms implements ICreatingAlgorithms {

	Cell randomCell;
	Cell NeighbourCell;

	public ownAlgo(Maze maze) {
		super(maze);
		randomCell = null;
		NeighbourCell = null;
	}

	


	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}


	public void createMaze() {
		
		for (int i= 1 ;i<10;i++)
		{		
		randomCell = _maze.getRandomCell();
		NeighbourCell = _maze.getRandomNeighbour(randomCell);

		checkRoots();
		whichWallToBreakDown();
		}
	}

	
	public void whichWallToBreakDown(){
		
	int [] randomPosition= _maze.getPositionOfCell(randomCell);
	int [] randomNeigbourPosition = _maze.getPositionOfCell(NeighbourCell);
		
	//System.out.println(randomNeigbourPosition.length);
	//System.out.println(randomNeigbourPosition[0] + ":" + randomNeigbourPosition[1]);
	
	/**
	 * if the Neigbour is at left, break the left Wall
	 */
	if (randomPosition[1]-1== randomNeigbourPosition[1]) destroyWall(0, 1);
	
	/**
	 * if the Neigbour is at right, break the right Wall
	 */
	if (randomPosition[1]+1== randomNeigbourPosition[1]) destroyWall(1, 0);
	
	
	/**
	 * if the Neigbour is at top, break the top Wall
	 */
	if (randomPosition[0]+1== randomNeigbourPosition[0]) destroyWall(2, 3);
	
	
	
	/**
	 * if the Neigbour is at bottom, break the bottom Wall
	 */
	if (randomPosition[0]+1== randomNeigbourPosition[0]) destroyWall(3, 2);
	
	
		
	
	
		
	}
	
	
	public void destroyWall(int whichWall, int whichWall2){
	randomCell.destroyWall(whichWall);
	NeighbourCell.destroyWall(whichWall2);
	
	randomCell.setRoot(NeighbourCell.getRoot());
	
	//System.out.println(randomCell.getRoot());
	//System.out.println(NeighbourCell.getRoot());
	
	}
	
	
	public void checkRoots() {
		/**
		 * just do anything, if the roots of this two cells are not the same
		 */
		if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {

			
			
			
			
			
			
		}

		/**
		 * if the root is the same, then ask the maze for a new randomCell
		 */
		else {

		}

	}

}
