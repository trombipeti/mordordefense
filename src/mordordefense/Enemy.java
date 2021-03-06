package mordordefense;

import java.io.Serializable;
import java.util.HashSet;

import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * Az ellenségek heterogén kollekcióját lehetővé tevő, közös absztrakt
 * ősosztály.
 * 
 */
public abstract class Enemy implements Serializable {

	private static final long serialVersionUID = -4561743089866969291L;

	/**
	 * Az enemy maximális (és kezdeti) életereje.
	 */
	protected float maxLifePoint;

	public float getMaxLifePoint() {
		return maxLifePoint;
	}

	/**
	 * Az enemy aktuális életereje. Ha <= 0, akkor az enemy meghalt. Ezt a
	 * következő léptetésnél egy {@link EnemyDeadException} kivétellel jelzi.
	 */
	protected float lifePoint;

	/**
	 * Az enemy alapsebessége. Mértékegysége: mező/másodperc
	 */
	protected float baseSpeed;

	/**
	 * Az enemy aktuális sebessége. Egy megadott mértéknél sose csökken lejebb.
	 * Mező/másodperc.
	 */
	protected float speed;

	/**
	 * Az enemy által megtett lépések száma.
	 */
	protected int stepNumber = -1;

	/**
	 * Az utolsó lépés ideje. Ebből az értékből tudja megmondani, hogy tud-e
	 * lépni az aktuális sebességértéke mellett.
	 */
	protected long timeOfLastStep;

	/**
	 * Az enemy aktuális helyzetét jelző {@link RouteCell}
	 */
	protected RouteCell routeCell;

	/**
	 * Az enemy eseményeire feliratkozott listenerek.
	 */
	protected HashSet<EnemyListener> listeners = new HashSet<EnemyListener>();

	/**
	 * Enemy halálát jelző boolean
	 */
	protected boolean dead = false;

	/**
	 * visszaadja, hogy halott-e az enemy
	 * 
	 * @return
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Konstruktor, amely beállítja a maximális életerőt és a kezdősebességet.
	 * 
	 * @param parMaxLifePoint
	 *            A maximális életerő.
	 * @param parSpeed
	 *            A kezdősebesség.
	 */
	public Enemy(float parMaxLifePoint, float parSpeed) {
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
	 * @return A {@link RouteCell}, amin az enemy áll.
	 */
	public RouteCell getRouteCell() {
		return routeCell;
	}

	public long getTimeOfLastStep() {
		return timeOfLastStep;
	}

	public void setTimeOfLastStep(long timeOfLastStep) {
		this.timeOfLastStep = timeOfLastStep;
	}

	/**
	 * Az enemyt lelassító függvény. Hogy az enemy ne álljon meg és ne induljon
	 * el visszafele, egy megadott szint alá nem tud csökkenni.
	 * 
	 * @param mertek
	 *            Mennyivel csökkenjen a sebessége.
	 */
	public void lassit(float mertek) {
		// mertek=mertek/(Controller.timeStep/10.0f);
		Logging.log(2, ">> Enemy.lassit() hívás, paraméter: " + mertek);
		if (speed - mertek >= 0.1f) {
			speed -= mertek;
			Logging.log(3, "\t A sebessége ennyire csökkent: " + speed);
		} else {
			speed = 0.1f;
			Logging.log(3,
					"\t Szegény már így is nagyon lassú, nem lassítom tovább, sebessége: "
							+ speed);
		}
		Logging.log(2, "<< Enemy.lassit()");
	}

	/**
	 * Az enemy sebességét alapértékre állító függvény.
	 */
	public void resetSpeed() {
		Logging.log(3, ">> Enemy.resetSpeed() hívás");
		speed = baseSpeed;
		Logging.log(3, "<< Enemy.resetSpeed()");
	}

	/**
	 * Az enemyt sebző függvény.
	 * 
	 * @param b
	 *            Az enemyt megsebző {@link Bullet}
	 */
	abstract public void sebez(Bullet b);

	/**
	 * Az Enemyt kettévágó absztrakt függvény.
	 */
	abstract protected void slice();

	/**
	 * Az ellenségeket léptető metódus. Ha útelágazásnál több helyre is tud
	 * lépni, akkor véletlenszerűen választ. Diszkrét idő szimuláció NINCS,
	 * vagyis ha ritkán hívjuk meg, és nagyon gyorsak az enemyk, akkor rosszul
	 * fog működni, és mindegyik enemy csak egyet fog lépni.
	 * 
	 * @throws EnemyDeadException
	 *             Ha az enemy már meghalt.
	 * @throws EnemyCannotStepException
	 *             Ha nem talált cellát, ahova lépni tud (jól felépített pálya
	 *             és normálisan megírt Controller esetén ilyen kivételt nem
	 *             kéne, hogy dobjon.)
	 * @return sikeres volt-e a lépés, haladt-e az ellenség
	 */
	abstract public boolean leptet() throws EnemyDeadException,
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
		Logging.log(3, ">> Enemy.getStepNumber() hívás");
		Logging.log(3, "<< " + stepNumber);
		return stepNumber;
	}

	/**
	 * Ezzel a függvénnyel lehet feliratkozni az enemy eseményeire.
	 * 
	 * @param el
	 *            Egy {@link EnemyListener} interfészt implementáló osztály
	 */
	public void addEnemyListener(EnemyListener el) {
		listeners.add(el);
	}

	/**
	 * Ezzel a függvénnyel lehet leiratkozni az enemy eseményeiről.
	 * 
	 * @param el
	 *            A leiratkozni kívánt {@link EnemyListener}.
	 */
	public void removeEnemyListener(EnemyListener el) {
		listeners.remove(el);
	}

	@Override
	public String toString() {
		String ret = "Enemy, tipus: " + getType() + ", eletero: " + lifePoint
				+ "/" + maxLifePoint + ", sebesseg: " + speed + ", lepesszam: "
				+ stepNumber;
		if (routeCell != null) {
			ret += ", pozíciója: " + routeCell.getCoords()[0] + " "
					+ routeCell.getCoords()[1];
		}
		return ret;
	}
}