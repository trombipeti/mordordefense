package mordordefense;

import java.util.List;

public class Trap implements RouteCellListener {
	/** Attributes */
	private int strength;

	/** Associations */
	private List<MagicStone> stones;

	/**
	 * Operation
	 * 
	 * @param s
	 */
	public void addStone(MagicStone s) {
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getBaseCost() {
		return 0;
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
}
