package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

public class Trap implements RouteCellListener {
	/** Attributes */
	protected int strength;
	protected List<MagicStone> stones = new ArrayList<MagicStone>();
	

	/**
	 * Operation
	 * 
	 * @param s
	 */
	public void addStone(MagicStone s) {
		Logging.log(">> Trap, addStone ");
		stones.add(s);
		Logging.log("<< void");
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getBaseCost() {
		Logging.log(">> Trap, getBaseCost meghivva");
		return 0;

	}

	@Override
	public String toString() {
		return "Trap, strength: " + strength;
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		// TODO Auto-generated method stub

	}
}