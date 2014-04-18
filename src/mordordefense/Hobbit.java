package mordordefense;

import java.util.ArrayList;
import java.util.Random;

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
		Logging.log(2, ">> Hobbit default konstruktor hívás");
		Logging.log(4, "<< Hobbit default konstruktor");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Hobbit(float defMaxLP, float defSpeed) {
		super(defMaxLP, defSpeed);
		Logging.log(2, ">> Hobbit konstruktor hívás, maxLP: " + defMaxLP
				+ " speed: " + defSpeed);
		Logging.log(4, "<< Hobbit konstruktor");

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
		Logging.log(2, ">> Hobbit.leptet() hívás");
		if (lifePoint <= 0) {
			throw new EnemyDeadException();
		}
		long _time = System.currentTimeMillis();
		if (_time - timeOfLastStep < speed) {
			Logging.log(2, "<< Hobbit.leptet(), nem tud meg lepni.");
			return;
		}
		// Eltároljuk, hogy melyik szomszédra tud egyáltalán lépni.
		// Kis szépséghiba, hogy ha több olyan cellatípus is van, akire nem tud
		// lépni,
		// akkor azokra külön-külön le kell csekkolni a getType()-ot.
		// Szerencsére
		// jelenleg ez a helyzet nem áll fenn.
		ArrayList<RouteCell> possibleNext = new ArrayList<RouteCell>();
		for (Cell rc : routeCell.getSzomszedok().values()) {
			if (rc != null && !rc.getType().equalsIgnoreCase("FieldCell")
					&& rc.getID() > routeCell.getID()) {
				possibleNext.add((RouteCell) rc);
			}
		}
		// Ha van olyan cella, ahova tud lépni, random sorsolunk egyet
		if (possibleNext.size() > 0) {
			// So random
			Random randgen = new Random(System.currentTimeMillis());
			int next = randgen.nextInt(possibleNext.size());
			RouteCell nextCell = possibleNext.get(next);
			Logging.log(3, "\t Erre a cellára lépek: " + nextCell.toString());
			routeCell.leave(this);
			nextCell.enter(this);
			resetSpeed();
			stepNumber++;
			routeCell = nextCell;
		} else {
			throw new EnemyCannotStepException();
		}
		Logging.log(2, "<< Hobbit.leptet()");
	}

	@Override
	public void sebez(Bullet b) {
		Logging.log(2, ">> Hobbit.sebez() hívás, paraméter: " + b.toString());
		if (b.isSlicing()) {
			slice();
		} else {
			lifePoint -= b.getDamage(this);
			Logging.log(3, "\t új életerő: " + lifePoint);
		}
		Logging.log(2, "<< Hobbit.sebez()");
	}

	@Override
	protected void slice() {
		Logging.log(2, ">> Hobbit.slice() hívás");
		Hobbit newEnemy = new Hobbit(lifePoint / 2, speed);
		lifePoint = (int) (Math.floor(lifePoint + 0.5));
		newEnemy.setRouteCell(routeCell);
		for (EnemyListener l : listeners) {
			l.onSlice(newEnemy);
		}
		Logging.log(2, "<< Hobbit.slice()");
	}
}