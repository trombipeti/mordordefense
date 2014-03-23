package mordordefense;

import mordordefense.testing.Logging;

public class Elf extends Enemy {
	/**
	 * Operation
	 * 
	 * @return
	 */
	public Elf() {
		Logging.log(">> Elf default konstruktor hívás");
	}
	
	public Elf(int parMaxLifePoint, int parSpeed ){
		super(parMaxLifePoint,parSpeed);
		Logging.log(">> Elf konstruktor hívás, maxLP: "+parMaxLifePoint+" speed: "+parSpeed);
		
	}

	@Override
	public String getType() {
		return "Elf";
	}
}