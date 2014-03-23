package mordordefense.testing;

import mordordefense.*;

public class SkeletonTester {
	
	public static void runSpawnTestCase() {
		Logging.log("=== SkeletonTester: spawnTestCase futtatása ===");
		Hobbit h = new Hobbit(8,1);
		Elf e = new Elf(13,1);
		SpawnPointCell sp = new SpawnPointCell();
		RouteCell rc = new RouteCell();
		sp.enter(h);
		sp.enter(e);
		sp.leave(e);
		rc.enter(e);
		Logging.log("=== SkeletonTester: spawnTestCase vége ===");
	}
	
	public static void runTowerTestCase() {
		Logging.log("=== SkeletonTester: towerTestCase futtatása ===");
		Dwarf d = new Dwarf(10,1);
		Tower t = new Tower();
		RouteCell rc1 = new RouteCell();
		rc1.setID(1);
		RouteCell rc2 = new RouteCell();
		rc2.setID(2);
		
		rc1.setSzomszed(0, rc2);
		rc1.addRouteCellListener(t);
		rc2.setSzomszed(2, rc1);
		rc2.addRouteCellListener(t);
		
		rc1.enter(d);
		rc1.leave(d);
		rc2.enter(d);
		Logging.log("=== SkeletonTester: towerTestCase vége ===");
	}
	
}
