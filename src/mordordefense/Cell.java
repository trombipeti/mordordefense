package mordordefense;

import java.util.List;

public abstract class Cell {
	/** Attributes */
	protected int numSzomszed;
	protected int ID;

	/** Associations */
	protected List<Cell> szomszedok;

	/**
	 * Operation
	 * 
	 * @return String
	 */
	abstract public String getType();

	/**
	 * Operation
	 * 
	 * @return List<Cell>
	 */
	public List<Cell> getSzomszedok() {
		return szomszedok;
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
		return ID;
	}

	/**
	 * Operation
	 * 
	 * @param i
	 */
	public void setID(int i) {
		ID = i;
	}

	/**
	 * Operation
	 * 
	 * @return int[2]
	 */
	public int[] getCoords() {
		return null;
	}
	
	public String toString() {
		return "Cella, típusa: "+getType()+"helyzet: "+getCoords()[0]+","+getCoords()[1];
	}
}
