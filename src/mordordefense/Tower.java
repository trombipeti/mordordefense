package mordordefense;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mordordefense.testing.Logging;

public class Tower implements RouteCellListener

{
	/** Attributes */
	protected int freq;
	protected int radius;
	protected int baseDamage;
	protected int timeSinceLastShoot;
	
	protected FieldCell parentCell;
	protected int baseCost;

	/** Associations */
	protected Set<RouteCell> closestCellsWithEnemy = new HashSet<RouteCell>();
	protected List<MagicStone> stones = new ArrayList<MagicStone>();
	
	public Tower() {
		parentCell = null;
		timeSinceLastShoot = 0;
		baseDamage = 1;
		radius = 3;
		freq = 10;
	}
	
	public Tower(int _freq, int _radius, int _baseDamage) {
		parentCell = null;
		timeSinceLastShoot = 0;
		freq = _freq;
		radius = _radius;
		baseDamage = _baseDamage;
		
	}

	/**
	 * Operation
	 * 
	 * @param s
	 */
	public void addStone(MagicStone s) {
		Logging.log(">> Tower.addStone() hívás, paraméter: "+s.toString());
		stones.add(s);
	}

	/**
	 * Operation
	 * 
	 * @return RouteCell[]
	 */
	public Set<RouteCell> getClosestCellsWithEnemy() {
		Logging.log(">> Tower.getClosestCellsWithEnemy() hívás");
		return closestCellsWithEnemy;
	}

	/**
	 * Operation
	 * 
	 * @param f
	 */
	public void setParentCell(FieldCell f) {
		Logging.log(">> Tower.setParentCell() hívás, paraméter: "+f.toString());
		parentCell = f;
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getBaseCost() {
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
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "+sender.toString()+", "+e.toString());
		closestCellsWithEnemy.add(sender);

	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "+sender.toString()+", "+d.toString());
		closestCellsWithEnemy.add(sender);
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "+sender.toString()+", "+h.toString());
		closestCellsWithEnemy.add(sender);
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(">> Tower.onEnter() hívás, paraméterek: "+sender.toString()+", "+h.toString());
		closestCellsWithEnemy.add(sender);
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "+sender.toString()+", "+e.toString());
	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "+sender.toString()+", "+d.toString());
	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "+sender.toString()+", "+h.toString());
	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(">> Tower.onLeave() hívás, paraméterek: "+sender.toString()+", "+h.toString());
	}
}
