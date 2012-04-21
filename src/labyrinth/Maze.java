package labyrinth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import algorithms.generation.Conf;

public class Maze {
	private int _rows, _cols;
	private Cell[][] map;
	private Map<Cell, int[]> positions;
	private List<Cell> allCells;
	private List<Cell> allRoots;

	public Maze(int rows, int cols) {
		
		_rows = 2*rows+1;
		_cols = 2*cols+1;
		map = new Cell[_rows][_cols];
		positions = new HashMap<Cell, int[]>(); // cell, position
		allCells = new ArrayList<Cell>();
		allRoots = new ArrayList<Cell>();

		generateMap();
	}

// ?????? METHOD ALREADY EXISTS!!!!
	
//	public Cell getCellOnPosition(int rows, int cols) {
////		System.out.println(rows + " + " + cols);
//		return map[rows][cols];
//
//	}

	/**
	 * creates the Map of the Labyrinth with a Cell[][] -Array.
	 */
	private void generateMap() {
		for (int row = 0; row < _rows; row++) {
			for (int col = 0; col < _cols; col++) {
	
				Cell newCell;
				// Unbreakable walls
				if (row==0 || col ==0 || row == _rows -1 || col == _cols -1) {
					newCell = new Cell(true, false);
					newCell.setState("U");
				}
					
				else if (row%2 == 0 && col%2 == 0) {
					newCell = new Cell(true, false);
					newCell.setState("U");
				}

				// Breakable walls
				else if (row%2 == 0 && col%2 == 1) {
					newCell = new Cell(true, true);
					newCell.setState("B");
				}
				else if (row%2 == 1 && col%2 == 0) {
					newCell = new Cell(true, true);
					newCell.setState("B");
				}
				
				// Cell
				else {
					newCell = new Cell(false, false);
					newCell.setState("C");
					allRoots.add(newCell);
					allCells.add(newCell);
				}
				newCell.setValue("" + row + col);
				
				// TODO Testing only - remove
				map[row][col] = newCell;
				positions.put(newCell, new int[] { row, col });
			}
		}
	}

	/**
	 * Creates the lab with the own algorithmus. It's a test method
	 */
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print("[" + map[i][j].getValue() + "]");
				if (j == map.length - 1) {
					System.out.println();
				}
			}
		}
	}

	public void printRoots() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				// System.out.print("[" + map[i][j].getRoot() + "]");
				System.out.print("[" + map[i][j].getRoot().getValue() + "]");
				if (j == map.length - 1) {
					System.out.println();
				}
			}
		}
	}

	public void printAsciiMaze() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j].getState());
				if (j == map.length - 1) {
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * @return returns a randomly selected cell
	 */
	public Cell getRandomCell() {
		//return map[new Random().nextInt(map.length)][new Random().nextInt(map.length)];
		return allCells.get(new Random().nextInt(allCells.size()));
	}

	/**
	 * @param cell
	 * @return position of the given cell
	 */
	public int[] getPositionOfCell(Cell cell) {
		return positions.get(cell);
	}

	// TODO: 'return new Cell()' ugly
	private Cell getCellOnPosition(int[] pos) {
		for (Map.Entry<Cell, int[]> entry : positions.entrySet()) {
			if (entry.getValue()[0] == pos[0] && entry.getValue()[1] == pos[1]) {
				return entry.getKey();
			}
		}
		return new Cell(true, false);
	}

	/**
	 * @param
	 * @return Returns a randomly chosen neighbour of input cell
	 */
	public Cell getRandomNeighbour(Cell cell) {
		int[] currentPos = getPositionOfCell(cell);

		/**
		 * fill a Array with all possibles Neighbours. If the selected Cell is
		 * at a end of the maze (top, right, left, bottom) then it could be that
		 * the cell has only 2 or 3 instead of 4 neighbours
		 */
		ArrayList<int[]> neighbourPositions = new ArrayList<int[]>();
		if ((currentPos[1] - 2 >= 0))
			neighbourPositions.add(new int[] { currentPos[0], currentPos[1] - 2 }); // neighbour left
		if ((currentPos[1] + 2 < _cols))
			neighbourPositions.add(new int[] { currentPos[0], currentPos[1] + 2 }); // neighbour right
		if ((currentPos[0] - 2 >= 0))
			neighbourPositions.add(new int[] { currentPos[0] - 2, currentPos[1] }); // neighbour top
		if ((currentPos[0] + 2 < _rows))
			neighbourPositions.add(new int[] { currentPos[0] + 2, currentPos[1] }); // neighbour bottoom

		return getCellOnPosition(neighbourPositions.get(new Random().nextInt(neighbourPositions.size())));
	}


	/**
	 * 
	 * @param cell
	 * @param wall
	 * @return gives the Neigbour of the inserted Cell back. int wall defines
	 *         which Neibour. (0=left,
	 */

	// ?? redundancy department of redundandcy?! 
	// pretty much the same as 'getRandomNeighbour()'
//	public Cell getNeigbourofCell(Cell cell, int wall) {
//System.out.println("W�nsche Nachbar bei Wand: " + wall);
//		int[] currentPos = this.getPositionOfCell(cell);
//		int[] neighbourPositions;
//		{
//			// System.out.println("Suche Nachbar bei Walls = " + wall);
//			if (Conf.LEFT_WALL == wall)
//				return this.getCellOnPosition(new int[] { currentPos[0], currentPos[1] - 1 }); // neighbour
//																					// left
//			if (Conf.RIGHT_WALL == wall)
//				return this.getCellOnPosition(new int[] { currentPos[0], currentPos[1] + 1 }); // neighbour right
//			if (Conf.TOP_WALL == wall)
//				return this.getCellOnPosition(new int[] { currentPos[0] - 1, currentPos[1] }); // neighbour top
//			if (Conf.BOTTOM_WALL == wall)
//				return this.getCellOnPosition(new int[] { currentPos[0] + 1, currentPos[1]}); // neighbour bottom
//
//		}
//		return null;
//	}

	/**
	 * This method takes care of cells belonging to the same root.
	 * 
	 * @param cell1
	 * @param cell2
	 */
	public void updateRoots(Cell cell1, Cell cell2) {
		Cell obsoleteRoot = cell2.getRoot();
		Cell root = cell1.getRoot();

		for (Cell entry : allCells) {
			if (entry.getRoot().equals(obsoleteRoot)) {
				entry.setRoot(root);
				allRoots.remove(obsoleteRoot);
			}
		}
	}

	public void breakWallBetweenCells(Cell cell1, Cell cell2) {
		int[] pos1 = getPositionOfCell(cell1);
		int[] pos2 = getPositionOfCell(cell2);

		//return getCellOnPosition(new int[] {(pos1[0]+pos2[0])/2,(pos1[1]+pos2[1])/2 });
		int[] wallPos =  new int[] {(pos1[0]+pos2[0])/2,(pos1[1]+pos2[1])/2 };
		Cell wall = getCellOnPosition(wallPos);
		if (wall.isWall() && wall.isBreakable()) {
			wall.setState("C");
			wall.setisWall(false);
			wall.setisBreakable(false);
		}
		
		updateRoots(cell1, cell2);
	}
	
	/**
	 * @return true if still more than one root exists
	 */
	public boolean hasMultipleRoots() {
		if (allRoots.size() > 1)
			return true;
		return false;
	}

	public int[] getRowsAndCols() {
		int[] rowscols = { _rows, _cols };
		return rowscols;

	}

	/**
	 * This is just for Testing
	 */
	public void sysoutAllCellAndRoots() {
		printMap();
		printRoots();
		printAsciiMaze();
	}

}
