package mordordefense;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Az ellenségek heterogén kollekcióját lehetővé tevő, közös absztrakt
 * ősosztály.
 * 
 */
public abstract class Enemy {

	/**
	 * Az enemy maximális (és kezdeti) életereje.
	 */
	protected int maxLifePoint;

	/**
	 * Az enemy aktuális életereje. Ha <= 0, akkor az enemy meghalt. Ezt a
	 * következő léptetésnél egy {@link EnemyDeadException} kivétellel jelzi.
	 */
	protected int lifePoint;

	/**
	 * Az enemy alapsebessége.
	 */
	protected int baseSpeed;

	/**
	 * Az enemy aktuális sebessége. Egy megadott mértéknél sose csökken lejebb.
	 */
	protected int speed;

	/**
	 * Az enemy által megtett lépések száma.
	 */
	protected int stepNumber;

	/**
	 * Az utolsó lépés ideje. Ebből az értékből tudja megmondani, hogy
	 * tud-e lépni az aktuális sebességértéke mellett.
	 */
	protected long timeOfLastStep;

	/**
	 * Az enemy aktuális helyzetét jelző {@link RouteCell}
	 */
	protected RouteCell routeCell;

	/**
	 * Konstruktor, amely beállítja a maximális életerőt és a kezdősebességet.
	 * 
	 * @param parMaxLifePoint
	 *            A maximális életerő.
	 * @param parSpeed
	 *            A kezdősebesség.
	 */
	public Enemy(int parMaxLifePoint, int parSpeed) {
		maxLifePoint = parMaxLifePoint;
		lifePoint = parMaxLifePoint;
		baseSpeed = parSpeed;
		speed = parSpeed;
	}

	/**
	 * Alap konstruktor
	 */
	public Enemy() {

	}

	/**
	 * Konstruktor, amely beállítja egy megadott RouteCell mezőre
	 * 
	 * @param rc
	 *            Az ellenség kezdeti helyzete ({@link RouteCell})
	 */
	public Enemy(RouteCell rc) {
		routeCell = rc;
	}

	/**
	 * Beállítja az enemy helyzetét. NEM ezzel a függvénnyel kell léptetni! Ezt
	 * a függvényt tipikusan a cella hívja meg, amelyikre rálépett.
	 * 
	 * @param rc
	 *            Az enemy helyét meghatározó {@link RouteCell}.
	 */
	public void setRouteCell(RouteCell rc) {
		routeCell = rc;
	}

	/**
	 * Az enemyt lelassító függvény. Hogy az enemy ne álljon meg és ne induljon
	 * el visszafele, egy megadott szint alá nem tud csökkenni.
	 * 
	 * @param mertek
	 *            Mennyivel csökkenjen a sebessége.
	 */
	public void lassit(int mertek) {
		Logging.log(">> Enemy.lassit() hívás, paraméter: " + mertek);
		if (speed - mertek >= 1) {
			speed -= mertek;
			Logging.log("\t A sebessége ennyire csökkent: " + speed);
		} else {
			Logging.log("\t Szegény már így is nagyon lassú, nem lassítom tovább, sebessége: "
					+ speed);
		}
		Logging.log("<< Enemy.lassit()");
	}

	/**
	 * Az enemy sebességét alapértékre állító függvény.
	 */
	public void resetSpeed() {
		Logging.log(">> Enemy.resetSpeed() hívás");
		speed = baseSpeed;
		Logging.log("<< Enemy.resetSpeed()");
	}

	/**
	 * Az enemyt sebző függvény.
	 * 
	 * @param mertek
	 *            Mennyivel csökkenjen az enemy életereje.
	 */
	public void sebez(int mertek) {
		Logging.log(">> Enemy.sebez() hívás, paraméter: " + mertek);
		lifePoint -= mertek;
		Logging.log("\t Az enemy új életereje: " + lifePoint);
	}

	/**
	 * Az ellenségeket léptető metódus. Ha útelágazásnál több helyre is tud
	 * lépni, akkor véletlenszerűen választ.
	 * 
	 * @throws EnemyDeadException
	 *             Ha az enemy már meghalt.
	 * @throws EnemyCannotStepException
	 *             Ha nem talált cellát, ahova lépni tud (jól felépített pálya
	 *             és normálisan megírt Controller esetén ilyen kivételt nem
	 *             kéne, hogy dobjon.)
	 * 
	 */
	abstract public void leptet() throws EnemyDeadException,
			EnemyCannotStepException;

	/**
	 * Az enemy típusát szövegesen visszaadó függvény, leginkább a kiíratáshoz.
	 * 
	 * @return String Az enemy típusa, pl Dwarf, Elf stb.
	 */
	abstract public String getType();

	/**
	 * Az enemy eddigi lépésszámát megadó metódus.
	 * 
	 * @return int Mennyit lépett eddig az enemy.
	 */
	public int getStepNumber() {
		Logging.log(">> Enemy.getStepNumber() hívás");
		Logging.log("<< " + stepNumber);
		return stepNumber;
	}

	@Override
	public String toString() {
		return "Enemy, tipus: " + getType() + ", eletero: " + lifePoint + "/"
				+ maxLifePoint + ", sebesseg: " + speed + ", lepesszam: "
				+ stepNumber + ", utoljara ekkor lepett: "
				+ timeOfLastStep;
	}
}