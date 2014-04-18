package mordordefense;

import java.util.ArrayList;
import java.util.Random;

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
		Logging.log(2, ">> Human default konstruktor hívás");
		Logging.log(4, "<< Human default konstruktor");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Human(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(2, ">> Human konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);
		Logging.log(4, "<< Human konstruktor");
	}

	@Override
	public String getType() {
		return "Human";
	}

	@Override
	public void leptet() throws EnemyDeadException, EnemyCannotStepException {
		Logging.log(2, ">> Human.leptet() hívás");
		if (lifePoint <= 0) {
			throw new EnemyDeadException();
		}
		long _time = System.currentTimeMillis();
		if (_time - timeOfLastStep < speed) {
			Logging.log(2, "<< Human.leptet(), nem tud meg lepni");
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
		Logging.log(2, "<< Human.leptet()");
	}

	@Override
	public void sebez(Bullet b) {
		Logging.log(2, ">> Human.sebez() hívás, paraméter: " + b.toString());
		if (b.isSlicing()) {
			slice();
		} else {
			lifePoint -= b.getDamage(this);
			Logging.log(3, "\t új életerő: " + lifePoint);
		}
		Logging.log(2, "<< Human.sebez()");
	}

	@Override
	protected void slice() {
		Logging.log(2, ">> Human.slice() hívás");
		Human newEnemy = new Human(lifePoint / 2, speed);
		lifePoint = (int) (Math.floor(lifePoint + 0.5));
		newEnemy.setRouteCell(routeCell);
		for (EnemyListener l : listeners) {
			l.onSlice(newEnemy);
		}
		Logging.log(2, "<< Human.slice()");
	}
}