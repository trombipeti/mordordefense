package mordordefense;

import mordordefense.testing.Logging;

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

	public String getType() {
		return "MordorCell";
	}
}
