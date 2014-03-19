package mordordefense;

public abstract class Enemy {
	/** Attributes */
	protected int maxLifePoint;
	protected int lifePoint;
	protected int speed;
	protected int stepNumber;
	protected int timeSinceLastStep;

	/**
	 * Operation
	 * 
	 * @param mertek
	 */
	public void lassit(int mertek) {

	}

	/**
	 * Operation
	 * 
	 * @param mertek
	 */
	public void sebez(int mertek) {

	}

	/**
	 * Operation
	 * 
	 */
	public void leptet() {

	}

	/**
	 * Operation
	 * 
	 * @return String
	 */
	public String getType() {
		return null;
	}

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getStepNumber() {
		return 0;
	}
}