package algorithms.generation.create;

import labyrinth.Cell;
import labyrinth.Maze;

public class ownCreatingAlgo_BACKUP extends CreatingAlgorithms implements ICreatingAlgorithms{

	Cell randomCell;
	Cell NeighbourCell;

	public ownCreatingAlgo_BACKUP(Maze maze) {
		super(maze);
		setName("<<eigener CreatingAlgo>>");
	}

	public void defineStartandEndCell() {
		// TODO Auto-generated method stub
	}

	public void createMaze() {
		while (_maze.hasMultipleRoots()) {
		//	for (int i = 1; i < (_maze.getRowsAndCols()[0] * _maze.getRowsAndCols()[1] * 10 ^ HelpInt); i++) {
				randomCell = _maze.getRandomCell();
				NeighbourCell = _maze.getRandomNeighbour(randomCell);				

				if (!randomCell.getRoot().equals(NeighbourCell.getRoot())) {
					_maze.breakWallBetweenCells(randomCell, NeighbourCell);
//TODO: comment out the following four lines as these are testing only
					_maze.printAsciiMaze();
					System.out.println();
				} else {
					System.out.println("impossibru");
				}				
		//	}
		}
//		long EndTime = System.currentTimeMillis();
//		System.out.println(EndTime);
//		System.out.println(EndTime - BeginTime);
//		System.out.println("Anzahl Duchg�nge: " + anzahlDurchg�nge);
		System.out.println("--MAP--");
		_maze.printMap();
		System.out.println("--ROOTS--");
		_maze.printRoots();
		System.out.println("--ASCI MAP--");
		_maze.printAsciiMaze();
	}
	public void createMaze2() {

		/**
		 * do this loop rows * cols * 5 and then look if all cell has the same
		 * root. If yes, there is a way in the maze.
		 */
		// long BeginTime = System.currentTimeMillis();
		// System.out.println(BeginTime);
		int HelpInt = 4;
		// int anzahlDurchg�nge = 0;
		while (_maze.hasMultipleRoots()) {
			// System.out.println("Exist just one Root?! = " +
			// _maze.existJustOneRoot());

			for (int i = 1; i < (_maze.getRowsAndCols()[0]
					* _maze.getRowsAndCols()[1] * 10 ^ HelpInt); i++) {
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
			if (HelpInt > 0)
				HelpInt = HelpInt - 1;
//			anzahlDurchg�nge = anzahlDurchg�nge + 1;
		}
		long EndTime = System.currentTimeMillis();
//		System.out.println(EndTime);
//		System.out.println(EndTime - BeginTime);
//		System.out.println("Anzahl Duchg�nge: " + anzahlDurchg�nge);
		_maze.printMap();
		System.out.println("");
		_maze.printRoots();

	}

	// }


	
	/**@deprecated
	 * This is now handled by maze.breakWallBetweenCells(Cell cell, Cell neighbourcell)
	 * TODO: Delete it!
	 * 
	 */
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
//		randomCell.destroyWall(whichWall);
//		NeighbourCell.destroyWall(whichWall2);
//
//		
//		_maze.updateRoots(randomCell, NeighbourCell);

		_maze.breakWallBetweenCells(randomCell, NeighbourCell);
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
