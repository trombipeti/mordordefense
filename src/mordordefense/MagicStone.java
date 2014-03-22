package mordordefense;

import mordordefense.testing.Logging;

public class MagicStone {

	static protected int baseCost;		

	protected float elfMultiplier;
	protected float dwarfMultiplier;
	protected float hobbitMultiplier;
	protected float humanMultiplier;

	protected float damageMultiplier;
	protected float freqMultiplier;
	protected float radiusMultiplier;

	/**
	 * Konstruktor
	 * @param elf
	 * @param dwarf
	 * @param hobbit
	 * @param human
	 * @param damage
	 * @param freq
	 * @param radius
	 */
	public MagicStone(float elf, float dwarf, float hobbit, float human, float damage, float freq, float radius){
		Logging.log(">> MagicStone konstruktor hívás, paraméterek: "+"elfMultiplier: "+elf+", dwarfMultiplier: "+dwarf+
					", hobbitMultiplier: "+hobbit+", humanMultiplier: "+human+", damageMultiplier: "+damage+", freqMultiplier: "+freq+", radiusMultiplier: "+radius);

		this.elfMultiplier=elf;
		this.dwarfMultiplier=dwarf;
		this.hobbitMultiplier=hobbit;
		this.humanMultiplier=human;
		
		this.damageMultiplier=damage;
		this.freqMultiplier=freq;
		this.radiusMultiplier=radius;
	}
	/**
	 * tünde-sebzésszorzót visszadó függvény
	 * 
	 * @param e
	 * @return elffMultiplier
	 */	
	public float getMultiplier(Elf e) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+e.toString());
		Logging.log("<< "+elfMultiplier);
		return elfMultiplier;
	}

	/**
	 * ember-sebzésszorzót visszadó függvény
	 * 
	 * @param h
	 * @return humanfMultiplier
	 */
	public float getMultiplier(Human h) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+h.toString()); 		
		Logging.log("<< "+humanMultiplier);
		return humanMultiplier;
	}

	/**
	 * hobbit-sebzésszorzót visszadó függvény
	 * 
	 * @param h
	 * @return hobbitMultiplier
	 */
	public float getMulitplier(Hobbit h) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+h.toString()); 		
		Logging.log("<< "+hobbitMultiplier);
		return hobbitMultiplier;
	}

	/**
	 * törp-sebzésszorzót visszadó függvény
	 * 
	 * @param d
	 * @return dwarfMultiplier
	 */
	public float getMultiplier(Dwarf d) {
		Logging.log(">> MagicStone.getMultiplier() hívás, paraméter: "+d.toString()); 		
		Logging.log("<< "+dwarfMultiplier);
		return dwarfMultiplier;
	}

	/**
	 * általános sebzésszorzót visszadó függvény
	 * @return damageMultiplier
	 */
	public float getDamageMultiplier() {
		Logging.log(">> MagicStone.getDamageMultiplier() hívás"); 		
		Logging.log("<< "+damageMultiplier);
		return damageMultiplier;
	}

	/**
	 * hatúsugár szorzót visszadó függvény
	 * @return radiusMultiplier
	 */
	public float getRadiusMultiplier() {
		Logging.log(">> MagicStone.getRadiusMultiplier() hívás"); 		
		Logging.log("<< "+radiusMultiplier);
		return radiusMultiplier;
	}

	/**
	 * frekvencia szorzót visszadó függvény
	 * @return freqMultiplier
	 */
	public float getFreqMultiplier() {
		Logging.log(">> MagicStone.getFreqMultiplier() hívás"); 		
		Logging.log("<< "+freqMultiplier);
		return freqMultiplier;
	}

	/**
	 * alap árat beállytó statikus függvény
	 * @param cost
	 */
	public static void setBaseCost(int cost){
		Logging.log(">> MagicStone.setBaseCost() hívás, paraméter: "+cost); 		
		Logging.log("<< void");

		baseCost=cost;
	}
	/**
	 * alap árat visszaadó függvény
	 * @return baseCost
	 */
	public int getBaseCost() {
		Logging.log(">> MagicStone.getBaseCost() hívás"); 		
		Logging.log("<< "+baseCost);
		return baseCost;
	}
	/**
	 * új kő árát számoló statikus függvény
	 * @param numStones
	 * @return
	 */
	static public int calcCost(int numStones){
		Logging.log(">> MagicStone.calcCost() hívás, paraméter: "+numStones); 		
		int cost=baseCost;
		Logging.log("<< "+cost);
		
		return cost;
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