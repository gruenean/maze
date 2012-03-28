package labyrinth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Maze {
	private int rows, cols;
	private Cell[][] map;
	private Map<int[], Cell> positions;
	private Map<Cell, Cell> relations;

	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		map = new Cell[rows][cols];
		positions = new HashMap<int[], Cell>();
		relations= new HashMap<Cell, Cell>();
		
		generateMap();
		//printMap();
	}

	/**
	 * creates the Map of the Labyrinth with a Cell[][] -Array.
	 */
	private void generateMap() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Cell newCell = new Cell();
				map[row][col] = newCell;
				relations.put(newCell, newCell);
				positions.put(new int[]{1,1}, newCell);
			}
		}
	}

	/**
	 * Creates the lab with the own algorithmus. It's a test method
	 */
//	public void initialisizeCells() {
//
//	}

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

	/**
	 * @return	returns a randomly selected cell
	 */
	public Cell getRandomCell() {
		return map[new Random().nextInt(map.length)][new Random().nextInt(map.length)];
	}

	/**
	 * @param cell	 
	 * @return		position of the given cell
	 */
	//TODO: set method to private - currently used for testing 
	public int[] getPositionOfCell(Cell cell) {
		for (Map.Entry<int[], Cell> entry: positions.entrySet()) {
			if (entry.getValue().equals(cell)) {
				return entry.getKey();
			}
		}
		return new int[0];
	}
	
//	public Cell getRandomNeighbour(Cell cell) {
//		int[] position = getPositionOfCell(cell);
//		ArrayList<int[]> neighbours= new ArrayList<int[]>();
//		neighbours.add(arg0)
//	}
	
//	public void create_One_Solution(){
//		
//	}
}
