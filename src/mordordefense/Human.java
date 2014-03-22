package mordordefense;

import mordordefense.testing.Logging;

public class Human extends Enemy {
	/**
	 * Operation
	 * 
	 * @return
	 */
	public Human() {
		Logging.log(">> Human default konstruktor hívás");
	}
	
	public Human(int parMaxLifePoint, int parSpeed ){
		super(parMaxLifePoint,parSpeed);
		Logging.log(">> Human konstruktor hívás, maxLP: "+parMaxLifePoint+" speed: "+parSpeed);
		
	}

	@Override
	public String getType() {
		return "Human";
	}
}