package mordordefense;

import mordordefense.testing.Logging;

/**
 * Csapdákra és tornyokra elhelyezhető varázskő.
 *
 */
public class MagicStone {

	/**
	 * A varázskövek alapára.
	 */
	static protected int baseCost;

	/**
	 * Tündékre ható szorzó.
	 */
	protected float elfMultiplier;

	/**
	 * Törpökre ható szorzó.
	 */
	protected float dwarfMultiplier;

	/**
	 * Hobbitokra ható szorzó.
	 */
	protected float hobbitMultiplier;

	/**
	 * Emberekre ható szorzó.
	 */
	protected float humanMultiplier;

	/**
	 * Általános sebzésszorzó.
	 */
	protected float damageMultiplier;

	/**
	 * A tornyok frekvenciájára ható szorzó
	 * 
	 * @see Tower#freq
	 */
	protected float freqMultiplier;

	/**
	 * A tornyok hatósugarára ható szorzó.
	 * 
	 * @see Tower#radius
	 */
	protected float radiusMultiplier;

	/**
	 * Konstruktor
	 * 
	 * @param elf
	 *            A tündékre ható sebzési szorzó
	 * @param dwarf
	 *            A törpökre ható sebzési szorzó
	 * @param hobbit
	 *            A hobbitokra ható sebzési szorzó
	 * @param human
	 *            Az emberekre ható sebzési szorzó
	 * @param damage
	 *            Általános sebzési szorzó
	 * @param freq
	 *            Torony esetén a frekvencia szorzója
	 * @param radius
	 *            Torony esetén a hatósugár szorzója
	 */
	public MagicStone(float elf, float dwarf, float hobbit, float human,
			float damage, float freq, float radius) {
		Logging.log(">> MagicStone konstruktor hívás, paraméterek: "
				+ "elfMultiplier: " + elf + ", dwarfMultiplier: " + dwarf
				+ ", hobbitMultiplier: " + hobbit + ", humanMultiplier: "
				+ human + ", damageMultiplier: " + damage
				+ ", freqMultiplier: " + freq + ", radiusMultiplier: " + radius);

		this.elfMultiplier = elf;
		this.dwarfMultiplier = dwarf;
		this.hobbitMultiplier = hobbit;
		this.humanMultiplier = human;

		this.damageMultiplier = damage;
		this.freqMultiplier = freq;
		this.radiusMultiplier = radius;
	}

	/**
	 * tünde-sebzésszorzót visszadó függvény
	 * 
	 * @param e
	 *            Egy {@link Elf}
	 * @return elfMultiplier A tündékre ható sebzés szorzója.
	 */
	public float getMultiplier(Elf e) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "
				+ e.toString());
		Logging.log("<< " + elfMultiplier);
		return elfMultiplier;
	}

	/**
	 * ember-sebzésszorzót visszadó függvény
	 * 
	 * @param h
	 *            Egy {@link Human}
	 * @return humanfMultiplier
	 */
	public float getMultiplier(Human h) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "
				+ h.toString());
		Logging.log("<< " + humanMultiplier);
		return humanMultiplier;
	}

	/**
	 * hobbit-sebzésszorzót visszadó függvény
	 * 
	 * @param h
	 *            egy {@link Hobbit}
	 * @return hobbitMultiplier
	 */
	public float getMulitplier(Hobbit h) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "
				+ h.toString());
		Logging.log("<< " + hobbitMultiplier);
		return hobbitMultiplier;
	}

	/**
	 * törp-sebzésszorzót visszadó függvény
	 * 
	 * @param d
	 *            Egy {@link Dwarf}
	 * @return dwarfMultiplier
	 */
	public float getMultiplier(Dwarf d) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "
				+ d.toString());
		Logging.log("<< " + dwarfMultiplier);
		return dwarfMultiplier;
	}

	/**
	 * általános sebzésszorzót visszadó függvény
	 * 
	 * @return damageMultiplier A sebzésszorzó
	 */
	public float getDamageMultiplier() {
		Logging.log(">> MagicStone.getDamageMultiplier() hívás");
		Logging.log("<< " + damageMultiplier);
		return damageMultiplier;
	}

	/**
	 * hatúsugár szorzót visszadó függvény
	 * 
	 * @return radiusMultiplier A hatósugárszorzó
	 */
	public float getRadiusMultiplier() {
		Logging.log(">> MagicStone.getRadiusMultiplier() hívás");
		Logging.log("<< " + radiusMultiplier);
		return radiusMultiplier;
	}

	/**
	 * frekvencia szorzót visszadó függvény
	 * 
	 * @return freqMultiplier
	 */
	public float getFreqMultiplier() {
		Logging.log(">> MagicStone.getFreqMultiplier() hívás");
		Logging.log("<< " + freqMultiplier);
		return freqMultiplier;
	}

	/**
	 * alap árat beállytó statikus függvény
	 * 
	 * @param cost
	 *            A kövek alapára.
	 */
	public static void setBaseCost(int cost) {
		Logging.log(">> MagicStone.setBaseCost() hívás, paraméter: " + cost);
		Logging.log("<< void");

		baseCost = cost;
	}

	/**
	 * alap árat visszaadó függvény
	 * 
	 * @return baseCost A kövek alapára
	 */
	public int getBaseCost() {
		Logging.log(">> MagicStone.getBaseCost() hívás");
		Logging.log("<< " + baseCost);
		return baseCost;
	}

	/**
	 * új kő árát számoló statikus függvény
	 * 
	 * @param numStones
	 * @return
	 */
	static public int calcCost(int numStones) {
		Logging.log(">> MagicStone.calcCost() hívás, paraméter: " + numStones);
		int cost = baseCost;
		Logging.log("<< " + cost);

		return cost;
	}

	@Override
	public String toString() {
		return "MagicStone, baseCost: " + baseCost + ", elfMultiplier"
				+ elfMultiplier + ", dwarfMultiplier" + dwarfMultiplier
				+ ", hobbitMultiplier" + hobbitMultiplier + ", humanMultiplier"
				+ humanMultiplier + ", damageMultiplier" + damageMultiplier
				+ ", freqMultiplier" + freqMultiplier + ", radiusMultiplier"
				+ radiusMultiplier;

	}
}