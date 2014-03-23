package mordordefense;

import java.util.List;

public class Controller implements RouteCellListener {
	/** Attributes */
	public int[][] mordorCoords;
	public int[][] spawnCoords;
	private int maxEnemyNum;
	// ## Implementation preserve start class attributes.
	// ## Implementation preserve end class attributes.
	/** Associations */
	private List<Trap> traps;
	private List<Tower> towers;
	private Saruman saruman;
	private List<Enemy> enemies;
	private List<Cell> cells;

	/**
	 * Operation
	 * 
	 */
	public void init() {

	}

	/**
	 * Operation
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
