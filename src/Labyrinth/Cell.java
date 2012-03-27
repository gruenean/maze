package Labyrinth;

/**
 * @author green
 * 
 */
public class Cell {

	private Cell root;
	private String value; // 1=wall / 0 = no wall
	// private Cell[] neighbours;
	private String[] walls = { "1", "1", "1", "1" };
	private int rowPosition;
	private int colPosition;

	public Cell(int row, int col) {
		this.root = this;
		rowPosition = row;
		colPosition = col;
	}

	/**
	 * @return current root of this cell
	 */
	public Cell getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            element for this cell
	 */
	public void setRoot(Cell root) {
		this.root = root;
	}

	/**
	 * @param value
	 *            This value is a Tag. If all cell has the same tag, it is
	 *            possible to find a way through the labyrinth
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public int getrowPosition() {
		return rowPosition;

	}

	public int getcolPosition() {
		return colPosition;

	}

}
