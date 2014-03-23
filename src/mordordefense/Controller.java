package mordordefense;

import java.util.List;

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
	private List<Trap> traps;

	/**
	 * A pályán lévő tornyok
	 */
	private List<Tower> towers;

	/**
	 * Szarumán
	 */
	private Saruman saruman;

	/**
	 * A pályán lévő ellenségek
	 */
	private List<Enemy> enemies;

	/**
	 * A pályán lévő cellák
	 */
	private List<Cell> cells;

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
	 * Inicializáló függvény
	 * 
	 */
	public void init() {

	}

	/**
	 * Az eseményeket vezérlő függvény
	 * 
	 */
	public void run() {

	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
		}
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
		}
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
		}
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
		}
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {

	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {

	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {

	}

	@Override
	public void onLeave(RouteCell sender, Human h) {

	}
}
