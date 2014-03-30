package mordordefense;

import mordordefense.testing.Logging;

/**
 * Mordort megtestesítő út. Ha erre rálép egy ellenség, vége a játéknak, és
 * vesztett Szarumán.
 * 
 */
public class MordorCell extends RouteCell {

	public MordorCell(int x, int y, int ID) {
		super(x, y, ID);
		Logging.log(">> MordorCell konstruktor hívás, x: " + x + ", y: " + y + ", ID: " + ID);
	}

	public String getType() {
		return "MordorCell";
	}
}
