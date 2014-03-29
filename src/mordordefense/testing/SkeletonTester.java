package mordordefense.testing;

import mordordefense.*;
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;

public class SkeletonTester {

	public static void runSpawnTestCase() {
		Logging.log("=== SkeletonTester: spawnTestCase futtatása ===");
		Hobbit h = new Hobbit(8, 1);
		Elf e = new Elf(13, 1);
		SpawnPointCell sp = new SpawnPointCell(0, 0);
		sp.setID(0);
		RouteCell rc = new RouteCell(1, 0);
		rc.setID(1);
		RouteCell uc = new RouteCell(1,1);
		uc.setID(2);
		RouteCell jc = new RouteCell(2,0);
		jc.setID(2);
		sp.setSzomszed(1, rc);
		rc.setSzomszed(3, sp);
		rc.setSzomszed(0, uc);
		uc.setSzomszed(2, rc);
		rc.setSzomszed(1, jc);
		jc.setSzomszed(3, rc);
		sp.enter(h);
		sp.enter(e);
		try {
			e.leptet();
			h.leptet();
			e.leptet();
			h.leptet();
		} catch (EnemyCannotStepException e1) {
			e1.printStackTrace();
		} catch (EnemyDeadException e1) {
			e1.printStackTrace();
			Logging.log("!!! Az enemy meghalt: " + e.toString() + " !!!");
		}
		Logging.log("=== SkeletonTester: spawnTestCase vége ===");
	}

	public static void runTowerTestCase() {
		Logging.log("=== SkeletonTester: towerTestCase futtatása ===");
		Dwarf d = new Dwarf(10, 1);
		Tower t = new Tower(1, 10, 1);
		RouteCell rc1 = new RouteCell(3, 1);
		rc1.setID(1);
		RouteCell rc2 = new RouteCell(3, 2);
		rc2.setID(2);
		RouteCell rc3 = new RouteCell(3, 3);
		rc1.setID(3);
		
		FieldCell fc = new FieldCell(2, 2);

		rc1.setSzomszed(0, rc2);
		rc2.setSzomszed(2, rc1);
		rc2.setSzomszed(0, rc3);
		rc3.setSzomszed(2, rc2);
		
		rc2.setSzomszed(3, fc);
		fc.setSzomszed(1, rc2);
		
		fc.addTower(t);

		rc1.enter(d);
		try {
			d.leptet();
			d.leptet();
		} catch (EnemyDeadException e) {
			// e.printStackTrace();
			Logging.log("!!! Az enemy meghalt: " + d.toString() + " !!!");
		} catch (EnemyCannotStepException e) {
			e.printStackTrace();
		}
		Logging.log("=== SkeletonTester: towerTestCase vége ===");
	}

	public static void runMordorTestCase() {
		Logging.log("=== SkeletonTester: mordorTestCase futtatása ===");
		Human h = new Human(10, 1);
		RouteCell rc = new RouteCell(0, 0);
		rc.setID(0);
		MordorCell mc = new MordorCell(0, 1);
		mc.setID(1);
		Controller c = new Controller(1);

		rc.setSzomszed(0, mc);
		mc.setSzomszed(2, rc);
		rc.enter(h);

		mc.addRouteCellListener(c);
		try {
			h.leptet();
		} catch (EnemyCannotStepException e1) {
			e1.printStackTrace();
		} catch (EnemyDeadException e1) {
			e1.printStackTrace();
			Logging.log("!!! Az enemy meghalt: " + h.toString() + " !!!");
		}

		Logging.log("=== SkeletonTester: mordorTestCase vége ===");
	}

	public static void runControllerTestCase() {
		Logging.log("=== SkeletonTester: controllerTestCase futtatása ===");
		Controller c = new Controller(1);
		c.init();
		c.run();
		Logging.log("=== SkeletonTester: controllerTestCase vége ===");

	}
	
	public static void runTrapTestCase() {
		Logging.log("=== SkeletonTester: trapTestCase futtatása ===");
		Dwarf d = new Dwarf(10, 1);
		Trap t = new Trap();
		RouteCell rc1 = new RouteCell(3, 1);
		rc1.setID(1);
		RouteCell rc2 = new RouteCell(3, 2);
		rc2.setID(2);
		RouteCell rc3 = new RouteCell(3, 3);
		rc1.setID(3);

		rc1.setSzomszed(0, rc2);
		rc1.addRouteCellListener(t);
		rc2.setSzomszed(2, rc1);
		rc2.addRouteCellListener(t);
		rc2.setSzomszed(0, rc3);
		rc3.setSzomszed(2, rc2);
		rc3.addRouteCellListener(t);

		rc1.enter(d);
		try {
			d.leptet();
			d.leptet();
		} catch (EnemyDeadException e) {
			// e.printStackTrace();
			Logging.log("!!! Az enemy meghalt: " + d.toString() + " !!!");
		} catch (EnemyCannotStepException e) {
			e.printStackTrace();
		}
		Logging.log("=== SkeletonTester: trapTestCase vége ===");
	}
}
