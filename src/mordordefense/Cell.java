package mordordefense;

import java.util.List;

import mordordefense.testing.Logging;

public abstract class Cell {
	/** Attributes */
	protected int numSzomszed;
	protected int ID;
	
	protected int coords[] = new int[2];
	
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
		Logging.log(">> Cell.getSzomszedok() hívás");
		Logging.log("<< "+szomszedok.toString());
		return szomszedok;
	}

	/**
	 * Operation
	 * 
	 * @param n
	 * @param szomszed
	 */
	public void setSzomszed(int n, Cell szomszed) {
		Logging.log(">> Cell.setSzomszedok() hívás, paraméterek: "+"n: "+n+", szomszed: "+szomszed.toString());
		Logging.log("<< void");
		szomszedok.add(szomszed);
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getID() {
		Logging.log(">>Cell.getID() hívás");
		Logging.log("<< "+ID);
		return ID;
	}

	/**
	 * Operation
	 * 
	 * @param i
	 */
	public void setID(int i) {
		Logging.log("Cell.setID() hívás, paraméter: "+i);
		ID = i;
		Logging.log("<< void");
	}

	/**
	 * Operation
	 * 
	 * @return int[2]
	 */
	public int[] getCoords() {
		Logging.log("Cell.getCoords() hívás");
		Logging.log("<< "+coords[0]+", "+coords[1]);
		return coords;
	}
	
	public String toString() {
		return "Cella, típusa: "+getType()+", helyzet: "+getCoords()[0]+","+getCoords()[1];
	}
}
