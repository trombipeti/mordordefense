package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

public class RouteCell extends Cell {
	protected Trap trap;
	protected List<RouteCellListener> listeners = new ArrayList<RouteCellListener>();
	protected List<Bullet> bullets = new ArrayList<Bullet>();
	protected List<Enemy> enemies = new ArrayList<Enemy>();

	public RouteCell() {
		Logging.log(">> RouteCell konstruktor hívás");
		trap = null;
	}

	public RouteCell(Trap t) {
		Logging.log(">> RouteCell konstruktor hívás, paraméter: "
				+ t.toString());
		trap = t;
	}

	/**
	 * Operation
	 * 
	 * @param t
	 */
	public void addTrap(Trap t) {
		Logging.log(">> RouteCell.addTrap() hívás, paraméter: " + t.toString());
		if (trap == null) {
			trap = t;
		}
	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void enter(Elf e) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + e.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, e);
		}
	}

	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void enter(Human h) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, h);
		}
	}

	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void enter(Hobbit h) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, h);
		}
	}

	/**
	 * Operation
	 * 
	 * @param d
	 */
	public void enter(Dwarf d) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + d.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, d);
		}
	}

	/**
	 * Operation
	 * 
	 * @param b
	 */
	public void addBullet(Bullet b) {
		Logging.log("RouteCell.addBullet() hívás, paraméter: "+b.toString());
		bullets.add(b);
	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void leave(Elf e) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + e.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, e);
		}
	}

	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void leave(Human h) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
	}

	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void leave(Hobbit h) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void leave(Dwarf d) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + d.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, d);
		}
	}

	/**
	 * Operation
	 * 
	 * @param l
	 */
	public void addRouteCellListener(RouteCellListener l) {
		Logging.log(">> RouteCell.addRouteCellListener() hívás"+l.toString());
		listeners.add(l);
	}

	@Override
	public String getType() {
		return "RouteCell";
	}
}
