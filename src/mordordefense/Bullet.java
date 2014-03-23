package mordordefense;

import mordordefense.testing.Logging;

public class Bullet {

	protected int elfDamage;
	protected int dwarfDamage;
	protected int hobbitDamage;
	protected int humanDamage;

	/**
	 * Operation
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
			int humanDamage) {
		Logging.log(">> Bullet konstruktor hivas, parameterek:");
		Logging.log("\t dwarfDamage: " + dwarfDamage + ", elfDamage: "
				+ elfDamage + ", hobbitDamage: " + hobbitDamage
				+ ", humanDamage: " + humanDamage);

		this.dwarfDamage = dwarfDamage;
		this.elfDamage = elfDamage;
		this.hobbitDamage = hobbitDamage;
		this.humanDamage = humanDamage;
	}

	/**
	 * Operation
	 * 
	 * @param e
	 *            Elf (tünde) típusú ellenség
	 */
	public void damage(Elf e) {
		Logging.log(">> Bullet.damage() hívás, paraméter: "+e.toString()); 
		e.sebez(elfDamage);
	}

	/**
	 * Operation
	 * 
	 * @param h
	 *            Hobbit típusú ellenség
	 */
	public void damage(Hobbit h) {
		Logging.log(">> Bullet.damage() hívás, paraméter: "+h.toString());
		h.sebez(hobbitDamage);
	}

	/**
	 * Operation
	 * 
	 * @param d
	 *            Dwarf (törp) típusú ellenség
	 */
	public void damage(Dwarf d) {
		Logging.log(">> Bullet.damage() hívás, paraméter: "+d.toString());
		d.sebez(dwarfDamage);
	}

	/**
	 * Operation
	 * 
	 * @param h
	 *            Human (ember) típusú ellenség.
	 */
	public void damage(Human h) {
		Logging.log(">> Bullet.damage() hívás, paraméter: "+h.toString());
		h.sebez(humanDamage);
	}

	@Override
	public String toString() {
		String ret;
		ret = "Bullet, dwarfDamage: " + dwarfDamage + ", elfDamage: "
				+ elfDamage + ", hobbitDamage: " + hobbitDamage
				+ ", humanDamage: " + humanDamage;
		return ret;
	}
}
