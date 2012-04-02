package labyrinth;

/**
 * @author green
 * 
 */
public class Cell {

	private Cell root;
	private String value; // 1=wall / 0 = no wall
	private int[] walls = { 1, 1, 1, 1 }; // left,right,top,bottom

	public Cell() {
		this.root = this;
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
	 * @param wall
	 *            to destroy
	 */
	public void destroyWall(int wall) {
		walls[wall] = 0;
		// System.out.println("Die Wand " +wall + " wurde abgerissen");
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
}
