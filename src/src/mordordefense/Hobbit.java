package mordordefense;

<<<<<<< HEAD
import mordordefense.testing.Logging;

=======
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Hobbit típusú ellenség.
 * 
 */
>>>>>>> origin/master
public class Hobbit extends Enemy

{
	/**
	 * Alap konstruktor
	 */
	public Hobbit() {
		Logging.log(">> Hobbit default konstruktor hívás");
	}
<<<<<<< HEAD
	
	public Hobbit(int parMaxLifePoint, int parSpeed ){
		super(parMaxLifePoint,parSpeed);
		Logging.log(">> Hobbit konstruktor hívás, maxLP: "+parMaxLifePoint+" speed: "+parSpeed);
		
=======

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Hobbit(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Hobbit konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);

>>>>>>> origin/master
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