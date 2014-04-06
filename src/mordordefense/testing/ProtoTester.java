package mordordefense.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mordordefense.Controller;
import mordordefense.Enemy;
import mordordefense.Tower;

public class ProtoTester {

	public static void TestCase2() {
		// Ez a kiadott kötelező teszt.
		Controller cont = new Controller("6_2_es.p");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		sinterp.interpret("input test2.txt");
		String s = cont.getTower(0).getClosestCellsWithEnemy().toString();
		System.out.println(cont.getTower(0).toString());
		System.out.print(s);
	}

	public static void TestCase3() {
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		Tower.globalDamage = 1;
		Tower.globalFreq = 1;
		Tower.globalRadius = 1;
		sinterp.interpret("input test3.txt");
	}

	public static void mainTestingEnvironment() {

		System.out.println("Melyik tesztesetet futtassam?");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int value = 0;
		try {
			String in = br.readLine();
			value = Integer.parseInt(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		default:
			break;
		}
	}
}
