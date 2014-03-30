package mordordefense;

import mordordefense.testing.Logging;

/**
 * Az ellenségek kiinduló helyét megtestesítő osztály.
 * 
 */
public class SpawnPointCell extends RouteCell {

	public SpawnPointCell(int x, int y) {
		super(x, y);
		Logging.log(">> SpawnPointCell konstruktor hívása, x: " + x + " y: "
				+ y);
	}

	@Override
	public String getType() {
		return "SpawnPointCell";
	}

}
