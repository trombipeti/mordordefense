package mordordefense;

import mordordefense.testing.Logging;

/**
 * Lövedéket megtestesítő osztály. Különböző típusú ellenségekre különbözően tud
 * hatni.
 * 
 */
public class Bullet {

	/**
	 * Tündékre ható sebzés.
	 */
	protected int elfDamage;

	/**
	 * Törpökre ható sebzés.
	 */
	protected int dwarfDamage;

	/**
	 * Hobbitokra ható sebzés.
	 */
	protected int hobbitDamage;

	/**
	 * Emberekra ható sebzés.
	 */
	protected int humanDamage;

	/**
	 * A lövedék kettvágja-e azt, akit eltalál.
	 */
	protected boolean slicing;

	/**
	 * @return A lövedék kettvágja-e azt, akit eltalál.
	 */
	public boolean isSlicing() {
		return slicing;
	}

	/**
	 * @param slicing
	 *            Kettévágós típusú legyen-e a lövedék.
	 */
	public void setSlicing(boolean slicing) {
		this.slicing = slicing;
	}

	/**
	 * Alap konstruktor, beállítja a sebzések értékeit.
	 * 
	 * @param dwarfDamage
	 *            Törpökre ható sebzése mértéke
	 * @param elfDamage
	 *            Tündékre ható sebzése mértéke
	 * @param hobbitDamage
	 *            Hobbitokra ható sebzése mértéke
	 * @param humanDamage
	 *            Embrekre ható sebzése mértéke
	 */
	public Bullet(int dwarfDamage, int elfDamage, int hobbitDamage,
			int humanDamage, boolean slicing) {
		Logging.log(">> Bullet konstruktor hivas, parameterek:");
		Logging.log("\t dwarfDamage: " + dwarfDamage + ", elfDamage: "
				+ elfDamage + ", hobbitDamage: " + hobbitDamage
				+ ", humanDamage: " + humanDamage + ", slicing: " + slicing);

		this.dwarfDamage = dwarfDamage;
		this.elfDamage = elfDamage;
		this.hobbitDamage = hobbitDamage;
		this.humanDamage = humanDamage;
		this.slicing = slicing;
	}

	/**
	 * Megsebzi a paraméterként kapott {@link Elf} ellenséget.
	 * 
	 * @param e
	 *            Elf (tünde) típusú ellenség
	 */
	public void damage(Elf e) {
		Logging.log(">> Bullet.damage() hívás, paraméter: " + e.toString());
		e.sebez(elfDamage);
	}

	/**
	 * Megsebzi a paraméterként kapott {@link Hobbit} ellenséget.
	 * 
	 * @param h
	 *            Hobbit típusú ellenség
	 */
	public void damage(Hobbit h) {
		Logging.log(">> Bullet.damage() hívás, paraméter: " + h.toString());
		h.sebez(hobbitDamage);
	}

	/**
	 * Megsebzi a paraméterként kapott {@link Dwarf} ellenséget.
	 * 
	 * @param d
	 *            Dwarf (törp) típusú ellenség
	 */
	public void damage(Dwarf d) {
		Logging.log(">> Bullet.damage() hívás, paraméter: " + d.toString());
		d.sebez(dwarfDamage);
	}

	/**
	 * Megsebzi a paraméterként kapott {@link Human} ellenséget.
	 * 
	 * @param h
	 *            Human (ember) típusú ellenség.
	 */
	public void damage(Human h) {
		Logging.log(">> Bullet.damage() hívás, paraméter: " + h.toString());
		h.sebez(humanDamage);
	}

	@Override
	public String toString() {
		String ret;
		ret = "Bullet, dwarfDamage: " + dwarfDamage + ", elfDamage: "
				+ elfDamage + ", hobbitDamage: " + hobbitDamage
				+ ", humanDamage: " + humanDamage + ", slicing: " + slicing;
		return ret;
	}
}
