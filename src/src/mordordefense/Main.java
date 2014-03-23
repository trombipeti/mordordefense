package mordordefense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import mordordefense.testing.Logging;
import mordordefense.testing.SkeletonTester;

public class Main {

	private static TreeMap<Integer, Runnable> testCases = new TreeMap<Integer, Runnable>();
<<<<<<< HEAD
	
=======

>>>>>>> origin/master
	private static TreeMap<Integer, Boolean> ranTestCases = new TreeMap<Integer, Boolean>();

	private static void setupTestCases() {
		testCases.put(1, new Runnable() {

			@Override
			public void run() {
				SkeletonTester.runTowerTestCase();
			}

			public String toString() {
				return "towerTestCase";
			}
		});
<<<<<<< HEAD
		
		testCases.put(2, new Runnable() {
			
=======

		testCases.put(2, new Runnable() {

>>>>>>> origin/master
			@Override
			public void run() {
				SkeletonTester.runSpawnTestCase();
			}
<<<<<<< HEAD
			
=======

>>>>>>> origin/master
			public String toString() {
				return "spawnPointTestCase";
			}
		});
	}

	private static int askForTestCase() {
		int ret = -1;
<<<<<<< HEAD
		if(ranTestCases.size() == testCases.size()) {
=======
		if (ranTestCases.size() == testCases.size()) {
>>>>>>> origin/master
			return -1;
		}
		System.out.println("Melyik tesztesetet futtassam? :");
		for (Integer i : testCases.keySet()) {
<<<<<<< HEAD
			if(! ranTestCases.containsKey(i)) {
				System.out.println("\t " + i + ": " + testCases.get(i).toString());
=======
			if (!ranTestCases.containsKey(i)) {
				System.out.println("\t " + i + ": "
						+ testCases.get(i).toString());
>>>>>>> origin/master
			}
		}
		System.out.println("\t q: kilepes");
		boolean read_ok = false;
<<<<<<< HEAD
		
=======

>>>>>>> origin/master
		while (!read_ok) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String in = br.readLine();
<<<<<<< HEAD
				if(in.equals("q")) {
=======
				if (in.equals("q")) {
>>>>>>> origin/master
					return -1;
				}
				int n = Integer.parseInt(in);
				if (n < 0 || n > testCases.size()) {
					System.out.println("A számnak 1 és " + testCases.size()
							+ "közt kell lennie!");
				} else {
					read_ok = true;
					ret = n;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static void main(String[] args) {

		// Itt kell beállítani majd, hogy hova logoljon.
		Logging.setLogFileName(null);
		setupTestCases();
		boolean ex = false;
<<<<<<< HEAD
		while(! ex) {
			int run = askForTestCase();
			if(run == -1) {
=======
		while (!ex) {
			int run = askForTestCase();
			if (run == -1) {
>>>>>>> origin/master
				ex = true;
				break;
			}
			testCases.get(run).run();
			ranTestCases.put(run, true);
		}

	}

}