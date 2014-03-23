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
	 * játék végét nyilvántartó változó
	 */
	private boolean gameEnded = false;

	
	/**
	 * Controller konstruktor
	 * @param maxenemy
	 */
	public Controller(int maxenemy){
		Logging.log(">> Controller konstruktor hívás, paraméter: maxenemy: "+maxenemy);
		maxEnemyNum=maxenemy;
	}
	/**
	 * Inicializáló függvény
	 * 
	 */	
	public void init(String file) {
		Logging.log(">> Controller.init() hívás");
		Logging.log("<< void");
	}

	/**
	 * Az eseményeket vezérlő függvény
	 * 
	 */
	public void run() {
		Logging.log(">> Controller.run() hívás");

		while(!gameEnded){
			
		}
		Logging.log("<< void");

	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		Logging.log("enemy nyert");
		Logging.log("<< void");
		gameEnded = true;
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		Logging.log("<< void");
		gameEnded = true;
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		Logging.log("<< void");
		gameEnded = true;
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Controller.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		Logging.log("<< void");
		gameEnded = true;
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		Logging.log("<< void");
	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		Logging.log("<< void");
	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		Logging.log("<< void");
	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(">> Controller.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		Logging.log("<< void");
	}
}
