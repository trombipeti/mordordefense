package mordordefense;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	 * A legutolsó lövés óta eltelt idő. Ebből számítja ki, hogy lőhet-e.
	 */
	protected int timeSinceLastShoot;

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
	 * A toronyban lévő várzskövek.
	 */
	protected List<MagicStone> stones = new ArrayList<MagicStone>();

	/**
	 * Varázskövet elhelyező függvény.
	 * 
	 * @param s
	 *            A hozzáadandó {@link MagicStone}
	 */
	public void addStone(MagicStone s) {
		Logging.log(">> Tower.addStone() hívás, paraméter: " + s.toString());
		stones.add(s);
	}

	/**
	 * A hatósugarában lévő, ellenséget tartalmazó cellák rendezett halmazát
	 * visszaadó függvény.
	 * 
	 * @return Set<RouteCell>
	 */
	public Set<RouteCell> getClosestCellsWithEnemy() {
		Logging.log(">> Tower.getClosestCellsWithEnemy() hívás");
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
		parentCell = f;
	}

	/**
	 * A tornyok építésének alapárát lekérdező függvény.
	 * 
	 * @return int Az építés alapára.
	 */
	public static int getBaseCost() {
		Logging.log(">> Tower.getBaseCost() hívás");
		return baseCost;
	}

	@Override
	public String toString() {
		return "Tower, freq: " + freq + ", radius: " + radius
				+ ", baseDamage: " + baseDamage
				+ ", utolso loves ota eltelt ido: " + timeSinceLastShoot;
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		closestCellsWithEnemy.add(sender);

	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		closestCellsWithEnemy.add(sender);
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		closestCellsWithEnemy.add(sender);
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		closestCellsWithEnemy.add(sender);
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + e.toString());
		if(sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + d.toString());
		if(sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if(sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "
				+ sender.toString() + ", " + h.toString());
		if(sender.getNumEnemies() == 0) {
			closestCellsWithEnemy.remove(sender);
		}
	}
}
