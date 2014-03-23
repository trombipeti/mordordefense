package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

/**
 * Az utat megtestesítő Cell leszármazott osztály. Csak ezen lépkedhetnek az
 * ellenségek, erre lehet csapdát elhelyezni.
 * 
 */
public class RouteCell extends Cell {

	/**
	 * A cellán lévő csapda. Ha nincs rajta, akkor null;
	 */
	protected Trap trap;

	/**
	 * A cellán áthaladó forgalmat figyelő listenerek.
	 */
	protected List<RouteCellListener> listeners = new ArrayList<RouteCellListener>();

	/**
	 * A cellán lévő ellenségekre ható lövedékek.
	 */
	protected List<Bullet> bullets = new ArrayList<Bullet>();

	/**
	 * A cellán tartózkodó ellenségek.
	 */
	protected List<Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * Alap konstruktor.
	 */
	public RouteCell() {
		Logging.log(">> RouteCell konstruktor hívás");
		trap = null;
	}

	/**
	 * A RouteCell-t csapdával létrehozó konstruktor.
	 * 
	 * @param t
	 *            A cellán lévő csapda.
	 */
	public RouteCell(Trap t) {
		Logging.log(">> RouteCell konstruktor hívás, paraméter: "
				+ t.toString());
		trap = t;
	}

	/**
	 * @see Cell#Cell(int, int)
	 */
	public RouteCell(int x, int y) {
		super(x, y);
		trap = null;
	}

	/**
	 * Az útra csapdát elhelyező metódus.
	 * 
	 * @param t
	 *            Az elhelyezendő csapda.
	 */
	public void addTrap(Trap t) {
		Logging.log(">> RouteCell.addTrap() hívás, paraméter: " + t.toString());
		if (trap == null) {
			trap = t;
		}
	}

	/**
	 * Elf beléptetése a cellára.
	 * 
	 * @param e
	 *            A belépő {@link Elf}
	 */
	public void enter(Elf e) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + e.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, e);
		}
		e.setRouteCell(this);
	}

	/**
	 * Ember beléptetése a cellára.
	 * 
	 * @param h
	 *            A belépő {@link Human}
	 */
	public void enter(Human h) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, h);
		}
		h.setRouteCell(this);
	}

	/**
	 * Hobbit beléptetése a cellára.
	 * 
	 * @param h
	 *            A belépő {@link Hobbit}
	 */
	public void enter(Hobbit h) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, h);
		}
		h.setRouteCell(this);
	}

	/**
	 * Törp beléptetése a cellára.
	 * 
	 * @param d
	 *            A belépő {@link Dwarf}
	 */
	public void enter(Dwarf d) {
		Logging.log(">> RouteCell.enter() hívás, paraméter: " + d.toString());
		for (RouteCellListener l : listeners) {
			l.onEnter(this, d);
		}
		d.setRouteCell(this);
	}

	/**
	 * Lövedék kilövése a cellára
	 * 
	 * @param b
	 *            A lövedék.
	 */
	public void addBullet(Bullet b) {
		Logging.log("RouteCell.addBullet() hívás, paraméter: " + b.toString());
		bullets.add(b);
	}

	/**
	 * Elf kiléptetése a celláról.
	 * 
	 * @param e
	 *            Egy {@link Elf}.
	 */
	public void leave(Elf e) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + e.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, e);
		}
	}

	/**
	 * Human kiléptetése a celláról.
	 * 
	 * @param h
	 *            Egy {@link Human}.
	 */
	public void leave(Human h) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
	}

	/**
	 * Hobbit kiléptetése a celláról.
	 * 
	 * @param h
	 *            Egy {@link Hobbit}.
	 */
	public void leave(Hobbit h) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
	}

	/**
	 * Törp kiléptetése a celláról.
	 * 
	 * @param Egy
	 *            {@link Dwarf}.
	 */
	public void leave(Dwarf d) {
		Logging.log(">> RouteCell.leave() hívás, paraméter: " + d.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, d);
		}
	}

	/**
	 * @return A cellán tarózkodó ellenségek száma.
	 */
	public int getNumEnemies() {
		Logging.log(">> RouteCell.getNumEnemis() hívás");
		Logging.log("<< " + enemies.size());
		return enemies.size();
	}

	/**
	 * Listener hozzáadása.
	 * 
	 * @param l
	 *            A hozzáadandó {@link RouteCellListener}
	 */
	public void addRouteCellListener(RouteCellListener l) {
		Logging.log(">> RouteCell.addRouteCellListener() hívás" + l.toString());
		listeners.add(l);
	}

	@Override
	public String getType() {
		return "RouteCell";
	}
}
