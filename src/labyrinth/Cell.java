package labyrinth;

/**
 * @author green
 * 
 */
public class Cell {

	private Cell root;
	private String state;
	private String value; // 1=wall / 0 = no wall
	private boolean isWall;
	private boolean isBreakable;
	public Cell(boolean isWall, boolean isBreakable) {
		this.isWall = isWall;
		this.isBreakable = isBreakable;
		this.root = this;
	}

	public boolean isWall() {
		return isWall;
	}
	
	public boolean isBreakable() {
		return isBreakable;
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
	
	public void setState(String state) {
		this.state= state;
	}

	public String getState() {
		return state;
	}
	
	protected void setisWall(boolean isWall) {
		this.isWall = isWall;
	}
	
	protected void setisBreakable(boolean isBreakable) {
		this.isBreakable = isBreakable;
	}
}
