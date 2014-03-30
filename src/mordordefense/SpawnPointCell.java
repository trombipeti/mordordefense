package mordordefense;

import mordordefense.testing.Logging;

/**
 * Az ellenségek kiinduló helyét megtestesítő osztály.
 * 
 */
public class SpawnPointCell extends RouteCell {

	public SpawnPointCell(int x, int y, int ID) {
		super(x, y, ID);
		Logging.log(">> SpawnPointCell konstruktor hívása, x: " + x + " y: "
				+ y + ", ID: " + ID);
	}

	@Override
	public String getType() {
		return "SpawnPointCell";
	}

}
