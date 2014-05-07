package mordordefense;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import mordordefense.testing.Logging;

/**
 * Szarumány tornyait megtestesítő osztály.
 * 
 */
public class Tower implements RouteCellListener

{

	public static int globalDamage;

	public static int globalFreq;

	public static int globalRadius;

	public static boolean globalSlice = false;
	/**
	 * A torony tüzelési frekvenciája.
	 */
	protected float freq;

	/**
	 * A torony hatósugara. Az őt tartalmaző cellától maximum ekkora távolságra
	 * lévő utakra tud lőni. Ezekre a RouteCell-ekre fel is iratkozik
	 * listenerként.
	 */
	protected float radius;

	/**
	 * A torony hatósugara, akkor használjuk csak, amikor köd ereszkedik a
	 * toronyra, ebben tároljuk az eredeti hatósugarat.
	 */

	protected float foglessRadius;

	/**
	 * A torony alapsebzése.
	 */
	protected float baseDamage;

	/**
	 * A legutolsó lövés ideje. Ebből számítja ki, hogy lőhet-e.
	 */
	protected long timeOfLastShoot;

	/**
	 * A tornyot ellepi-e a köd.
	 */
	protected boolean hasFog;

	/**
	 * Mikor lepte el a tornyot a köd.
	 */
	protected long fogAddTime;

	/**
	 * Mennyi ideig lepi el a köd a tornyot.
	 */
	protected long fogTimeOut;

	/**
	 * A ködből hátralévő idő.
	 */
	protected long fogTimeRemaining;

	/**
	 * A tornyot tartalmazó {@link FieldCell}.
	 */
	protected FieldCell parentCell;

	/**
	 * A tornyok éptésének alapára.
	 */
	static protected float baseCost;

	/**
	 * A torony hatósugarában lévő, ellenséget tartalmazó {@link RouteCell}-ek,
	 * távolság szerint növekvő sorrendben.
	 */
	protected Set<RouteCell> closestCellsWithEnemy = new HashSet<RouteCell>();

	/**
	 * A hatósugárban lévő cellák.
	 */
	protected Set<Cell> cellsInRange = new HashSet<Cell>();

	/**
	 * A toronyban lévő várzskövek.
	 */
	protected List<MagicStone> stones = new ArrayList<MagicStone>();

	public Tower() {
		Logging.log(2, ">> Tower default konstruktor hívás");
		this.freq = globalFreq;
		this.radius = globalRadius;
		this.baseDamage = globalDamage;
		this.hasFog = false;
		timeOfLastShoot = 0;
		Logging.log(4, "<< Tower default konstruktor");
	}

	/**
	 * Tower konstruktor
	 * 
	 * @param freq
	 *            lövési frekvencia
	 * @param radius
	 *            hatósugár
	 * @param damage
	 *            alap sebzési érték
	 */
	public Tower(float freq, float radius, float damage) {
		Logging.log(2, ">> Tower konstruktor hívás, paraméterek: freq: " + freq
				+ ", radius: " + radius + ", damage: " + damage);
		this.freq = freq;
		this.radius = radius;
		this.baseDamage = damage;
		this.hasFog = false;
		foglessRadius = radius;
		timeOfLastShoot = 0;
		Logging.log(4, "<< Tower konstruktor");
	}

	/**
	 * Varázskövet elhelyező függvény. A torony tüzelési frekvenciáját és
	 * hatósugarát is a kellő mértékben növeli.
	 * 
	 * @param s
	 *            A hozzáadandó {@link MagicStone}
	 */
	public void addStone(MagicStone s) {
		Logging.log(2, ">> Tower.addStone() hívás, paraméter: " + s.toString());
		stones.add(s);
		Logging.log(1, s.toString());
		// Az int osztás rossz tulajdonságit kerüljük el a castolgatással
		freq = (int) (freq * s.getFreqMultiplier());
		radius = (int) (radius * s.getRadiusMultiplier());
		if (s.getRadiusMultiplier() != 1) {
			removeFromListeners();
			cellsInRange.clear();
			setUpNeighbors();
		}
		Logging.log(2, "<< Tower.addStone()");
	}

	/**
	 * @return A tornyon lévő varázskövek.
	 */
	public List<MagicStone> getStones() {
		return stones;
	}

	/**
	 * A hatósugarában lévő, ellenséget tartalmazó cellák rendezett halmazát
	 * visszaadó függvény.
	 * 
	 * @return Set<RouteCell>
	 */
	public Set<RouteCell> getClosestCellsWithEnemy() {
		Logging.log(4, ">> Tower.getClosestCellsWithEnemy() hívás");
		Logging.log(4, "<< Tower.getClosestCellsWithEnemy()");
		return closestCellsWithEnemy;
	}

	public boolean isHasFog() {
		return hasFog;
	}

