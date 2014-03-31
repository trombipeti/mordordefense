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
		Logging.log(">> Elf default konstruktor hívás");
		Logging.log("<< Elf deafult konstruktor");
	}

	/**
	 * @see Enemy#Enemy(int, int)
	 */
	public Elf(int parMaxLifePoint, int parSpeed) {
		super(parMaxLifePoint, parSpeed);
		Logging.log(">> Elf konstruktor hívás, maxLP: " + parMaxLifePoint
				+ " speed: " + parSpeed);
		Logging.log("<< Elf konstruktor");

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
			Logging.log("\t Erre a cellára lépek: " + nextCell.toString());
			routeCell.leave(this);
			nextCell.enter(this);
			resetSpeed();
			stepNumber++;
			routeCell = nextCell;
		} else {
			throw new EnemyCannotStepException();
		}
		Logging.log("<< Elf.leptet()");
	}

	@Override
	public void sebez(Bullet b) {
		Logging.log(">> Elf.sebez() hívás, paraméter: " + b.toString());
		if (b.isSlicing()) {
			slice();
		} else {
			lifePoint -= b.getDamage(this);
			Logging.log("\t új életerő: " + lifePoint);
		}
	}

	@Override
	protected void slice() {
		Logging.log(">> Elf.slice() hívás");
		Elf newEnemy = new Elf(lifePoint/2,speed);
		lifePoint = (int) (Math.floor(lifePoint+0.5));
		newEnemy.setRouteCell(routeCell);
		for (EnemyListener l : listeners) {
			l.onSlice(newEnemy);
		}
		Logging.log("<< Elf.slice()");
	}
}
