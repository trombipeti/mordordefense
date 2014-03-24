package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * A játékot vezérlő "játékmester" objektum.
 * 
 */
public class Controller implements RouteCellListener {

	/**
	 * Mordor koordinátái.
	 * 
	 * @see MordorCell
	 */
	public int[] mordorCoords;

	/**
	 * Az indulópont koordinátái.
	 * 
	 * @see SpawnPointCell
	 */
	public int[] spawnCoords;

	/**
	 * Maximum hány enemy indulhat el.
	 */
	private int maxEnemyNum;

	/**
	 * A pályán lévő csapdák
	 */
	private List<Trap> traps = new ArrayList<Trap>();

	/**
	 * A pályán lévő tornyok
	 */
	private List<Tower> towers = new ArrayList<Tower>();

	/**
	 * Szarumán
	 */
	private Saruman saruman;

	/**
	 * A pályán lévő ellenségek
	 */
	private List<Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * A pályán lévő cellák
	 */
	private ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();

	/**
	 * Azt tárolja, hogy véget ért-e a játék.
	 */
	private volatile boolean gameEnded = false;

	/**
	 * A győzteseket tároló StringBuffer. Azért ilyen típusú, hogy szálbiztos
	 * legyen.
	 */
	private volatile StringBuffer winner = null;

	/**
	 * Konstruktor
	 * 
	 * @param n
	 *            hány ellenséget tehet le
	 */
	public Controller(int n) {
		Logging.log(">> Controller konstruktor hívás, paraméter:" + n);
		maxEnemyNum = n;
	}

	/**
	 * Inicializáló függvény később egy config fájlból fogja a pályát beolvasni
	 */
	public void init() {
		Logging.log("Controller.init() hívás");
		saruman = new Saruman(100);
		SpawnPointCell rc = new SpawnPointCell(0, 0);
		rc.setID(0);
		MordorCell mc = new MordorCell(0, 1);
		mc.setID(1);
		ArrayList row = new ArrayList<Cell>();
		row.add(rc);
		row.add(mc);
		mc.addRouteCellListener(this);
		cells.add(row);
		calcSzomszedok(rc);
		spawnCoords = rc.getCoords();
	}

	/**
	 * meghatározza a paraméterül kapott cella szomszédait
	 * 
	 * @param c
	 */
	private void calcSzomszedok(Cell c) {
		Logging.log("Controller.calcSzomszedok() hívás, paraméter: "
				+ c.toString());
		int[] coords = c.getCoords();
		c.setSzomszed(1, cells.get(0).get(1));
	}

	/**
	 * Az eseményeket vezérlő függvény
	 * 
	 */
	public void run() {
		Logging.log("Controller.run() hívás");
		Elf e = new Elf(10, 1);
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		sp.enter(e);
		enemies.add(e);

		for (Enemy en : enemies) {
			try {
				en.leptet();
			} catch (EnemyDeadException e1) {
				saruman.addManna(2);
				e1.printStackTrace();
				enemies.remove(en);
			} catch (EnemyCannotStepException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log("Enemy nyert: " + e.toString());
		}
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log("Enemy nyert: " + d.toString());
		}
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log("Enemy nyert: " + h.toString());
		}
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log("Enemy nyert: " + h.toString());
		}
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());

	}
}
