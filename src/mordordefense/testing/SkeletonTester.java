package mordordefense.testing;

import mordordefense.Cell.Dir;
import mordordefense.Controller;
import mordordefense.Dwarf;
import mordordefense.Elf;
import mordordefense.FieldCell;
import mordordefense.Hobbit;
import mordordefense.Human;
import mordordefense.MordorCell;
import mordordefense.RouteCell;
import mordordefense.SpawnPointCell;
import mordordefense.Tower;
import mordordefense.Trap;
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;

public class SkeletonTester {

	public static void runSpawnTestCase() {
		Logging.log(0, "=== SkeletonTester: spawnTestCase futtatása ===");
		Hobbit h = new Hobbit(8, 1);
		Elf e = new Elf(13, 1);
		SpawnPointCell sp = new SpawnPointCell(0, 0, 0);
		RouteCell rc = new RouteCell(1, 0, 1);
		RouteCell uc = new RouteCell(1, 1, 2);
		RouteCell jc = new RouteCell(2, 0, 3);
		sp.setSzomszed(Dir.RIGHT, rc);
		rc.setSzomszed(Dir.LEFT, sp);
		rc.setSzomszed(Dir.UP, uc);
		uc.setSzomszed(Dir.DOWN, rc);
		rc.setSzomszed(Dir.RIGHT, jc);
		jc.setSzomszed(Dir.LEFT, rc);
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
			Logging.log(0, "!!! Az enemy meghalt: " + e.toString() + " !!!");
		}
		Logging.log(0, "=== SkeletonTester: spawnTestCase vége ===");
	}

	public static void runTowerTestCase() {
		Logging.log(0, "=== SkeletonTester: towerTestCase futtatása ===");
		Dwarf d = new Dwarf(10, 1);
		Tower t = new Tower(1, 10, 1);
		RouteCell rc1 = new RouteCell(3, 1, 1);
		RouteCell rc2 = new RouteCell(3, 2, 2);
		RouteCell rc3 = new RouteCell(3, 3, 3);

		FieldCell fc = new FieldCell(2, 2);

		rc1.setSzomszed(Dir.UP, rc2);
		rc2.setSzomszed(Dir.DOWN, rc1);
		rc2.setSzomszed(Dir.UP, rc3);
		rc3.setSzomszed(Dir.DOWN, rc2);

		rc2.setSzomszed(Dir.LEFT, fc);
		fc.setSzomszed(Dir.RIGHT, rc2);

		fc.addTower(t);

		rc1.enter(d);
		try {
			d.leptet();
			d.leptet();
		} catch (EnemyDeadException e) {
			// e.printStackTrace();
			Logging.log(0, "!!! Az enemy meghalt: " + d.toString() + " !!!");
		} catch (EnemyCannotStepException e) {
			e.printStackTrace();
		}
		Logging.log(0, "=== SkeletonTester: towerTestCase vége ===");
	}

	public static void runMordorTestCase() {
		Logging.log(0, "=== SkeletonTester: mordorTestCase futtatása ===");
		Human h = new Human(10, 1);
		RouteCell rc = new RouteCell(0, 0, 0);
		rc.setID(0);
		MordorCell mc = new MordorCell(0, 1, 1);
		mc.setID(1);
		Controller c = new Controller(1, "");

		rc.setSzomszed(Dir.UP, mc);
		mc.setSzomszed(Dir.DOWN, rc);
		rc.enter(h);

		mc.addRouteCellListener(c);
		try {
			h.leptet();
		} catch (EnemyCannotStepException e1) {
			e1.printStackTrace();
		} catch (EnemyDeadException e1) {
			e1.printStackTrace();
			Logging.log(0, "!!! Az enemy meghalt: " + h.toString() + " !!!");
		}

		Logging.log(0, "=== SkeletonTester: mordorTestCase vége ===");
	}

	public static void runControllerTestCase() {
		Logging.log(0, "=== SkeletonTester: controllerTestCase futtatása ===");
		Controller c = new Controller(1, "palya1.p");
		c.init();
		c.addDwarf(new Dwarf(10, 2));
		c.stepAllEnemies();
		c.stepAllEnemies();
		// c.addHobbit(new Hobbit(8,3));
		c.stepAllEnemies();
		c.stepAllEnemies();
		c.stepAllEnemies();
		c.stepAllEnemies();
		c.stepAllEnemies();
		c.stepAllEnemies();
		Logging.log(0, "=== SkeletonTester: controllerTestCase vége ===");

	}

	public static void runTrapTestCase() {
		Logging.log(0, "=== SkeletonTester: trapTestCase futtatása ===");
		Dwarf d = new Dwarf(10, 1);
		Trap t = new Trap();
		RouteCell rc1 = new RouteCell(3, 1, 1);
		RouteCell rc2 = new RouteCell(3, 2, 2);
		RouteCell rc3 = new RouteCell(3, 3, 3);

		rc1.setSzomszed(Dir.UP, rc2);
		rc1.addRouteCellListener(t);
		rc2.setSzomszed(Dir.DOWN, rc1);
		rc2.addRouteCellListener(t);
		rc2.setSzomszed(Dir.UP, rc3);
		rc3.setSzomszed(Dir.DOWN, rc2);
		rc3.addRouteCellListener(t);

		rc1.enter(d);
		try {
			d.leptet();
			d.leptet();
		} catch (EnemyDeadException e) {
			// e.printStackTrace();
			Logging.log(0, "!!! Az enemy meghalt: " + d.toString() + " !!!");
		} catch (EnemyCannotStepException e) {
			e.printStackTrace();
		}
		Logging.log(0, "=== SkeletonTester: trapTestCase vége ===");
	}
}
