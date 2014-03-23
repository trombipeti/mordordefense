package mordordefense;

import java.util.List;

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

	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {

	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {

	}

	@Override
	public void onEnter(RouteCell sender, Human h) {

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
