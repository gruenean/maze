package algorithms.generation;

import labyrinth.Cell;
import labyrinth.Maze;

public class ownAlgo extends CreatingAlgorithms {

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

		/**
		 * do this loop rows * cols * 5 and then look if all cell has the same
		 * root. If yes, there is a way in the maze.
		 */
		while (!_maze.existJustOneRoot()) {
			 System.out.println("Exist just one Root?! = " + _maze.existJustOneRoot());

			for (int i = 1; i < (_maze.getRowsAndCols()[0]
					* _maze.getRowsAndCols()[1] ); i++) {
				// System.out.println(i + "-te Schlaufe");
				randomCell = _maze.getRandomCell();
				NeighbourCell = _maze.getRandomNeighbour(randomCell);

				if (checkRoots()) {
					whichWallToBreakDown();
				}
				// else {
				// int help = 1;
				// System.out.println(i+ "-te Schlaufe: gleicher Root");
				 }
			}
	_maze.sysoutAllCellAndRoots();
	}
	
	

	//}

	public void whichWallToBreakDown() {

		int[] randomPosition = _maze.getPositionOfCell(randomCell);
		int[] randomNeigbourPosition = _maze.getPositionOfCell(NeighbourCell);

		// System.out.println(randomCell.getValue());

		/**
		 * if the Neigbour is at left, break the left Wall
		 */
		if (randomPosition[0] == randomNeigbourPosition[0]
				&& randomPosition[1] - 1 == randomNeigbourPosition[1])
			destroyWall(0, 1);

		/**
		 * if the Neigbour is at right, break the right Wall
		 */
		else if (randomPosition[0] == randomNeigbourPosition[0]
				&& randomPosition[1] + 1 == randomNeigbourPosition[1])
			destroyWall(1, 0);

		/**
		 * if the Neigbour is at top, break the top Wall
		 */
		else if (randomPosition[0] - 1 == randomNeigbourPosition[0]
				&& randomPosition[1] == randomNeigbourPosition[1])
			destroyWall(2, 3);

		/**
		 * if the Neigbour is at bottom, break the bottom Wall
		 */
		else if (randomPosition[0] + 1 == randomNeigbourPosition[0]
				&& randomPosition[1] == randomNeigbourPosition[1])
			destroyWall(3, 2);

		/**
		 * just to see if it is always a neighbour ;-)
		 */
		else
			System.out.println("not a neighbour !!!!");

	}

	public void destroyWall(int whichWall, int whichWall2) {
		randomCell.destroyWall(whichWall);
		NeighbourCell.destroyWall(whichWall2);

		_maze.updateRoots(randomCell, NeighbourCell);


	}

	public boolean checkRoots() {
		/**
		 * just do anything, if the roots of this two cells are not the same
		 */
		if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {
			return true;
		}

		return false;

	}

}