	/**
	 * A torony helyzetét beállító függvény.
	 * 
	 * @param f
	 *            A tornyot tartalmazó {@link FieldCell}
	 */
	public void setParentCell(FieldCell f) {
		Logging.log(4,
				">> Tower.setParentCell() hívás, paraméter: " + f.toString());
		if (f != null) {
			parentCell = f;
			setUpNeighbors();
		}
		Logging.log(4, "<< Tower.setParentCell()");
	}

	/**
	 * @return A tornyot tartalmazó {@link Cell}
	 */
	public Cell getParentCell() {
		return parentCell;
	}

	/**
	 * A torony szomszédait beállító függvény. Beregisztrálja a tornyot a
	 * hatósugarában lévő utak eseményeire.
	 * 
	 */
	private void setUpNeighbors() {
		Logging.log(4, ">> Tower.setUpNeighbors() hívás");
		getNeighbors(parentCell);
		for (Cell c : cellsInRange) {
			if (!c.getType().equalsIgnoreCase("FieldCell")) {
				((RouteCell) c).addRouteCellListener(this);
			}
		}
		Logging.log(4, "<< Tower.setUpNeighbors()");
	}

	/**
	 * Leiratkoztatja a Tornyot a hatósugarában lévő RouteCell-ekről
	 * 
	 */

	private void removeFromListeners() {
		for (Cell c : cellsInRange) {
			if (!c.getType().equalsIgnoreCase("FieldCell")) {
				((RouteCell) c).removeRouteCellListener(this);
			}
		}
	}

	/**
	 * Feliratkoztatja a tornyot a paraméterként kapott cella szomszédaira,
	 * amennyiben hatósugaron belül vannak.
	 * 
	 * @param c
	 *            A cella
	 */
	private void getNeighbors(Cell c) {
		TreeMap<Integer, Cell> neighbors = c.getSzomszedok();
		for (Cell nc : neighbors.values()) {
			if (nc != null && isInRange(nc) && !cellsInRange.contains(nc)) {
				cellsInRange.add(nc);
				getNeighbors(nc);
			}
		}
	}

	/**
	 * A tornyok építésének alapárát lekérdező függvény.
	 * 
	 * @return float Az építés alapára.
	 */
	public static float getBaseCost() {
		Logging.log(4, ">> Tower.getBaseCost() hívás");
		Logging.log(4, "<< Tower.getBaseCost() return: " + baseCost);
		return baseCost;
	}

	/**
	 * Megmondja, mennyi manna szükséges a torony megépítéséhez. Ez az érték a
	 * következő: {@link Tower#baseCost} + {@link Tower#foglessRadius} +
	 * {@link Tower#freq} + {@link Tower#baseDamage}
	 * 
	 * @return A torony megépítésének ára
	 */
	public float getCost() {
		Logging.log(4, ">> Tower.getCost() hívás");
		float ret = baseCost;
		ret += foglessRadius + freq + baseDamage;
		Logging.log(4, "<< Tower.getCost() return: " + ret);
		return ret;
	}

	/**
	 * Megmondja hogy egy cella benne van e a torony hatósugarában
	 * 
	 * @param c
	 *            A megnézendő {@link Cell}
	 */
	private boolean isInRange(Cell c) {
		Logging.log(3, ">> Tower.isInRange() hívás, paraméter: " + c);
		boolean ret = false;
		double dst = Cell.Distance(parentCell, c);
		ret = (dst <= radius);
		Logging.log(3, "<< Tower.isInRange() return: " + ret);
		return ret;
	}

	/**
	 * Ködöt ereszt a toronyra, amelynek következtében erősen csökken a
	 * látómezeje.
	 * 
	 * @param timeOut
	 *            Mennyi idő múlva oszoljon fel a köd (milliszekundumban).
	 */
	public void addFog(long timeOut) {
		Logging.log(2, ">> Tower.addFog hívás, paraméter: " + timeOut);
		if (hasFog) {
			Logging.log(4, "<< Tower.addFog, van már rajta köd");
			return;
		}
		hasFog = true;
		foglessRadius = radius;
		radius = radius / 2;
		fogTimeOut = timeOut;
		fogAddTime = System.currentTimeMillis();
		fogTimeRemaining = timeOut;
		removeFromListeners();
		cellsInRange.clear();
		setUpNeighbors();
		Logging.log(4, "<< Tower.addFog");
	}

	/**
	 * Feloszlatja a tornyon lévő ködöt.
	 */
	public void removeFog() {
		Logging.log(2, ">> Tower.removeFog hívás");
		hasFog = false;
		radius = foglessRadius;
		fogAddTime = fogTimeOut = -1;
		removeFromListeners();
		setUpNeighbors();
		Logging.log(4, "<< Tower.removeFog");
	}

