package labyrinth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Maze {
	private int rows, cols;
	private Cell[][] map;
	private Map<Cell, int[]> positions;
	private Map<Cell, Cell> relations;

	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		map = new Cell[rows][cols];
		positions = new HashMap<Cell, int[]>();
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
				
				//TODO Testing only - remove
				newCell.setValue(""+row+col);
				map[row][col] = newCell;
				relations.put(newCell, newCell);
				positions.put(newCell, new int[]{row,col});
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
	private int[] getPositionOfCell(Cell cell) {
		return positions.get(cell);
	}
	
	//TODO: 'return new Cell()' ugly
	private Cell getCellOnPosition(int[] pos) {
		for (Map.Entry<Cell, int[]> entry: positions.entrySet()) {
			if (entry.getValue()[0] == pos[0] && entry.getValue()[1] == pos[1]) {
				return entry.getKey();
			}
		}
		return new Cell();
	}
	
	/**
	 * @param 	
	 * @return 	Returns a randomly chosen neighbour of input cell
	 */
	public Cell getRandomNeighbour(Cell cell) {
		int[] currentPos = getPositionOfCell(cell);
		
		ArrayList<int[]> neighbourPositions= new ArrayList<int[]>();
		neighbourPositions.add(new int[]{currentPos[0],currentPos[1]-1}); // neighour left
		neighbourPositions.add(new int[]{currentPos[0],currentPos[1]+1}); // neighbour right
		neighbourPositions.add(new int[]{currentPos[0]-1,currentPos[1]}); // neighbour top
		neighbourPositions.add(new int[]{currentPos[0]+1,currentPos[1]}); // neighour bottom
		
		getCellOnPosition(neighbourPositions.get(0)).setValue("LL");
		getCellOnPosition(neighbourPositions.get(1)).setValue("RR");
		getCellOnPosition(neighbourPositions.get(2)).setValue("TT");
		getCellOnPosition(neighbourPositions.get(3)).setValue("BB");
		
		return getCellOnPosition(neighbourPositions.get(new Random().nextInt(neighbourPositions.size())));
	}
	
//	public void create_One_Solution(){
//		
//	}
}
