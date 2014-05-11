package mordordefense;

import mordordefense.testing.Logging;

/**
 * Mordort megtestesítő út. Ha erre rálép egy ellenség, vége a játéknak, és
 * vesztett Szarumán.
 * 
 */
public class MordorCell extends RouteCell {

	private static final long serialVersionUID = -7876818825648099206L;

	public MordorCell(int x, int y, int ID) {
		super(x, y, ID);
		Logging.log(2, ">> MordorCell konstruktor hívás, x: " + x + ", y: " + y
				+ ", ID: " + ID);
		Logging.log(4, "<< MordorCell konstruktor");
	}

	public String getType() {
		return "MordorCell";
	}
}
