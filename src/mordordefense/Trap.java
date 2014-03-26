package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

public class Trap implements RouteCellListener {

	/**
	 * A csapda alaperőssége. Alapból ennyivel lassítja a rálépő ellenséget.
	 */
	protected int strength;

	protected static int baseCost;

	/**
	 * A csapdán lévő varázskövek.
	 */
	protected List<MagicStone> stones = new ArrayList<MagicStone>();

	/**
	 * Operation
	 * 
	 * @param s
	 */
	public void addStone(MagicStone s) {
		Logging.log(">> Trap.addStone() hívás, paraméter: " + s.toString());
		stones.add(s);
	}

	/**
	 * A csapdaelhelyezés alapárát visszaadó függvény.
	 * 
	 * @return int Az építés alapköltsége.
	 */
	public static int getBaseCost() {
		Logging.log(">> Trap.getBaseCost() hívás.");
		Logging.log("<< " + baseCost);
		return baseCost;
	}

	/**
	 * Az építés alapárát beállító függvény.
	 * 
	 * @param c
	 *            Az alapár.
	 */
	public static void setBaseCost(int c) {
		Logging.log(">> Trap.setBaseCost() hívás, paraméter: " + c);
		baseCost = c;
	}

	@Override
	public String toString() {
		return "Trap, strength: " + strength;
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(">> Trap.onEnter() hívás, paraméterek: "
				+ sender.toString() + e.toString());
		int sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMultiplier(e);
		}
		e.lassit(sebzes);
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Trap.onEnter() hívás, paraméterek: "
				+ sender.toString() + d.toString());
		int sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMultiplier(d);
		}
		d.lassit(sebzes);
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Trap.onEnter() hívás, paraméterek: "
				+ sender.toString() + h.toString());
		int sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMulitplier(h);
		}
		h.lassit(sebzes);
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Trap.onEnter() hívás, paraméterek: "
				+ sender.toString() + h.toString());
		int sebzes = strength;
		for (MagicStone s : stones) {
			sebzes *= s.getMultiplier(h);
		}
		h.lassit(sebzes);
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
