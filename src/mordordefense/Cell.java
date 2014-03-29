package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

/**
 * Absztrakt osztály a pálya mezőinek heterogén kollekciójához.
 * 
 */
public abstract class Cell {

	/**
	 * A szomszédok száma
	 */
	protected int numSzomszed;

	/**
	 * A cella ID-je
	 */
	protected int ID;

	/**
	 * A cella koordinátái a pályán
	 */
	protected int coords[] = new int[2];

	/**
	 * A cella szomszédai. Amelyik null, az a pálya szélét jelenti.
	 */
	protected List<Cell> szomszedok = new ArrayList<Cell>();

	/**
	 * típust visszaadó absztrakt függvény
	 * 
	 * @return String A Cell típusa, pl RouteCell
	 */
	abstract public String getType();

	/**
	 * paraméter nélküli konstruktor
	 */
	protected Cell() {
		Logging.log(">> Cell.Cell() konstruktor hívás");
	}

	/**
	 * koordinátákat beállító konstruktor
	 * 
	 * @param x
	 *            Az x koordináta
	 * @param y
	 *            Az y koordináta
	 */

	protected Cell(int x, int y) {
		coords[0] = x;
		coords[1] = y;
		Logging.log(">> Cell.Cell() konstruktor hívás, paraméterek: " + "x: "
				+ x + ", y:" + y);
	}

	/**
	 * Szomszédokat visszaadó függvény
	 * 
	 * @return List<Cell> A cella szomszédai.
	 */
	public List<Cell> getSzomszedok() {
		Logging.log(">> Cell.getSzomszedok() hívás");
		Logging.log("<< " + szomszedok.toString());
		return szomszedok;
	}

	/**
	 * Szomszédokat beállító függvény
	 * 
	 * @param n
	 *            Melyik szomszéd. óra járásának megfelelően 0-tól
	 * @param szomszed
	 *            Ki az a bizonyos szomszéd. Lehet null, ez pályaszélet jelent.
	 */
	public void setSzomszed(int n, Cell szomszed) {
		Logging.log(">> Cell.setSzomszedok() hívás, paraméterek: " + "n: " + n
				+ ", szomszed: " + szomszed.toString());
		Logging.log("<< void");
		szomszedok.add(szomszed);
	}

	/**
	 * ID-t visszadó függvény
	 * 
	 * @return int A cella aktuális ID-je
	 */
	public int getID() {
		Logging.log(">> Cell.getID() hívás");
		Logging.log("<< " + ID);
		return ID;
	}

	/**
	 * ID-t beállító függvény
	 * 
	 * @param i
	 *            A cella új ID-je
	 */
	public void setID(int i) {
		Logging.log(">> Cell.setID() hívás, paraméter: " + i);
		ID = i;
		Logging.log("<< void");
	}

	/**
	 * koordinátákat visszaadó függvény
	 * 
	 * @return int[] A cella koordinátái egy két elemű int tömbben.
	 */
	public int[] getCoords() {
		Logging.log(">> Cell.getCoords() hívás");
		Logging.log("<< " + coords[0] + ", " + coords[1]);
		return coords;
	}

	public String toString() {
		return "Cella, típusa: " + getType() + ", ID: " + ID + ", helyzet: "
				+ coords[0] + "," + coords[1];
	}
	
	public static double Distance(Cell c1, Cell c2) {
		double ret = 0.0f;
		int x1 = c1.getCoords()[0];
		int y1 = c1.getCoords()[1];
		int x2 = c2.getCoords()[0];
		int y2 = c2.getCoords()[1];
		ret = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		return ret;
	}
}
