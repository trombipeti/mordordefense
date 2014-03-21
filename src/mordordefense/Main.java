package mordordefense;

import mordordefense.testing.Logging;
import mordordefense.testing.SkeletonTester;

public class Main {

	public static void main(String[] args) {

		// Itt kell beállítani majd, hogy hova logoljon.
		Logging.setLogFileName(null);
		SkeletonTester.runTowerTestCase();
	}

}