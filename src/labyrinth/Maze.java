package labyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import main.Conf;

public class Maze {
	private int _rows, _cols;
	private Cell[][] map;
	private Map<Cell, int[]> positions;
	private List<Cell> allCells;
	private List<Cell> allRoots;
	private Conf _globalConf;

	public Maze(int rows, int cols, Conf globalConf) {
		this._globalConf = globalConf;
		_rows = 2 * rows + 1;
		_cols = 2 * cols + 1;
		map = new Cell[_rows][_cols];
		positions = new HashMap<Cell, int[]>(); // cell, position
		allCells = new ArrayList<Cell>();
		allRoots = new ArrayList<Cell>();

		generateMap();
	}

	/**
	 * TODO: still used?
	 * @return
	 */
	public int[] getLastPosition() {
		int[] temp = { _rows, _cols };
		return temp;
	}

	
	/**
	 * TODO: still used?
	 * Returns the start cell
	 * 
	 * @return start of maze
	 */
	public Cell getStartCell() {
		return getCellOnPosition(1, 0);
	}

	
	/**
	 * TODO: still used?
	 * Returns the end cell
	 *  
	 * @return end of maze
	 */
	public Cell getEndCell() {
		return getCellOnPosition(_cols - 2, _rows - 1);
	}

	/**
	 * Returns the cell on a given Position
	 * 
	 * @param rows
	 * @param cols
	 * @return
	 */
	public Cell getCellOnPosition(int rows, int cols) {
//		_globalConf.get_output().printLine(
//				"Will Zelle an Position " + rows + " + " + cols);
//
//		_globalConf.get_output().printLine("Map Length = " + map.length);
		return map[rows][cols];

	}

