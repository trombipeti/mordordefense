package mordordefense.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mordordefense.Controller;
import mordordefense.Tower;
import mordordefense.Trap;

public class ProtoTester {

	public static void TestCase2() {
		// Ez a kiadott kötelező teszt.
		Controller cont = new Controller("6_2_es.p");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		sinterp.interpret("input test2.txt");
	}

	public static void TestCase3() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		sinterp.interpret("input test3.txt");
	}

	public static void TestCase4() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Trap.globalStrength = 1;
		sinterp.interpret("input test4.txt");
	}

	public static void TestCaseMainLoop() {
		Controller cont = new Controller("palya1.p");
		cont.init();
		cont.startMainLoop();
	}

	public static void TestCase5() {

	}

	public static void mainTestingEnvironment() {

		boolean end = false;
		endless: while (!end) {
			System.out.println("\nMelyik tesztesetet futtassam?");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			int value = 0;
			String in = "";
			try {
				in = br.readLine();
				value = Integer.parseInt(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				if (in.equalsIgnoreCase("q")) {
					end = true;
					break endless;
				}
			}
			switch (value) {
			case 0:
				System.out.println("Rossz teszteset");
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
	}
}
