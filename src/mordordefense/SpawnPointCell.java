package mordordefense;

import mordordefense.testing.Logging;

/**
 * Az ellenségek kiinduló helyét megtestesítő osztály.
 * 
 */
public class SpawnPointCell extends RouteCell {

	public SpawnPointCell(int x, int y, int ID) {
		super(x, y, ID);
		Logging.log(2, ">> SpawnPointCell konstruktor hívása, x: " + x + " y: "
				+ y + ", ID: " + ID);
		Logging.log(4, "<< SpawnPointCell konstruktor");
	}

	@Override
	public String getType() {
		return "SpawnPointCell";
	}

}
