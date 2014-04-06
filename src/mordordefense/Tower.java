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

	/**
	 * A torony tüzelési frekvenciája.
	 */
	protected int freq;

	/**
	 * A torony hatósugara. Az őt tartalmaző cellától maximum ekkora távolságra
	 * lévő utakra tud lőni. Ezekre a RouteCell-ekre fel is iratkozik
	 * listenerként.
	 */
	protected int radius;

	/**
	 * A torony alapsebzése.
	 */
	protected int baseDamage;

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
	 * A tornyot tartalmazó {@link FieldCell}.
	 */
	protected FieldCell parentCell;

	/**
	 * A tornyok éptésének alapára.
	 */
	static protected int baseCost;

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
		Logging.log(">> Tower default konstruktor hívás");
		// TODO default értékek valahonnan
		this.freq = 1;
		this.radius = 1;
		this.baseDamage = 1;
		this.hasFog = false;
		Logging.log("<< Tower default konstruktor");
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
	public Tower(int freq, int radius, int damage) {
		Logging.log(">> Tower konstruktor hívás, paraméterek: freq: " + freq
				+ ", radius: " + radius + ", damage: " + damage);
		this.freq = freq;
		this.radius = radius;
		this.baseDamage = damage;
		this.hasFog = false;
		Logging.log("<< Tower konstruktor");
	}

	/**
	 * Varázskövet elhelyező függvény. A torony tüzelési frekvenciáját és
	 * hatósugarát is a kellő mértékben növeli.
	 * 
	 * @param s
	 *            A hozzáadandó {@link MagicStone}
	 */
	public void addStone(MagicStone s) {
		Logging.log(">> Tower.addStone() hívás, paraméter: " + s.toString());
		stones.add(s);
		// Az int osztás rossz tulajdonságit kerüljük el a castolgatással
		freq = (int) (freq * s.getFreqMultiplier());
		radius = (int) (radius * s.getRadiusMultiplier());
		Logging.log("<< Tower.addStone()");
	}

	/**
	 * A hatósugarában lévő, ellenséget tartalmazó cellák rendezett halmazát
	 * visszaadó függvény.
	 * 
	 * @return Set<RouteCell>
	 */
	public Set<RouteCell> getClosestCellsWithEnemy() {
		Logging.log(">> Tower.getClosestCellsWithEnemy() hívás");
		Logging.log("<< Tower.getClosestCellsWithEnemy()");
		return closestCellsWithEnemy;
	}

	/**
	 * A torony helyzetét beállító függvény.
	 * 
	 * @param f
	 *            A tornyot tartalmazó {@link FieldCell}
	 */
	public void setParentCell(FieldCell f) {
		Logging.log(">> Tower.setParentCell() hívás, paraméter: "
				+ f.toString());
		if (f != null) {
			parentCell = f;
			setUpNeighbors();
		}
		Logging.log("<< Tower.setParentCell()");
	}

	/**
	 * A torony szomszédait beállító függvény. Beregisztrálja a tornyot a
	 * hatósugarában lévő utak eseményeire.
	 * 
	 */
	private void setUpNeighbors() {
		Logging.log(">> Tower.setUpNeighbors() hívás");
		getNeighbors(parentCell);
		for (Cell c : cellsInRange) {
			if (!c.getType().equalsIgnoreCase("FieldCell")) {
				((RouteCell) c).addRouteCellListener(this);
			}
		}
		Logging.log("<< Tower.setUpNeighbors()");
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
	 * @return int Az építés alapára.
	 */
	public static int getBaseCost() {
		Logging.log(">> Tower.getBaseCost() hívás");
		Logging.log("<< Tower.getBaseCost() return: " + baseCost);
		return baseCost;
	}

	/**
	 * Megmondja hogy egy cella benne van e a torony hatósugarában
	 * 
	 * @param c
	 *            A megnézendő {@link Cell}
	 */
	private boolean isInRange(Cell c) {
		Logging.log(">> Tower.isInRange() hívás, paraméter: " + c);
		boolean ret = false;
		double dst = Cell.Distance(parentCell, c);
		ret = (dst <= radius);
		Logging.log("<< Tower.isInRange() return: " + ret);
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
		hasFog = true;
		fogTimeOut = timeOut;
		fogAddTime = System.currentTimeMillis();
	}

	/**
	 * Feloszlatja a tornyon lévő ködöt.
	 */
	public void removeFog() {
		hasFog = false;
		fogAddTime = fogTimeOut = -1;
	}

	/**
	 * Lövedéket lő a paraméterként kapott útra.
	 * 
	 * @param rc
	 *            A {@link RouteCell}, ahova a lövedéket ki kell lőni.
	 */
	private void fire(RouteCell rc) {
		int dw, el, hu, ho;
		dw = el = hu = ho = baseDamage;
		for (MagicStone s : stones) {
			float dmgmult = s.getDamageMultiplier();
			dw *= s.getDwarfMultiplier() * dmgmult;
			el *= s.getElfMultiplier() * dmgmult;
			hu *= s.getHumanMultiplier() * dmgmult;
			ho *= s.getHobbitMultiplier() * dmgmult;
		}
		boolean slice = (new Random().nextInt(10) % 10 == 0);
		Bullet b = new Bullet(dw, el, hu, ho, slice);
		rc.addBullet(b);
		timeOfLastShoot = System.currentTimeMillis();
		Logging.log(b.toString() + " from: " + parentCell.getCoords()[0]+" "+parentCell.getCoords()[1] + " to: "
				+ rc.getCoords()[0]+" "+rc.getCoords()[1]);
	}

	/**
	 * A tornyok építésének alapárát beállító függvény.
	 * 
	 * @param c
	 *            Az építés alapára.
	 */
	public static void setBaseCost(int c) {
		Logging.log(">> Tower.setBaseCost() hívás, paraméter: " + c);
		baseCost = c;
	}

	@Override
	public String toString() {
		return "Tower, radius: " + radius + ", baseDamage: " + baseDamage
				+ ", freq: " + freq + ", utolso loves ota ideje: "
				+ timeOfLastShoot + ", hasfog: " + hasFog + ", parentCell: "
				+ parentCell.getCoords()[0]+" "+parentCell.getCoords()[1];
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		closestCellsWithEnemy.add(sender);
		fire(sender);

	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		closestCellsWithEnemy.add(sender);
		fire(sender);
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		closestCellsWithEnemy.add(sender);
		fire(sender);
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		closestCellsWithEnemy.add(sender);
		fire(sender);
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if (sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}
}
