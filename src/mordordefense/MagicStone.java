package mordordefense;

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
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+e.toString());
		Logging.log("<< "+elfMultiplier);
		return elfMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @param h
	 * @return float
	 */
	public float getMultiplier(Human h) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+h.toString()); 		
		Logging.log("<< "+humanMultiplier);
		return humanMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @param h
	 * @return float
	 */
	public float getMulitplier(Hobbit h) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+h.toString()); 		
		Logging.log("<< "+hobbitMultiplier);
		return hobbitMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @param d
	 * @return float
	 */
	public float getMultiplier(Dwarf d) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+d.toString()); 		
		Logging.log("<< "+dwarfMultiplier);
		return dwarfMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return float
	 */
	public float getDamageMultiplier() {
		Logging.log(">> MagicStone.getDamageMultiplier() hívás"); 		
		Logging.log("<< "+damageMultiplier);
		return damageMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return float
	 */
	public float getRadiusMultiplier() {
		Logging.log(">> MagicStone.getRadiusMultiplier() hívás"); 		
		Logging.log("<< "+radiusMultiplier);
		return radiusMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return float
	 */
	public float getFreqMultiplier() {
		Logging.log(">> MagicStone.getFreqMultiplier() hívás"); 		
		Logging.log("<< "+freqMultiplier);
		return freqMultiplier;
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getBaseCost() {
		Logging.log(">> MagicStone.getBaseCost() hívás"); 		
		Logging.log("<< "+baseCost);
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