	/**
	 * Lövedéket lő a paraméterként kapott útra.
	 * 
	 * @param rc
	 *            A {@link RouteCell}, ahova a lövedéket ki kell lőni.
	 */
	private void fire(RouteCell rc) {
		Logging.log(2, ">> Tower.fire hívás, paraméter: " + rc.toString());
		long dt = (System.currentTimeMillis() - timeOfLastShoot);
		Logging.log(1, "Tower lőne, eltelt idő: " + dt + ", freq: " + freq);
		if (dt > 1000.0 / freq) {
			Logging.log(1, "Tower lőni fog");
			float dw, el, hu, ho;
			dw = el = hu = ho = baseDamage;
			for (MagicStone s : stones) {
				float dmgmult = s.getDamageMultiplier();
				dw *= s.getDwarfMultiplier() * dmgmult;
				el *= s.getElfMultiplier() * dmgmult;
				hu *= s.getHumanMultiplier() * dmgmult;
				ho *= s.getHobbitMultiplier() * dmgmult;
			}
			boolean slice;

			if (!globalSlice) {
				if (Controller.isRandom()) {
					slice = (new Random().nextInt(50) == 0);
				} else {
					slice = false;
				}
			} else {
				slice = globalSlice;
			}
			Bullet b = new Bullet(dw, el, hu, ho, slice);
			// Ha első fire hívás, akkor csak egyszer lövünk.
			// Ha nem, akkor annyiszor, ahányszor kell.
			int numShoot = (timeOfLastShoot == 0 ? 1
					: (int) (dt / (1000.0f / freq)));
			Logging.log(1, "A Tower ennyiszer lő: " + numShoot);
			for (int i = 0; i < numShoot; ++i) {
				rc.addBullet(b);
				Logging.log(1,
						b.toString() + " from: " + parentCell.getCoords()[0]
								+ " " + parentCell.getCoords()[1] + " to: "
								+ rc.getCoords()[0] + " " + rc.getCoords()[1]);
			}
			timeOfLastShoot = System.currentTimeMillis();
		}
		Logging.log(4, "<< Tower.fire");
	}

	/**
	 * Megpróbál lövedéket kilőni az összes közelben lévő útra.
	 */
	public void fireAll() {
		Logging.log(2, ">> Tower.fireAll");
		if (hasFog && System.currentTimeMillis() - fogAddTime > fogTimeOut) {
			removeFog();
		}
		for (RouteCell rc : closestCellsWithEnemy) {
			fire(rc);
		}
		Logging.log(4, "<< Tower.fireAll");
	}

	/**
	 * A tornyok építésének alapárát beállító függvény.
	 * 
	 * @param c
	 *            Az építés alapára.
	 */
	public static void setBaseCost(float c) {
		Logging.log(4, ">> Tower.setBaseCost() hívás, paraméter: " + c);
		baseCost = c;
		Logging.log(4, "<< Tower.setBaseCost");
	}

	@Override
	public String toString() {
		String ret = "Tower, radius: " + radius + ", baseDamage: " + baseDamage
				+ ", freq: " + freq + ", elozo loves ideje: "
				+ (timeOfLastShoot) + ", hasfog: " + hasFog;
		if (parentCell != null) {
			ret += ", parentCell: " + parentCell.getCoords()[0] + " "
					+ parentCell.getCoords()[1];
		}
		return ret;
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(2,
				">> Tower.onEnter() hívás, paraméterek: " + sender.toString()
						+ ", " + e.toString());
		closestCellsWithEnemy.add(sender);
		// fire(sender);
		Logging.log(4, "<< Tower.onEnter");

	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(2,
				">> Tower.onEnter() hívás, paraméterek: " + sender.toString()
						+ ", " + d.toString());
		closestCellsWithEnemy.add(sender);
		// fire(sender);
		Logging.log(4, "<< Tower.onEnter");
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(2,
				">> Tower.onEnter() hívás, paraméterek: " + sender.toString()
						+ ", " + h.toString());
		closestCellsWithEnemy.add(sender);
		// fire(sender);
		Logging.log(4, "<< Tower.onEnter");
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(2,
				">> Tower.onEnter() hívás, paraméterek: " + sender.toString()
						+ ", " + h.toString());
		closestCellsWithEnemy.add(sender);
		// fire(sender);
		Logging.log(4, "<< Tower.onEnter");
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(3,
				">> Tower.onLeave() hívás, paraméterek: " + sender.toString()
						+ ", " + e.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
		Logging.log(4, "<< Tower.onLeave");
	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(3,
				">> Tower.onLeave() hívás, paraméterek: " + sender.toString()
						+ ", " + d.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
		Logging.log(4, "<< Tower.onLeave");
	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(3,
				">> Tower.onLeave() hívás, paraméterek: " + sender.toString()
						+ ", " + h.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
		Logging.log(4, "<< Tower.onLeave");
	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(3,
				">> Tower.onLeave() hívás, paraméterek: " + sender.toString()
						+ ", " + h.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
		Logging.log(4, "<< Tower.onLeave");
	}
}