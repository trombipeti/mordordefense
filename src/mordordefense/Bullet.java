package mordordefense;

public class Bullet {

	private int elfDamage;
	private int dwarfDamage;
	private int hobbitDamage;
	private int humanDamage;

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
		System.out.println(">> Bullet konstruktor hívás, paraméterek:");
		System.out.println("\t dwarfDamage: " + dwarfDamage + ", elfDamage: "
				+ elfDamage+", hobbitDamage: "+hobbitDamage+", humanDamage: "+humanDamage);
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
}
