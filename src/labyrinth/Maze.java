package labyrinth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Maze {
	private int rows, cols;
	private Cell[][] map;
	private Map<Cell, int[]> positions;
	private Map<Cell, Cell> relations;
	private List<Cell> allCells;
	private List<Cell> allRoots;

	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		map = new Cell[rows][cols];
		positions = new HashMap<Cell, int[]>(); // cell, position
		relations = new HashMap<Cell, Cell>(); // cell, root
		allCells = new ArrayList<Cell>();
		allRoots = new ArrayList<Cell>();

		generateMap();
	}

	/**
	 * creates the Map of the Labyrinth with a Cell[][] -Array.
	 */
	private void generateMap() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Cell newCell = new Cell();

				// TODO Testing only - remove
				newCell.setValue("" + row + col);
				map[row][col] = newCell;
				//relations.put(newCell, newCell);
				positions.put(newCell, new int[] { row, col });
				allCells.add(newCell);
				allRoots.add(newCell);
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
				System.out.print("[" + map[i][j].getRoot() + "]");
//				System.out.print("[" + map[i][j].getRoot().getValue() + "]");
				if (j == map.length - 1) {
					System.out.println();
				}
			}
		}
	}

	public boolean existJustOneRoot() {

		int[] startPosition = { 0, 0 };
		int equals = 0;
		Cell startRoot = getCellOnPosition(startPosition);
		// for (int i = 0; i < relations.size(); i++) {
		// System.out.println(startRoot);

		for (Map.Entry<Cell, Cell> entry : relations.entrySet()) {
			if (startRoot.equals(entry.getValue()))
				equals = equals + 1;
			// System.out.println(entry.getValue() + "            " +
			// entry.getKey());
		}
		// System.out.println(equals);
		if (equals == 1)
			return false;
		else
			return true;
	}

	/**
	 * @return returns a randomly selected cell
	 */
	public Cell getRandomCell() {
		return map[new Random().nextInt(map.length)][new Random().nextInt(map.length)];
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
		return new Cell();
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
		if ((currentPos[1] - 1 >= 0))
			neighbourPositions.add(new int[] { currentPos[0], currentPos[1] - 1 }); // neighbour left
		if ((currentPos[1] + 1 < cols))
			neighbourPositions.add(new int[] { currentPos[0], currentPos[1] + 1 }); // neighbour right
		if ((currentPos[0] - 1 >= 0))
			neighbourPositions.add(new int[] { currentPos[0] - 1, currentPos[1] }); // neighbour top
		if ((currentPos[0] + 1 < rows))
			neighbourPositions.add(new int[] { currentPos[0] + 1, currentPos[1] }); // neighbour bottom

		// TODO: Testing only
		// getCellOnPosition(neighbourPositions.get(0)).setValue("LL");
		// getCellOnPosition(neighbourPositions.get(1)).setValue("RR");
		// getCellOnPosition(neighbourPositions.get(2)).setValue("TT");
		// getCellOnPosition(neighbourPositions.get(3)).setValue("BB");

		// for testing
		// System.out.println(getCellOnPosition(neighbourPositions.get(new
		// Random().nextInt(neighbourPositions.size()))));
		// System.out.println(neighbourPositions.size());

		return getCellOnPosition(neighbourPositions.get(new Random().nextInt(neighbourPositions.size())));
	}

	// TODO: set to protected after testing
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
		

	public boolean hasMultipleRoots() {
		if(allRoots.size() > 1 )
			return true;
		return false;
	}
	public int[] getRowsAndCols() {
		int[] rowscols = { rows, cols };
		return rowscols;

	}

	/**
	 * This is just for Testing
	 */
	public void sysoutAllCellAndRoots() {

		 for (Map.Entry<Cell, Cell> entry : relations.entrySet()) {
		
		 System.out.println("Zelle:      " + entry.getValue()
		 + "                         Root zu der Zelle:  " + entry.getKey());
		 }

	}

	// public void create_One_Solution(){
	//
	// }
}
