package mordordefense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import mordordefense.testing.Logging;
import mordordefense.testing.SkeletonTester;

public class Main {

	private static TreeMap<Integer, Runnable> testCases = new TreeMap<Integer, Runnable>();
	
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
		
		testCases.put(2, new Runnable() {
			
			@Override
			public void run() {
				SkeletonTester.runSpawnTestCase();
			}
			
			public String toString() {
				return "spawnPointTestCase";
			}
		});
	}

	private static int askForTestCase() {
		int ret = -1;
		if(ranTestCases.size() == testCases.size()) {
			return -1;
		}
		System.out.println("Melyik tesztesetet futtassam? :");
		for (Integer i : testCases.keySet()) {
			if(! ranTestCases.containsKey(i)) {
				System.out.println("\t " + i + ": " + testCases.get(i).toString());
			}
		}
		System.out.println("\t q: kilepes");
		boolean read_ok = false;
		
		while (!read_ok) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String in = br.readLine();
				if(in.equals("q")) {
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
		while(! ex) {
			int run = askForTestCase();
			if(run == -1) {
				ex = true;
				break;
			}
			testCases.get(run).run();
			ranTestCases.put(run, true);
		}

	}

}