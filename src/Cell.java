
/**
 * @author green
 *
 */
public class Cell {

	private Cell root;
	private String value; //1=wall
	private Cell[] neighbours;
	private String nwall,swall,wwall,ewall;
	
	/**
	 * 
	 */
	public Cell() {
		this.root = this;
		nwall = "1";
		swall = "1";
		wwall = "1";
		ewall = "1";
		value = "1";
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
