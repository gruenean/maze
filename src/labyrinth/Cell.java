package labyrinth;

/**
 * @author green
 * 
 */
public class Cell {

	private Cell root;
	private String state; //C=cell, B=breakable wall, U=unbreakable wall
	private String value;
	private boolean isWall;
	private boolean isBreakable;
	public Cell(boolean isWall, boolean isBreakable) {
		this.isWall = isWall;
		this.isBreakable = isBreakable;
		this.root = this;
	}

	/**
	 * @return	returns whether this particular cell is a (un)breakable wall or not
	 */
	protected boolean isWall() {
		return isWall;
	}
	
	/**
	 * @return	returns whether this particular cell is a breakable wall or not.
	 */
	protected boolean isBreakable() {
		return isBreakable;
	}
	
	/**
	 * @return current root of this cell
	 */
	public Cell getRoot() {
		return root;
	}

	/**
	 * @param root 	new root of this cell
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
	
	
	/**
	 * This method is used to set the state (e.g. C,U,B)
	 * @param state		new state to be set
	 */
	protected void setState(String state) {
		if (state == "C") {
			state=" ";
		}
		this.state= state;
	}

	/**
	 * This method is used to get the state of the cell (e.g. C,U,B)
	 * @return		state of this cell 
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * This method when a wall between two cells was broken 
	 * @param isWall	make this cell a wall or a path
	 */
	protected void setisWall(boolean isWall) {
		this.isWall = isWall;
	}
	
	/**
	 * This method is used to set a cell to (un)breakable - mostly used during the generation of a maze
	 * @param isBreakable	make this cell (un)breakable
	 */
	protected void setisBreakable(boolean isBreakable) {
		this.isBreakable = isBreakable;
	}
}
