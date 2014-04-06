package mordordefense.testing;

import mordordefense.Controller;
import mordordefense.Enemy;

public class ProtoTester {
	
	public static void TestCase1(){
		//Ez a kiadott kötelező teszt.
		Controller cont = new Controller("");
		ScriptInterpreter sinterp = new ScriptInterpreter(cont);
		sinterp.interpret("input test1.txt");
	}

	public static void mainTestingEnvironment() {
		
	}
}
