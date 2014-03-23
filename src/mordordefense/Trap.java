package mordordefense;

import java.util.ArrayList;
import java.util.List;

import mordordefense.testing.Logging;

public class Trap implements RouteCellListener {
	/** Attributes */
	protected int strength;
	protected List<MagicStone> stones = new ArrayList<MagicStone> ();
        protected baseCost;
        protected baseDamage;
        protected baseSlow;
        
	/**
	 * Operation
	 * 
	 * @param s
	 */
	public void addStone(MagicStone s) {
            Logging.log(">> Trap.addStone() meghivva ");
            stones.add(s);
            Logging.log(">> Trap.addStone() void visszateres");
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getBaseCost() {
            Logging.log(">> Trap.getBaseCost() meghivva");
            return 0;
            
	}

	@Override
	public String toString() {
		return "Trap, strength: " + strength;
	}

	@Override
	public void onEnter(RouteCell sender, Elf e) {
            Logging.log(">> Trap.onEnter() hivas, parameterek: "+sender.toString()+", "+e.toString());
            float slow = baseSlow;
            for (MagicStone m : stones) {
                slow *= m.elfMultiplier;
            }
            Enemy.lassit(slow);
            Logging.log(">> Trap.onEnter() void visszateres");
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
            Logging.log(">> Trap.onEnter() hivas, parameterek: "+sender.toString()+", "+d.toString());
            float slow = baseSlow;
            for (MagicStone m : stones) {
                slow *= m.dwarfMultiplier;
            }
            Enemy.lassit(slow);
            Logging.log(">> Trap.onEnter() void visszateres");

	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
            Logging.log(">> Trap.onEnter() hivas, parameterek: "+sender.toString()+", "+h.toString());
            float slow = baseSlow;
            for (MaicStone m : stones) {
                slow *= m.hobbitMultiplier;
            }
            Enemy.lassit(slow);
            Logging.log(">> Trap.onEnter() void visszateres");

	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
            Logging.log(">> Trap.onEnter() hivas, parameterek: "+sender.toString()+", "+h.toString());
            float slow = baseSlow;
            for (MaicStone m : stones) {
                slow *= m.humanMultiplier;
            }
            Enemy.lassit(slow);
            Logging.log(">> Trap.onEnter() void visszateres");

	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		Logging.log(">> Trap.onLeave() hivas, parameterek: "+sender.toString()+", "+e.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		Logging.log(">> Trap.onLeave() hivas, parameterek: "+sender.toString()+", "+d.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		Logging.log(">> Trap.onLeave() hivas, parameterek: "+sender.toString()+", "+h.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		Logging.log(">> Trap.onLeave() hivas, parameterek: "+sender.toString()+", "+h.toString());
		
	}
}
