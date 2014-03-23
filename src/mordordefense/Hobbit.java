package mordordefense;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Hobbit típusú ellenség.
 * 
 */
public class Hobbit extends Enemy

{
	/**
	 * Alap konstruktor
	 */
	public Hobbit() {
		Logging.log(">> Hobbit default konstruktor hívás");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Hobbit(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Hobbit konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);

	}

	@Override
	public String getType() {
		return "Hobbit";
	}

	/**
	 * @see mordordefense.Enemy#leptet()
	 */
	@Override
	public void leptet() throws EnemyDeadException, EnemyCannotStepException {
		Logging.log(">> Dwarf.leptet() hívás");
		if (lifePoint <= 0) {
			throw new EnemyDeadException();
		}
		RouteCell next = null;
		for (Cell rc : routeCell.getSzomszedok()) {
			if (! rc.getType().equalsIgnoreCase("FieldCell")
					&& rc.getID() > stepNumber) {
				next = (RouteCell) rc;
				Logging.log("\t Erre a cellara lépek: " + next.toString());
				break;
			}
		}
		if (next != null) {
			routeCell.leave(this);
			next.enter(this);
			resetSpeed();
			routeCell = next;
		} else {
			throw new EnemyCannotStepException();
		}
	}
}