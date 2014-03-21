package mordordefense;

import java.io.IOException;

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
		try {
			Logging.log(">> Bullet konstruktor hivas, parameterek:");
			Logging.log("\t dwarfDamage: " + dwarfDamage + ", elfDamage: "
					+ elfDamage + ", hobbitDamage: " + hobbitDamage
					+ ", humanDamage: " + humanDamage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		e.sebez(elfDamage);
	}

	/**
	 * Operation
	 * 
	 * @param h
	 *            Hobbit típusú ellenség
	 */
	public void damage(Hobbit h) {
		h.sebez(hobbitDamage);
	}

	/**
	 * Operation
	 * 
	 * @param d
	 *            Dwarf (törp) típusú ellenség
	 */
	public void damage(Dwarf d) {
		d.sebez(dwarfDamage);
	}

	/**
	 * Operation
	 * 
	 * @param h
	 *            Human (ember) típusú ellenség.
	 */
	public void damage(Human h) {
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
