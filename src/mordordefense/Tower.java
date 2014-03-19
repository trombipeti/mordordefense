package mordordefense;

import java.util.List;

public class Tower implements RouteCellListener

{
	/** Attributes */
	protected int freq;
	protected int radius;
	protected int baseDamage;
	protected int timeSinceLastShoot;

	/** Associations */
	protected List<RouteCell> closestCellsWithEnemy;
	protected List<MagicStone> stones;

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
	 * @return RouteCell[]
	 */
	public List<RouteCell> getClosestCellsWithEnemy() {
		return null;
	}

	/**
	 * Operation
	 * 
	 * @param f
	 */
	public void setParentCell(FieldCell f) {

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
	public String toString() {
		return "Tower, freq: " + freq + ", radius: " + radius
				+ ", baseDamage: " + baseDamage
				+ ", utolso loves ota eltelt ido: " + timeSinceLastShoot;
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
