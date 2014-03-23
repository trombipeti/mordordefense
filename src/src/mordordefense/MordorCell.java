package mordordefense;

import mordordefense.testing.Logging;

<<<<<<< HEAD
public class MordorCell extends RouteCell {
	
	public MordorCell(int x, int y){
		super(x,y);
		Logging.log(">>MordorCell konstruktor hívás, x: "+x+" y: "+y);
	}
	
=======
/**
 * Mordort megtestesítő út. Ha erre rálép egy ellenség, vége a játéknak, és
 * vesztett Szarumán.
 * 
 */
public class MordorCell extends RouteCell {

	public MordorCell(int x, int y) {
		super(x, y);
		Logging.log(">>MordorCell konstruktor hívás, x: " + x + " y: " + y);
	}

>>>>>>> origin/master
	public String getType() {
		return "MordorCell";
	}
}
