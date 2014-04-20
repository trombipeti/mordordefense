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

	public float getElfMultiplier() {
		return elfMultiplier;
	}

	/**
	 * Törpökre ható szorzó.
	 */
	protected float dwarfMultiplier;

	public float getDwarfMultiplier() {
		return dwarfMultiplier;
	}

	/**
	 * Hobbitokra ható szorzó.
	 */
	protected float hobbitMultiplier;

	public float getHobbitMultiplier() {
		return hobbitMultiplier;
	}

	/**
	 * Emberekre ható szorzó.
	 */
	protected float humanMultiplier;

	public float getHumanMultiplier() {
		return humanMultiplier;
	}

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
		Logging.log(2, ">> MagicStone konstruktor hívás, paraméterek: "
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
		Logging.log(4, "<< MagicStone konstruktor");
	}

	/**
	 * tünde-sebzésszorzót visszadó függvény
	 * 
	 * @param e
	 *            Egy {@link Elf}
	 * @return elfMultiplier A tündékre ható sebzés szorzója.
	 */
	public float getMultiplier(Elf e) {
		Logging.log(
				3,
				">> MagicStone.getMultiplier() hívás, paraméter: "
						+ e.toString());
		Logging.log(3, "<< " + elfMultiplier);
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
		Logging.log(
				3,
				">> MagicStone.getMultiplier() hívás, paraméter: "
						+ h.toString());
		Logging.log(3, "<< " + humanMultiplier);
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
		Logging.log(
				3,
				">> MagicStone.getMultiplier() hívás, paraméter: "
						+ h.toString());
		Logging.log(3, "<< " + hobbitMultiplier);
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
		Logging.log(
				3,
				">> MagicStone.getMultiplier() hívás, paraméter: "
						+ d.toString());
		Logging.log(3, "<< " + dwarfMultiplier);
		return dwarfMultiplier;
	}

	/**
	 * általános sebzésszorzót visszadó függvény
	 * 
	 * @return damageMultiplier A sebzésszorzó
	 */
	public float getDamageMultiplier() {
		Logging.log(3, ">> MagicStone.getDamageMultiplier() hívás");
		Logging.log(3, "<< " + damageMultiplier);
		return damageMultiplier;
	}

	/**
	 * hatúsugár szorzót visszadó függvény
	 * 
	 * @return radiusMultiplier A hatósugárszorzó
	 */
	public float getRadiusMultiplier() {
		Logging.log(3, ">> MagicStone.getRadiusMultiplier() hívás");
		Logging.log(3, "<< " + radiusMultiplier);
		return radiusMultiplier;
	}

	/**
	 * frekvencia szorzót visszadó függvény
	 * 
	 * @return freqMultiplier
	 */
	public float getFreqMultiplier() {
		Logging.log(3, ">> MagicStone.getFreqMultiplier() hívás");
		Logging.log(3, "<< " + freqMultiplier);
		return freqMultiplier;
	}

	/**
	 * alap árat beállytó statikus függvény
	 * 
	 * @param cost
	 *            A kövek alapára.
	 */
	public static void setBaseCost(int cost) {
		Logging.log(4, ">> MagicStone.setBaseCost() hívás, paraméter: " + cost);
		Logging.log(4, "<< void");

		baseCost = cost;
	}

	/**
	 * alap árat visszaadó függvény
	 * 
	 * @return baseCost A kövek alapára
	 */
	public static int getBaseCost() {
		Logging.log(3, ">> MagicStone.getBaseCost() hívás");
		Logging.log(3, "<< " + baseCost);
		return baseCost;
	}

	/**
	 * TODO Ez mit kéne csináljon pontosan??? új kő árát számoló statikus
	 * függvény
	 * 
	 * @param numStones
	 * @return Az építés ára.
	 */
	static public int calcCost(int numStones) {
		Logging.log(3, ">> MagicStone.calcCost() hívás, paraméter: "
				+ numStones);
		int cost = baseCost;
		Logging.log(3, "<< " + cost);

		return cost;
	}

	@Override
	public String toString() {
		return "MagicStone, baseCost: " + baseCost + ", elfMultiplier: "
				+ elfMultiplier + ", dwarfMultiplier: " + dwarfMultiplier
				+ ", hobbitMultiplier: " + hobbitMultiplier + ", humanMultiplier: "
				+ humanMultiplier + ", damageMultiplier: " + damageMultiplier
				+ ", freqMultiplier: " + freqMultiplier + ", radiusMultiplier: "
				+ radiusMultiplier;

	}
}