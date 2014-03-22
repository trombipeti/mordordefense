package mordordefense.testing;

import mordordefense.*;

public class SkeletonTester {
	
	public static void runSpawnTestCase() {
		Hobbit h = new Hobbit();
		Elf e = new Elf();
		SpawnPointCell sp = new SpawnPointCell();
		RouteCell rc = new RouteCell();
		sp.enter(h);
		sp.enter(e);
		sp.leave(e);
		rc.enter(e);
	}
	
	public static void runTowerTestCase() {
		Dwarf d = new Dwarf();
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
	}
	
}