	/**
	 * creates the Map of the Labyrinth with a Cell[][] -Array.
	 */
	private void generateMap() {
		for (int row = 0; row < _rows; row++) {
			for (int col = 0; col < _cols; col++) {

				Cell newCell;
				// Unbreakable walls
				if (row == 0 || col == 0 || row == _rows - 1
						|| col == _cols - 1) {
					newCell = new Cell(true, false);
					newCell.setState("U");
				}

				else if (row % 2 == 0 && col % 2 == 0) {
					newCell = new Cell(true, false);
					newCell.setState("U");
				}

				// Breakable walls
				else if (row % 2 == 0 && col % 2 == 1) {
					newCell = new Cell(true, true);
					newCell.setState("B");
				} else if (row % 2 == 1 && col % 2 == 0) {
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
				/**
				 * set the Value of the generated Cell (lengt = always 3)
				 */

				String rowString = "" + row;
				if (rowString.length() == 1)
					rowString = "0" + rowString;
				// else if (rowString.length()==2) rowString="0"+rowString;

				String colString = "" + col;
				if (colString.length() == 1)
					colString = "0" + colString;
				// else if (colString.length()==2) colString="0"+colString;

				newCell.setValue("" + rowString + " " + colString);

				// TODO Testing only - remove
				map[row][col] = newCell;
				positions.put(newCell, new int[] { row, col });
			}
		}
	}

	/**
	 * Prints the maze showing the values of all cells and walls
	 */
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
//				String currentCell = map[i][j].getValue();
				_globalConf.get_output().printLine("[" + map[i][j].getValue() + "]");

				if (j == map.length - 1) {
					_globalConf.get_output().printLine(" ");
				}
			}
		}
	}

	/**
	 * Prints the maze showing the values of the root of each cell or wall
	 */
	public void printRoots() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				_globalConf.get_output().printLine(
						"[" + map[i][j].getRoot().getValue() + "]");
				if (j == map.length - 1) {
					_globalConf.get_output().printLine(" ");
				}
			}
		}
	}

	/**
	 * Prints an ascii maze as follows: B = breakable wall U = unbreakable wall
	 * C = cells
	 */
	public void printAsciiMaze() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				_globalConf.get_output().print(map[i][j].getState());
				if (j == map.length - 1) {
					_globalConf.get_output().printLine(" ");
				}
			}
		}
	}
	/**
	 * Marks the first cell as entrance (top-left) and the last as exit (bottom-right)
	 */
	public void createEntranceAndExit(){
		map[1][0].setState(" ");
		map[1][0].setisBreakable(false);
		map[_rows-2][_cols-1].setState(" ");
		map[_rows-2][_cols-1].setisBreakable(false);
	}

	/**
	 * This method is used to select a cell randomly
	 * 
	 * @return returns a randomly selected cell
	 */
	public Cell getRandomCell() {
		return allCells.get(new Random().nextInt(allCells.size()));
	}

	/**
	 * 
	 * @param cell
	 * @return position of the given cell
	 */
	public int[] getPositionOfCell(Cell cell) {
		// System.out.println("positions = " + positions.get(cell)[0] +
		// positions.get(cell)[1]);
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
	 * This method returns a randomly chosen neighbour of a given cell
	 * 
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
			neighbourPositions
					.add(new int[] { currentPos[0], currentPos[1] - 2 }); // neighbour
																			// left
		if ((currentPos[1] + 2 < _cols))
			neighbourPositions
					.add(new int[] { currentPos[0], currentPos[1] + 2 }); // neighbour
																			// right
		if ((currentPos[0] - 2 >= 0))
			neighbourPositions
					.add(new int[] { currentPos[0] - 2, currentPos[1] }); // neighbour
																			// top
		if ((currentPos[0] + 2 < _rows))
			neighbourPositions
					.add(new int[] { currentPos[0] + 2, currentPos[1] }); // neighbour
																			// bottoom

		Collections.shuffle(neighbourPositions);
		return getCellOnPosition(neighbourPositions.get(new Random()
				.nextInt(neighbourPositions.size())));
	}

	/**
	 * @param cell
	 * @param wall
	 * @return gives the Neigbour of the inserted Cell back. int wall defines
	 *         which Neibour. (0=left,
	 */
	public Cell getNeigbourofCell(Cell cell, int wall) {
		// System.out.println("W�nsche Nachbar bei Wand: " + wall);
		int[] currentPos = this.getPositionOfCell(cell);
		{
			// System.out.println("Suche Nachbar bei Walls = " + wall);
			if (Conf.LEFT_WALL == wall)
				return this.getCellOnPosition(new int[] { currentPos[0],
						currentPos[1] - 1 }); // neighbour
			// left
			if (Conf.RIGHT_WALL == wall)
				return this.getCellOnPosition(new int[] { currentPos[0],
						currentPos[1] + 1 }); // neighbour right
			if (Conf.TOP_WALL == wall)
				return this.getCellOnPosition(new int[] { currentPos[0] - 1,
						currentPos[1] }); // neighbour top
			if (Conf.BOTTOM_WALL == wall)
				return this.getCellOnPosition(new int[] { currentPos[0] + 1,
						currentPos[1] }); // neighbour bottom

		}
		return null;
	}

	/**
	 * This method takes care of cells belonging to the same root.
	 * 
	 * @param cell1
	 * @param cell2
	 */
	private void updateRoots(Cell cell1, Cell cell2) {
		Cell obsoleteRoot = cell2.getRoot();
		Cell root = cell1.getRoot();

		for (Cell entry : allCells) {
			if (entry.getRoot().equals(obsoleteRoot)) {
				entry.setRoot(root);
				allRoots.remove(obsoleteRoot);
			}
		}
	}

	/**
	 * This method handles the deletion of a wall between to cells
	 * 
	 * @param cell1
	 * @param cell2
	 */
	public void breakWallBetweenCells(Cell cell1, Cell cell2) {
		int[] pos1 = getPositionOfCell(cell1);
		int[] pos2 = getPositionOfCell(cell2);

		int[] wallPos = new int[] { (pos1[0] + pos2[0]) / 2,
				(pos1[1] + pos2[1]) / 2 };
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

	/**
	 * @deprecated THIS METHOD WILL BE DELETED SOON TODO: delete method!!! seems
	 *             not to be used anymore.
	 * 
	 */
	public int[] getRowsAndCols() {
		int[] rowscols = { _rows, _cols };
		return rowscols;

	}

	public int getRows() {
		return _rows;
	}

	public int getCols() {
		return _cols;
	}

	public List<Cell> getAllWalls() {
		List<Cell> allWalls = new ArrayList<Cell>();
		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map.length; j++) {
				String currentState = map[i][j].getState();
				if (currentState == "U" || currentState == "B") {
					allWalls.add(map[j][i]);
				}
			}

		}

		return allWalls;

	}

	public void printMapRootsAsci() {
		printMap();
		printRoots();
		printAsciiMaze();
	}
}
