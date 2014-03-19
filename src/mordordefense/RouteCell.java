package mordordefense;

import java.util.List;

public class RouteCell extends Cell {
	private Trap trap;
	private List<RouteCellListener> listeners;
	private List<Bullet> bullets;
	private List<Enemy> enemies;

	/**
	 * Operation
	 * 
	 * @param t
	 */
	public void addTrap(Trap t) {

	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void enter(Enemy e) {

	}

	/**
	 * Operation
	 * 
	 * @param b
	 */
	public void addBullet(Bullet b) {

	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void leave(Enemy e) {

	}

	/**
	 * Operation
	 * 
	 * @param l
	 */
	public void addRouteCellListener(RouteCellListener l) {

	}

	@Override
	public String getType() {
		return "RouteCell";
	}
}
