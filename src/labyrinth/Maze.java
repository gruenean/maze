package labyrinth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Maze {
	private int rows, cols;
	private Cell[][] map;
	private Map<String, Cell> positions;
	private Map<Cell, Cell> relations;

	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		map = new Cell[rows][cols];
		positions = new HashMap<String, Cell>();
		relations= new HashMap<Cell, Cell>();
		
		generateMap();
		printMap();
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
				positions.put(""+row+"."+col, newCell);
			}
		}
	}

	/**
	 * Creates the lab with the own algorithmus. It's a test method
	 */
	public void initialisizeCells() {

	}

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

	
	
//	public void create_One_Solution(){
//		
//	}
}
