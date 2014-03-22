package mordordefense;

import mordordefense.testing.Logging;

public class Dwarf extends Enemy {

	/**
	 * Operation
	 * 
	 * @return
	 */
	public Dwarf() {
		Logging.log(">> Dwarf default konstruktor hívás");
	}
	
	public Dwarf(int parMaxLifePoint, int parSpeed ){
		super(parMaxLifePoint,parSpeed);
		Logging.log(">> Dwarf konstruktor hívás, maxLP: "+parMaxLifePoint+" speed: "+parSpeed);
		
	}
	
	@Override
	public String getType() {
		return "Dwarf";
	}
}