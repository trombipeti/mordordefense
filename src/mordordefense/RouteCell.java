package mordordefense;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

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
	// protected List<Enemy> enemies = new ArrayList<Enemy>();

	protected CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();

	public List<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * A RouteCell-t csapdával létrehozó konstruktor.
	 * 
	 * @see Cell#Cell(int, int)
	 * 
	 * @param t
	 *            A cellán lévő csapda.
	 */
	public RouteCell(int x, int y, Trap t) {
		super(x, y);
		Logging.log(2,
				">> RouteCell konstruktor hívás, paraméter: " + t.toString());
		trap = t;
		Logging.log(4, ">> RouteCell konstruktor");
	}

	/**
	 * @see Cell#Cell(int, int)
	 */
	public RouteCell(int x, int y, int ID) {
		super(x, y, ID);
		trap = null;
	}

	/**
	 * Az útra csapdát elhelyező metódus.
	 * 
	 * @param t
	 *            Az elhelyezendő csapda.
	 */
	public boolean addTrap(Trap t) {
		Logging.log(2, ">> RouteCell.addTrap() hívás, paraméter: "
				+ (t == null ? "null" : t.toString()));
		if (trap == null && t != null) {
			trap = t;
			trap.setParentCell(this);
			listeners.add(t);
			Logging.log(2, "<< RouteCell.addTrap() return: boolean - true");
			return true;
		} else {
			Logging.log(2, "<< RouteCell.addTrap() return: boolean - false");
			return false;
		}
	}

	public boolean hasTrap() {
		if (trap != null) {
			return true;
		}
		return false;
	}

	public Trap getTrap() {
		return trap;
	}

	/**
	 * Elf beléptetése a cellára.
	 * 
	 * @param e
	 *            A belépő {@link Elf}
	 */
	public void enter(Elf e) {
		Logging.log(2, ">> RouteCell.enter() hívás, paraméter: " + e.toString());
		e.setRouteCell(this);
		enemies.add(e);
		for (RouteCellListener l : listeners) {
			l.onEnter(this, e);
		}
		Logging.log(4, "<< RouteCell.enter");
	}

	/**
	 * Ember beléptetése a cellára.
	 * 
	 * @param h
	 *            A belépő {@link Human}
	 */
	public void enter(Human h) {
		Logging.log(2, ">> RouteCell.enter() hívás, paraméter: " + h.toString());
		h.setRouteCell(this);
		enemies.add(h);
		for (RouteCellListener l : listeners) {
			l.onEnter(this, h);
		}
		Logging.log(4, "<< RouteCell.enter");
	}

	/**
	 * Hobbit beléptetése a cellára.
	 * 
	 * @param h
	 *            A belépő {@link Hobbit}
	 */
	public void enter(Hobbit h) {
		Logging.log(2, ">> RouteCell.enter() hívás, paraméter: " + h.toString());
		h.setRouteCell(this);
		enemies.add(h);
		for (RouteCellListener l : listeners) {
			l.onEnter(this, h);
		}
		Logging.log(4, "<< RouteCell.enter");
	}

	/**
	 * Törp beléptetése a cellára.
	 * 
	 * @param d
	 *            A belépő {@link Dwarf}
	 */
	public void enter(Dwarf d) {
		Logging.log(2, ">> RouteCell.enter() hívás, paraméter: " + d.toString());
		d.setRouteCell(this);
		enemies.add(d);
		for (RouteCellListener l : listeners) {
			l.onEnter(this, d);
		}
		Logging.log(4, "<< RouteCell.enter");
	}

	/**
	 * Lövedék kilövése a cellára
	 * 
	 * @param b
	 *            A lövedék.
	 */
	public void addBullet(Bullet b) {
		Logging.log(2,
				">> RouteCell.addBullet() hívás, paraméter: " + b.toString());
		bullets.add(b);
		ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
		for (ListIterator<Enemy> iter = enemies.listIterator(); iter.hasNext();) {
			// for (Enemy e : enemies){
			for (Bullet b1 : bullets) {
				Enemy e = iter.next();
				e.sebez(b1);
				if (e.isDead()) {
					toRemove.add(e);
				}
			}
		}
		enemies.removeAll(toRemove);
		bullets.remove(b);
		Logging.log(2, "<< RouteCell.addBullet()");
	}

	/**
	 * Elf kiléptetése a celláról.
	 * 
	 * @param e
	 *            Egy {@link Elf}.
	 */
	public void leave(Elf e) {
		Logging.log(2, ">> RouteCell.leave() hívás, paraméter: " + e.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, e);
		}
		if (!e.isDead())
			enemies.remove(e);
		Logging.log(4, "<< RouteCell.leave");
	}

	/**
	 * Human kiléptetése a celláról.
	 * 
	 * @param h
	 *            Egy {@link Human}.
	 */
	public void leave(Human h) {
		Logging.log(2, ">> RouteCell.leave() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
		if (!h.isDead())
			enemies.remove(h);
		Logging.log(4, "<< RouteCell.leave");
	}

	/**
	 * Hobbit kiléptetése a celláról.
	 * 
	 * @param h
	 *            Egy {@link Hobbit}.
	 */
	public void leave(Hobbit h) {
		Logging.log(2, ">> RouteCell.leave() hívás, paraméter: " + h.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, h);
		}
		if (!h.isDead())
			enemies.remove(h);
		Logging.log(4, "<< RouteCell.leave");
	}

	/**
	 * Törp kiléptetése a celláról.
	 * 
	 * @param d
	 *            Egy {@link Dwarf}.
	 */
	public void leave(Dwarf d) {
		Logging.log(2, ">> RouteCell.leave() hívás, paraméter: " + d.toString());
		for (RouteCellListener l : listeners) {
			l.onLeave(this, d);
		}
		if (!d.isDead())
			enemies.remove(d);
		Logging.log(4, "<< RouteCell.leave");
	}

	/**
	 * @return A cellán tarózkodó ellenségek száma.
	 */
	public int getNumEnemies() {
		Logging.log(3, ">> RouteCell.getNumEnemies() hívás");
		Logging.log(3, "<< " + enemies.size());
		return enemies.size();
	}

	/**
	 * Listener hozzáadása.
	 * 
	 * @param l
	 *            A hozzáadandó {@link RouteCellListener}
	 */
	public void addRouteCellListener(RouteCellListener l) {
		Logging.log(3, ">> RouteCell.addRouteCellListener() hívás, paraméter: "
				+ l.toString());
		listeners.add(l);
		Logging.log(4, "<< RouteCell.addRouteCellListener");
	}

	/**
	 * Listener leiratkozása
	 * 
	 * @param l
	 *            A leiratkozni vágyó {@link RouteCellListener}
	 */
	public void removeRouteCellListener(RouteCellListener l) {
		Logging.log(
				3,
				">> RouteCell.removeRouteCellListener() hívás, paraméter: "
						+ l.toString());
		listeners.remove(l);
		Logging.log(4, "<< RouteCell.removeRouteCellListener");
	}

	@Override
	public String getType() {
		return "RouteCell";
	}
}
