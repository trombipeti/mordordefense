package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

public class Trap implements RouteCellListener {

	/**
	 * A csapda alaperőssége. Alapból ennyivel lassítja a rálépő ellenséget.
	 */
	public static int globalStrength;
	protected float strength;

	/**
	 * A csapda tartózkodási helye.
	 */
	protected RouteCell parentCell;

	protected static float baseCost;

	/**
	 * A csapdán lévő varázskövek.
	 */
	protected List<MagicStone> stones = new ArrayList<MagicStone>();

	public Trap() {
		Logging.log(2, ">> Trap default konstruktor hívás");
		this.strength = globalStrength;
		Logging.log(4, "<< Trap default konstruktor");
	}

	public Trap(float strength) {
		Logging.log(2, ">> Trap konstruktor hívás, parameter: " + strength);
		this.strength = strength;
		Logging.log(4, "<< Trap konstruktor");
	}

	/**
	 * Varázskövet helyez el a csapdára.
	 * 
	 * @param s
	 *            Az elhelyezendő {@link MagicStone}.
	 */
	public void addStone(MagicStone s) {
		Logging.log(3, ">> Trap.addStone() hívás, paraméter: " + s.toString());
		stones.add(s);
		Logging.log(1, s.toString());
		Logging.log(4, "<< Trap.addStone");
	}

	/**
	 * @return A csapdán lévő varázskövek.
	 */
	public List<MagicStone> getStones() {
		return stones;
	}

	/**
	 * A csapdaelhelyezés alapárát visszaadó függvény.
	 * 
	 * @return int Az építés alapköltsége.
	 */
	public static float getBaseCost() {
		Logging.log(4, ">> Trap.getBaseCost() hívás.");
		Logging.log(4, "<< " + baseCost);
		return baseCost;
	}

	/**
	 * Az építés alapárát beállító függvény.
	 * 
	 * @param c
	 *            Az alapár.
	 */
	public static void setBaseCost(float c) {
		Logging.log(4, ">> Trap.setBaseCost() hívás, paraméter: " + c);
		baseCost = c;
		Logging.log(4, "<< Trap.setBaseCost");
	}

	/**
	 * Megmondja, mennyi manna szükséges a csapda megépítéséhez. Ennek értéke:
	 * {@link Trap#baseCost} + {@link Trap#strength}
	 * 
	 * @return A csapda építésének ára
	 */
	public float getCost() {
		Logging.log(2, ">> Trap.getCost() hívás");
		float ret = baseCost + strength;
		Logging.log(2, "<< Trap.getCost() return: " + ret);
		return ret;
	}

	/**
	 * A Trap-et tároló RouteCell-t beállító függvény
	 * 
	 * @param c
	 *            A tároló RouteCell
	 */

	public void setParentCell(RouteCell c) {
		if (c != null)
			parentCell = c; // nincs szükség logolásra, pusztán kozmetikai
	}

	@Override
	public String toString() {
		return "Trap, strength: " + strength;
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(2,
				">> Trap.onEnter() hívás, paraméterek: " + sender.toString()
						+ e.toString());
		float sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMultiplier(e);
		}
		e.lassit(sebzes);
		Logging.log(4, "<< Trap.onEnter");
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(2,
				">> Trap.onEnter() hívás, paraméterek: " + sender.toString()
						+ d.toString());
		float sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMultiplier(d);
		}
		d.lassit(sebzes);
		Logging.log(4, "<< Trap.onEnter");
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(2,
				">> Trap.onEnter() hívás, paraméterek: " + sender.toString()
						+ h.toString());
		float sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMulitplier(h);
		}
		h.lassit(sebzes);
		Logging.log(4, "<< Trap.onEnter");
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(2,
				">> Trap.onEnter() hívás, paraméterek: " + sender.toString()
						+ h.toString());
		float sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMultiplier(h);
		}
		h.lassit(sebzes);
		Logging.log(4, "<< Trap.onEnter");
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
