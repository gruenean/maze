package algorithms.generation.create;

import labyrinth.Cell;
import labyrinth.Maze;


/**
 * this algorithm is a self
 * @author micha
 *
 */
public class ownCreatingAlgo extends ACreatingAlgorithms  {

	Cell randomCell;
	Cell NeighbourCell;

	public ownCreatingAlgo(Maze maze) {
		super(maze);
		setName("<<eigener CreatingAlgo>>");
	}

	private void defineStartandEndCell() {
		_maze.createEntranceAndExit();
	}

	public void createMaze() {
		while (_maze.hasMultipleRoots()) {
			randomCell = _maze.getRandomCell();
			NeighbourCell = _maze.getRandomNeighbour(randomCell);

			if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {
				_maze.breakWallBetweenCells(randomCell, NeighbourCell);
				// TODO: comment out the following four lines as these are
				// testing only
				_maze.printAsciiMaze();
				System.out.println();
			} else {
				System.out.println("impossibru");
			}
		}
		defineStartandEndCell();
//		System.out.println("--MAP--");
//		_maze.printMap();
//		System.out.println("--ROOTS--");
//		_maze.printRoots();
//		System.out.println("--ASCI MAP--");
//		_maze.printAsciiMaze();
	}

//	public void createMaze2() {
//
//		/**
//		 * do this loop rows * cols * 5 and then look if all cell has the same
//		 * root. If yes, there is a way in the maze.
//		 */
//		int HelpInt = 4;
//		while (_maze.hasMultipleRoots()) {
//
//			for (int i = 1; i < (_maze.getRowsAndCols()[0]
//					* _maze.getRowsAndCols()[1] * 10 ^ HelpInt); i++) {
//				randomCell = _maze.getRandomCell();
//				NeighbourCell = _maze.getRandomNeighbour(randomCell);
//
//				if (checkRoots()) {
//					whichWallToBreakDown();
//				}
//			}
//			if (HelpInt > 0)
//				HelpInt = HelpInt - 1;
//		}
//		long EndTime = System.currentTimeMillis();
//		_maze.printMap();
//		System.out.println("");
//		_maze.printRoots();
//
//	}
//
//
//	/**
//	 * @deprecated This is now handled by maze.breakWallBetweenCells(Cell cell,
//	 *             Cell neighbourcell) TODO: Delete it!
//	 * 
//	 */
//	public void whichWallToBreakDown() {
//
//		int[] randomPosition = _maze.getPositionOfCell(randomCell);
//		int[] randomNeigbourPosition = _maze.getPositionOfCell(NeighbourCell);
//
//		/**
//		 * if the Neigbour is at left, break the left Wall
//		 */
//		if (randomPosition[0] == randomNeigbourPosition[0]
//				&& randomPosition[1] - 1 == randomNeigbourPosition[1])
//			destroyWall(0, 1);
//
//		/**
//		 * if the Neigbour is at right, break the right Wall
//		 */
//		else if (randomPosition[0] == randomNeigbourPosition[0]
//				&& randomPosition[1] + 1 == randomNeigbourPosition[1])
//			destroyWall(1, 0);
//
//		/**
//		 * if the Neigbour is at top, break the top Wall
//		 */
//		else if (randomPosition[0] - 1 == randomNeigbourPosition[0]
//				&& randomPosition[1] == randomNeigbourPosition[1])
//			destroyWall(2, 3);
//
//		/**
//		 * if the Neigbour is at bottom, break the bottom Wall
//		 */
//		else if (randomPosition[0] + 1 == randomNeigbourPosition[0]
//				&& randomPosition[1] == randomNeigbourPosition[1])
//			destroyWall(3, 2);
//
//		/**
//		 * just to see if it is always a neighbour ;-)
//		 */
//		else
//			System.out.println("not a neighbour !!!!");
//
//	}
//
//	public void destroyWall(int whichWall, int whichWall2) {
//
//		_maze.breakWallBetweenCells(randomCell, NeighbourCell);
//	}
//
//	public boolean checkRoots() {
//		/**
//		 * just do anything, if the roots of this two cells are not the same
//		 */
//		if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {
//			return true;
//		}
//
//		return false;
//
//	}

}
