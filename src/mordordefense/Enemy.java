package mordordefense;

public abstract class Enemy {
	/** Attributes */
	protected int maxLifePoint;
	protected int lifePoint;
	protected int speed;
	protected int stepNumber;
	protected int timeSinceLastStep;
<<<<<<< HEAD
        protected RouteCell rc;
        
=======

	public Enemy(int parMaxLifePoint, int parSpeed) {
		maxLifePoint = parMaxLifePoint;
		lifePoint = parMaxLifePoint;
		speed = parSpeed;
	}
	
	public Enemy() {
		
	}
	
>>>>>>> origin/master
	/**
	 * Operation
	 * 
	 * @param mertek
	 */
<<<<<<< HEAD
                       
	public void lassit(int mertek) {
            Logging.log(">> Enemy.lassit() meghivva");
           // If((speed-mertek) < hataretrek){
           //     speed = hataretrek;
           // }
           // else {
                    speed -= mertek;
           //      }
            Logging.log("<< Enemy.lassit() void visszateres");
=======
	public void lassit(int mertek) {

>>>>>>> origin/master
	}

	/**
	 * Operation
	 * 
	 * @param mertek
	 */
	public void sebez(int mertek) {
<<<<<<< HEAD
            Logging.log(">> Enemy.sebez() meghivva");
            lifePoint -= mertek;
            Logging.log("<< Enemy.sebez() void visszateres");
=======

>>>>>>> origin/master
	}

	/**
	 * Operation
	 * 
	 */
	public void leptet() {
<<<<<<< HEAD
            Logging.log("<< Enemy.leptet() meghivva");
            //rc.leave                                      //elhagyja azt a mezot amin allt
            stepNumber++;                                   //noveli a lepesszamot
            timeSinceLastStep = 0;                          //0-za az idot, mert lepett
            for (int i=0; rc.szomszedok.size()!=i;i++){
                if(rc.szomszedok.get(i).ID == stepNumber){
                    rc=(RouteCell)rc.szomszedok.get(i);     //beallitja az rc-t arra a mezore amire lepett
                }
            }
            //rc.Enter                                      //belep arra a mezore ahova lep
            Logging.log("<<  Enemy.leptet() void visszateres");
=======
>>>>>>> origin/master
		
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
<<<<<<< HEAD
            Logging.log("<< Enemy.getStepNumber() meghivva");
            
            Logging.log("<< Enemy.getStepNumber() visszateres"+stepNumber+);
            return stepNumber;
=======
		return stepNumber;
>>>>>>> origin/master
	}

	public String toString() {
		return "Enemy, tipus: " + getType() + ", eletero: " + lifePoint + "/"
				+ maxLifePoint + ", sebesseg: " + speed + ", lepesszam: "
				+ stepNumber + ", utoljara ennyi ideje lepett: "
				+ timeSinceLastStep;
	}
}