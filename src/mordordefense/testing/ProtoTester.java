package mordordefense.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import mordordefense.Controller;
import mordordefense.Tower;
import mordordefense.Trap;

public class ProtoTester {

	public static void TestCase1() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		sinterp.interpret("input test1.txt");
	}

	public static void TestCase2() {
		// Ez a kiadott kötelező teszt.
		Controller cont = new Controller("6_2_es.p");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		Tower.setBaseCost(10);
		sinterp.interpret("input test2.txt");
	}

	public static void TestCase3() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 2;
		Tower.setBaseCost(10);
		sinterp.interpret("input test3.txt");
	}

	public static void TestCase4() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Trap.globalStrength = 1;
		Trap.setBaseCost(10);
		sinterp.interpret("input test4.txt");
	}

	public static void TestCaseMainLoop() {
		Controller cont = new Controller("palya1.p");
		cont.init();
		cont.startMainLoop();
	}

	public static void TestCase5() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		Tower.setBaseCost(10);
		sinterp.interpret("input test5.txt");

	}

	/**
	 * Ezzel a tesztesettel tetszőleges, kézzel megadott parancsokat lehet
	 * tesztelni.
	 */
	public static void TestCaseManualInput() {
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		Tower.setBaseCost(10);
		Trap.globalStrength = 1;
		Trap.setBaseCost(10);
		System.out
				.println("Parancssor módba váltottunk, q-val visszatérsz az előzőbe.");
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String in = null;
			try {
				System.out.print("$: ");
				in = br.readLine();
				if (in == null || in.equals("q")) {
					break;
				}
				sinterp.interpret(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void mainTestingEnvironment() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		endless: while (true) {
			System.out.println("\nMelyik tesztesetet futtassam?");
			int value = 0;
			String in = "";
			try {
				in = br.readLine();
				value = Integer.parseInt(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				if (in == null || in.equalsIgnoreCase("q")) {
					break endless;
					// Gyönyörű szép one-liner
				} else if (Arrays.asList(
						(new String[] { "cmd", "command", "manual" }))
						.contains(in)) {
					TestCaseManualInput();
					continue endless;
				} else if (in.toLowerCase().startsWith("loglevel")) {
					String[] cmd = in.split(" ");
					if (cmd.length >= 2) {
						int l = Integer.parseInt(cmd[1]);
						Logging.setLogLevel(l);
						continue endless;
					} else {
						value = 0;
					}
				} else {
					value = 0;
				}
			}
			switch (value) {
			case 0:
				System.out.println("Rossz teszteset");
				break;
			case 1:
				TestCase1();
				break;
			case 2:
				TestCase2();
				break;
			case 3:
				TestCase3();
				break;
			case 4:
				TestCase4();
				break;
			case 5:
				TestCase5();
				break;
			default:
				break;
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
