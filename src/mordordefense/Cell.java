package mordordefense;

import java.util.TreeMap;

import mordordefense.testing.Logging;

/**
 * Absztrakt osztály a pálya mezőinek heterogén kollekciójához.
 * 
 */
public abstract class Cell {

	public static enum Dir {
		UP(0, "UP"), RIGHT(1, "RIGHT"), DOWN(2, "DOWN"), LEFT(3, "LEFT");

		private final int value;
		private final String name;

		private Dir(int val, String name) {
			value = val;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public String toString() {
			return name;
		}
	}

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
	protected TreeMap<Integer, Cell> szomszedok = new TreeMap<Integer, Cell>();

	/**
	 * Tárolja, hogy már ismeri-e a szomszédait. Ez azért kell, hogy rekurzívan
	 * lehessen beállítani a Controllerben a szomszédokat.
	 */
	protected boolean neighborsKnown = false;

	public boolean isNeighborsKnown() {
		return neighborsKnown;
	}

	public void setNeighborsKnown(boolean neighborsKnown) {
		this.neighborsKnown = neighborsKnown;
	}

	/**
	 * típust visszaadó absztrakt függvény
	 * 
	 * @return String A Cell típusa, pl RouteCell
	 */
	abstract public String getType();

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
		for (int i = 0; i < 4; ++i) {
			szomszedok.put(i, null);
		}
	}

	protected Cell(int x, int y, int ID) {
		coords[0] = x;
		coords[1] = y;
		this.ID = ID;
		Logging.log(">> Cell.Cell() konstruktor hívás, paraméterek: " + "x: "
				+ x + ", y: " + y + ", ID: " + ID);
		for (int i = 0; i < 4; ++i) {
			szomszedok.put(i, null);
		}
	}

	/**
	 * Szomszédokat visszaadó függvény
	 * 
	 * @return ArrayList<Cell> A cella szomszédai.
	 */
	public TreeMap<Integer, Cell> getSzomszedok() {
		Logging.log(">> Cell.getSzomszedok() hívás");
		Logging.log("<< " + szomszedok.toString());
		return szomszedok;
	}

	/**
	 * Szomszédokat beállító függvény
	 * 
	 * @param d
	 *            Melyik szomszéd.
	 * @param szomszed
	 *            Ki az a bizonyos szomszéd. Lehet null, ez pályaszélet jelent.
	 */
	public void setSzomszed(Dir d, Cell szomszed) {
		Logging.log(">> Cell.setSzomszed() hívás, paraméterek: " + "n: "
				+ d.toString() + ", szomszed: "
				+ (szomszed == null ? "null" : szomszed.toString()));
		szomszedok.put(d.getValue(), szomszed);
		Logging.log("<< void");
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
		ret = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		return ret;
	}
}
