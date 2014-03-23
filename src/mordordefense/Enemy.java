package mordordefense;

public abstract class Enemy {
	/** Attributes */
	protected int maxLifePoint;
	protected int lifePoint;
	protected int speed;
	protected int stepNumber;
	protected int timeSinceLastStep;

	public Enemy(int parMaxLifePoint, int parSpeed) {
		maxLifePoint = parMaxLifePoint;
		lifePoint = parMaxLifePoint;
		speed = parSpeed;
	}
	
	public Enemy() {
		
	}
	
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
	abstract public String getType();

	/**
	 * Operation
	 * 
	 * @return int
	 */
	public int getStepNumber() {
		return stepNumber;
	}

	public String toString() {
		return "Enemy, tipus: " + getType() + ", eletero: " + lifePoint + "/"
				+ maxLifePoint + ", sebesseg: " + speed + ", lepesszam: "
				+ stepNumber + ", utoljara ennyi ideje lepett: "
				+ timeSinceLastStep;
	}
}