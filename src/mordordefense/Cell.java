package mordordefense;

import java.util.List;

public abstract class Cell {
	/** Attributes */
	private int numSzomszed;

	/** Associations */
	private List<Cell> szomszedok;

	/**
	 * Operation
	 * 
	 * @return String
	 */
	public String getType() {
		return "";
	}

	/**
	 * Operation
	 * 
	 * @return List<Cell>
	 */
	public List<Cell> getSzomszedok() {
		return null;
	}

	/**
	 * Operation
	 * 
	 * @param n
	 * @param szomszed
	 */
	public void setSzomszed(int n, Cell szomszed) {

	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getID() {
		return 0;
	}

	/**
	 * Operation
	 * 
	 * @param i
	 */
	public void setID(int i) {

	}

	/**
	 * Operation
	 * 
	 * @return int[2]
	 */
	public int[] getCoords() {
		return null;
	}
}
