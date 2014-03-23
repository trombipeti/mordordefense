package mordordefense;

<<<<<<< HEAD
import mordordefense.testing.Logging;

=======
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Tünde típusú ellenség.
 * 
 */
>>>>>>> origin/master
public class Elf extends Enemy {

	/**
	 * Alap konstruktor.
	 */
	public Elf() {
		Logging.log(">> Elf default konstruktor hívás");
	}
<<<<<<< HEAD
	
	public Elf(int parMaxLifePoint, int parSpeed ){
		super(parMaxLifePoint,parSpeed);
		Logging.log(">> Elf konstruktor hívás, maxLP: "+parMaxLifePoint+" speed: "+parSpeed);
		
=======

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Elf(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Elf konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);

>>>>>>> origin/master
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
