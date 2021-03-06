package mordordefense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mordordefense.drawing.MordorFrame;
import mordordefense.testing.Logging;
import mordordefense.testing.SkeletonTester;

public class Main {

	private static TreeMap<Integer, Runnable> testCases = new TreeMap<Integer, Runnable>();

	private static TreeMap<Integer, Boolean> ranTestCases = new TreeMap<Integer, Boolean>();

	@SuppressWarnings("unused")
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

		testCases.put(3, new Runnable() {

			@Override
			public void run() {
				SkeletonTester.runMordorTestCase();
			}

			@Override
			public String toString() {
				return "mordorTestCase";
			}
		});

		testCases.put(4, new Runnable() {

			@Override
			public void run() {
				SkeletonTester.runControllerTestCase();
			}

			@Override
			public String toString() {
				return "controllerTestCase";
			}
		});

		testCases.put(5, new Runnable() {

			@Override
			public void run() {
				SkeletonTester.runTrapTestCase();
			}

			@Override
			public String toString() {
				return "trapTestCase";
			}
		});

	}

	@SuppressWarnings("unused")
	private static int askForTestCase() {
		int ret = -1;
		if (ranTestCases.size() == testCases.size()) {
			return -1;
		}
		System.out.println("Melyik tesztesetet futtassam? :");
		for (Integer i : testCases.keySet()) {
			// if (!ranTestCases.containsKey(i)) {
			System.out.println("\t " + i + ": " + testCases.get(i).toString());
			// }
		}
		System.out.println("\t q: kilepes");
		boolean read_ok = false;

		while (!read_ok) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String in = br.readLine();
				if (in.equals("q")) {
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

	private static void printUsage() {
		System.out
				.println("Használat:\n\tjava mordordefense.Main [kapcsoló=érték]*");
		System.out.println("Kapcsoló lehet:");
		System.out.println("\t -h, --help: Ezen súgó kiíratása és kilépés.");
		System.out
				.println("\t -l, --loglevel SZÁM: Naplózás szintjét SZÁM-ra állítja.");
	}

	public static void main(String[] args) {

		// Itt kell beállítani majd, hogy hova logoljon.
		Logging.setLogFileName(null);
		Logging.setLogLevel(2);
		
		if (args.length >= 1) {
			if (args[0].equals("-h") || args[0].equals("--help")
					|| args.length == 1) {
				printUsage();
				System.exit(0);
			}
			if (args.length >= 2) {
				if (args[0].equals("-l") || args[0].equals("--loglevel")) {
					try {
						int level = Integer.parseInt(args[1]);
						Logging.setLogLevel(level);
					} catch (NumberFormatException e) {
						printUsage();
						System.exit(-1);
					}
				}
			}
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		MordorFrame mf = new MordorFrame(new Controller("palya1.p"));
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setVisible(true);

		Controller.timeStep = 100;
		Controller.setRandom(true);
		Tower.globalSlice = false;
	}
}
