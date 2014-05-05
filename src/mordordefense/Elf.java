package mordordefense;

import java.util.ArrayList;
import java.util.Random;

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
		Logging.log(2, ">> Elf default konstruktor hívás");
		Logging.log(4, "<< Elf deafult konstruktor");
	}

	/**
	 * @see Enemy#Enemy(float, float)
	 */
	public Elf(float defMaxLP, float defSpeed) {
		super(defMaxLP, defSpeed);
		Logging.log(2, ">> Elf konstruktor hívás, maxLP: " + defMaxLP
				+ " speed: " + defSpeed);
		Logging.log(4, "<< Elf konstruktor");

	}

	@Override
	public String getType() {
		return "Elf";
	}

	/**
	 * @return
	 * @see mordordefense.Enemy#leptet()
	 */
	@Override
	public boolean leptet() throws EnemyCannotStepException, EnemyDeadException {
		Logging.log(2, ">> Elf.leptet() hívás");
		if (lifePoint <= 0) {
			Logging.log(2, "<< Elf.leptet() exception");
			throw new EnemyDeadException();
		}
		long _time = System.currentTimeMillis();
		// Itt ha még nem lépett egyet se, akkor hagyjuk lépni!!!
		if (stepNumber > 0 && ((_time - timeOfLastStep) / 1000.f) * speed < 1) {
			Logging.log(2, "<< Elf.leptet(), nem tud meg lepni.");
			return false;
		}
		if (stepNumber < 0) {
			stepNumber = 0;
			timeOfLastStep = System.currentTimeMillis();
			Logging.log(2, "<< Elf.leptet(), belepett a spawnpointra");
			return true;
		}
		// Eltároljuk, hogy melyik szomszédra tud egyáltalán lépni.
		// Kis szépséghiba, hogy ha több olyan cellatípus is van, akire nem tud
		// lépni,
		// akkor azokra külön-külön le kell csekkolni a getType()-ot.
		// Szerencsére
		// jelenleg ez a helyzet nem áll fenn.
		ArrayList<RouteCell> possibleNext = new ArrayList<RouteCell>();
		if (routeCell != null) {
			for (Cell rc : routeCell.getSzomszedok().values()) {
				if (rc != null && !rc.getType().equalsIgnoreCase("FieldCell")
						&& rc.getID() > routeCell.getID()) {
					possibleNext.add((RouteCell) rc);
				}
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
			resetSpeed();
			nextCell.enter(this);
			stepNumber++;
			routeCell = nextCell;
			timeOfLastStep = System.currentTimeMillis();
		} else {
			Logging.log(2, "<< Elf.leptet() exception");
			throw new EnemyCannotStepException();
		}
		Logging.log(2, "<< Elf.leptet()");
		return true;
	}

	@Override
	public void sebez(Bullet b) {
		Logging.log(2, ">> Elf.sebez() hívás, paraméter: " + b.toString());
		if (b.isSlicing() && lifePoint > 1) {
			slice();
		} else {
			lifePoint -= b.getDamage(this);
			Logging.log(3, "\t új életerő: " + lifePoint);
			if (lifePoint <= 0) {
				for (EnemyListener l : listeners) {
					l.onDie(this);
				}
				dead = true;
				routeCell.leave(this);
			}
		}
		Logging.log(2, "<< Elf.sebez()");
	}

	@Override
	protected void slice() {
		Logging.log(2, ">> Elf.slice() hívás");
		Elf newEnemy = new Elf(lifePoint / 2, defSpeed);
		lifePoint = lifePoint / 2;
		routeCell.enter(newEnemy);
		for (EnemyListener l : listeners) {
			l.onSlice(newEnemy);
		}
		Logging.log(2, "<< Elf.slice()");
	}
}
