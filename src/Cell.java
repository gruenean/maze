
/**
 * @author green
 *
 */
public class Cell {

	private Cell root;
	private String value; //1=wall
	//private Cell[] neighbours;
	private String[] walls = {"1","1","1","1"};
	
	/**
	 * 
	 */
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
	 * @param root element for this cell
	 */
	public void setRoot(Cell root) {
		this.root = root; 
	}
	
	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
