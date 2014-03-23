package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

public abstract class Cell {
	/** Attributes */
	protected int numSzomszed;
	protected int ID;
	
	protected int coords[] = new int[2];
	
	protected List<Cell> szomszedok = new ArrayList<Cell>();
	
	public Cell() {
		
	}

	/**
	 * típust visszaadó absztrakt függvény
	 * 
	 * @return String
	 */
	abstract public String getType();
	
	/**
	 * koordinátákat beállító konstruktor
	 * @param x
	 * @param y
	 */
	
	protected Cell(int x, int y){
		coords[0]=x;
		coords[1]=y;
	}
	
	/**
	 * Szomszédokat visszaadó függvény
	 * 
	 * @return List<Cell>
	 */
	public List<Cell> getSzomszedok() {
		Logging.log(">> Cell.getSzomszedok() hívás");
		Logging.log("<< "+szomszedok.toString());
		return szomszedok;
	}

	/**
	 * Szomszédokat beállító függvény
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
	 * ID-t visszadó függvény
	 * 
	 * @return int
	 */
	public int getID() {
		Logging.log(">>Cell.getID() hívás");
		Logging.log("<< "+ID);
		return ID;
	}

	/**
	 * ID-t beállító függvény
	 * 
	 * @param i
	 */
	public void setID(int i) {
		Logging.log("Cell.setID() hívás, paraméter: "+i);
		ID = i;
		Logging.log("<< void");
	}

	/**
	 * koordinátákat visszaadó függvény
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
