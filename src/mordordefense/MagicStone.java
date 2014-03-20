package mordordefense;

import sun.util.calendar.BaseCalendar;

public class MagicStone {

	protected int baseCost;

	protected float elfMultiplier;
	protected float dwarfMultiplier;
	protected float hobbitMultiplier;
	protected float humanMultiplier;

	protected float damageMultiplier;
	protected float freqMultiplier;
	protected float radiusMultiplier;

	/**
	 * Operation
	 * 
	 * @param e
	 * @return float
	 */
	public float getMultiplier(Elf e) {
		return elfMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @param h
	 * @return float
	 */
	public float getMultiplier(Human h) {
		return humanMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @param h
	 * @return float
	 */
	public float getMulitplier(Hobbit h) {
		return hobbitMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @param d
	 * @return float
	 */
	public float getMultiplier(Dwarf d) {
		return dwarfMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return float
	 */
	public float getDamageMultiplier() {
		return damageMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return float
	 */
	public float getRadiusMultiplier() {
		return radiusMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return float
	 */
	public float getFreqMultiplier() {
		return freqMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getBaseCost() {
		return baseCost;
	}

	public String toString() {
		return "MagicStone, baseCost: " + baseCost + ", elfMultiplier"
				+ elfMultiplier + ", dwarfMultiplier" + dwarfMultiplier
				+ ", hobbitMultiplier" + hobbitMultiplier + ", humanMultiplier"
				+ humanMultiplier + ", damageMultiplier" + damageMultiplier
				+ ", freqMultiplier" + freqMultiplier + ", radiusMultiplier"
				+ radiusMultiplier;

	}
}