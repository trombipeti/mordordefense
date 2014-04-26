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
		Logging.log(2, ">> Bullet konstruktor hivas, parameterek:");
		Logging.log(2, "\t dwarfDamage: " + dwarfDamage + ", elfDamage: "
				+ elfDamage + ", hobbitDamage: " + hobbitDamage
				+ ", humanDamage: " + humanDamage + ", slicing: " + slicing);

		this.dwarfDamage = dwarfDamage;
		this.elfDamage = elfDamage;
		this.hobbitDamage = hobbitDamage;
		this.humanDamage = humanDamage;
		this.slicing = slicing;
		Logging.log(4, "<< Bullet konstruktor");
	}

	/**
	 * @param h
	 *            Egy {@link Hobbit}.
	 * @return A hobbitokra ható sebzés.
	 */
	public int getDamage(Hobbit h) {
		return hobbitDamage;
	}

	/**
	 * @param h
	 *            Egy {@link Human}.
	 * @return A humanokra ható sebzés.
	 */
	public int getDamage(Human h) {
		return humanDamage;
	}

	/**
	 * @param d
	 *            Egy {@link Dwarf}.
	 * @return A törpökre ható sebzés.
	 */
	public int getDamage(Dwarf d) {
		return dwarfDamage;
	}

	/**
	 * @param e
	 *            Egy {@link Elf}.
	 * @return A tündékre ható sebzés.
	 */
	public int getDamage(Elf e) {
		return elfDamage;
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
