package mordordefense;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Törp típusú ellenség.
 * 
 */
public class Dwarf extends Enemy {

	/**
	 * Alap konstruktor.
	 */
	public Dwarf() {
		Logging.log(">> Dwarf default konstruktor hívás");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Dwarf(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Dwarf konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);

	}

	@Override
	public String getType() {
		return "Dwarf";
	}

	@Override
	public void leptet() throws EnemyDeadException, EnemyCannotStepException {
		Logging.log(">> Dwarf.leptet() hívás");
		if (lifePoint <= 0) {
			throw new EnemyDeadException();
		}
		RouteCell next = null;
		for (Cell rc : routeCell.getSzomszedok()) {
			if (rc.getType().equalsIgnoreCase("RouteCell")
					&& rc.getID() > stepNumber) {
				next = (RouteCell) rc;
				Logging.log("\t Erre a cellara lépek: " + next.toString());
				break;
			}
		}
		if (next != null) {
			routeCell.leave(this);
			next.enter(this);
			routeCell = next;
		} else {
			throw new EnemyCannotStepException();
		}
	}
}
