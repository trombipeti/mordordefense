package mordordefense;

import mordordefense.testing.Logging;

public class Hobbit extends Enemy

{
	/**
	 * Operation
	 * 
	 * @return
	 */
	public Hobbit() {
		Logging.log(">> Hobbit default konstruktor hívás");
	}
	
	public Hobbit(int parMaxLifePoint, int parSpeed ){
		super(parMaxLifePoint,parSpeed);
		Logging.log(">> Hobbit konstruktor hívás, maxLP: "+parMaxLifePoint+" speed: "+parSpeed);
		
	}

	@Override
	public String getType() {
		return "Hobbit";
	}
}