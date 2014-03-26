package mordordefense;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Tünde típusú ellenség.
 * 
 */
public class Elf extends Enemy {

	/**
	 * Alap konstruktor.
	 */
	public Elf() {
		Logging.log(">> Elf default konstruktor hívás");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Elf(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Elf konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);

	}

	@Override
	public String getType() {
		return "Elf";
	}

	@Override
	public void leptet() throws EnemyCannotStepException, EnemyDeadException {
		Logging.log(">> Elf.leptet() hívás");
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
