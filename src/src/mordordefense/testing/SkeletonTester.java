package mordordefense.testing;

import mordordefense.*;
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;

public class SkeletonTester {

	public static void runSpawnTestCase() {
		Logging.log("=== SkeletonTester: spawnTestCase futtatása ===");
<<<<<<< HEAD
		Hobbit h = new Hobbit(8,1);
		Elf e = new Elf(13,1);
		SpawnPointCell sp = new SpawnPointCell(0,0);
		sp.setID(0);
		RouteCell rc = new RouteCell(1,0);
=======
		Hobbit h = new Hobbit(8, 1);
		Elf e = new Elf(13, 1);
		SpawnPointCell sp = new SpawnPointCell(0, 0);
		sp.setID(0);
		RouteCell rc = new RouteCell(1, 0);
>>>>>>> origin/master
		rc.setID(1);
		sp.setSzomszed(1, rc);
		rc.setSzomszed(3, sp);
		sp.enter(h);
		sp.enter(e);
<<<<<<< HEAD
		sp.leave(e);
		rc.enter(e);
=======
		try {
			e.leptet();
			h.leptet();
		} catch (EnemyCannotStepException e1) {
			e1.printStackTrace();
		} catch (EnemyDeadException e1) {
			e1.printStackTrace();
			Logging.log("!!! Az enemy meghalt: " + e.toString() + " !!!");
		}
>>>>>>> origin/master
		Logging.log("=== SkeletonTester: spawnTestCase vége ===");
	}

	public static void runTowerTestCase() {
		Logging.log("=== SkeletonTester: towerTestCase futtatása ===");
<<<<<<< HEAD
		Dwarf d = new Dwarf(10,1);
		Tower t = new Tower();
		RouteCell rc1 = new RouteCell(3,1);
		rc1.setID(1);
		RouteCell rc2 = new RouteCell(3,2);
=======
		Dwarf d = new Dwarf(10, 1);
		Tower t = new Tower();
		RouteCell rc1 = new RouteCell(3, 1);
		rc1.setID(1);
		RouteCell rc2 = new RouteCell(3, 2);
>>>>>>> origin/master
		rc2.setID(2);

		rc1.setSzomszed(0, rc2);
		rc1.addRouteCellListener(t);
		rc2.setSzomszed(2, rc1);
		rc2.addRouteCellListener(t);

		rc1.enter(d);
<<<<<<< HEAD
		rc1.leave(d);
		rc2.enter(d);
=======
		try {
			d.leptet();
		} catch (EnemyDeadException e) {
			e.printStackTrace();
			Logging.log("!!! Az enemy meghalt: " + d.toString() + " !!!");
		} catch (EnemyCannotStepException e) {
			e.printStackTrace();
		}
>>>>>>> origin/master
		Logging.log("=== SkeletonTester: towerTestCase vége ===");
	}

}
