package mordordefense;

import java.io.IOException;
import java.util.List;

import mordordefense.testing.Logging;

public class RouteCell extends Cell {
	protected Trap trap;
	protected List<RouteCellListener> listeners;
	protected List<Bullet> bullets;
	protected List<Enemy> enemies;
	
	
	public RouteCell() {
		try {
			Logging.log(">> RouteCell konstruktor hívás");
		} catch (IOException e) {
			e.printStackTrace();
		}
		trap = null;
	}
	
	public RouteCell(Trap t) {
		try {
			Logging.log(">> RouteCell konstruktor hívás, paraméter: "+t.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		trap = t;
	}
	
	/**
	 * Operation
	 * 
	 * @param t
	 */
	public void addTrap(Trap t) {
		try {
			Logging.log(">> RouteCell.addTrap() hívás, paraméter: "+t.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(trap == null) {
			trap = t;
		}
	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void enter(Elf e) {
		for(RouteCellListener l: listeners) {
			l.onEnter(this, e);
		}
	}
	
	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void enter(Human h) {
		for(RouteCellListener l: listeners) {
			l.onEnter(this, h);
		}
	}
	
	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void enter(Hobbit h) {
		for(RouteCellListener l: listeners) {
			l.onEnter(this, h);
		}
	}
	
	/**
	 * Operation
	 * 
	 * @param d
	 */
	public void enter(Dwarf d) {
		for(RouteCellListener l: listeners) {
			l.onEnter(this, d);
		}
	}

	/**
	 * Operation
	 * 
	 * @param b
	 */
	public void addBullet(Bullet b) {
		bullets.add(b);
	}

	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void leave(Elf e) {
		for(RouteCellListener l : listeners) {
			l.onLeave(this, e);
		}
	}
	
	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void leave(Human h) {
		for(RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
	}
	
	/**
	 * Operation
	 * 
	 * @param h
	 */
	public void leave(Hobbit h) {
		for(RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
	}
	
	/**
	 * Operation
	 * 
	 * @param e
	 */
	public void leave(Dwarf d) {
		for(RouteCellListener l : listeners) {
			l.onLeave(this, d);
		}
	}

	/**
	 * Operation
	 * 
	 * @param l
	 */
	public void addRouteCellListener(RouteCellListener l) {
		listeners.add(l);
	}

	@Override
	public String getType() {
		return "RouteCell";
	}
}
