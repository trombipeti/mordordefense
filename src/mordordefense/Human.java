package mordordefense;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Ember típusú ellenség.
 * 
 */
public class Human extends Enemy {
	/**
	 * Alap konstruktor
	 */
	public Human() {
		Logging.log(">> Human default konstruktor hívás");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Human(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Human konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);

	}

	@Override
	public String getType() {
		return "Human";
	}

	@Override
	public void leptet() throws EnemyDeadException, EnemyCannotStepException {
		Logging.log(">> Human.leptet() hívás");
		if (lifePoint <= 0) {
			throw new EnemyDeadException();
		}
		RouteCell next = null;
		for (Cell rc : routeCell.getSzomszedok()) {
			if (!rc.getType().equalsIgnoreCase("FieldCell")
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
			stepNumber++;
			routeCell = next;
		} else {
			throw new EnemyCannotStepException();
		}
	}
}