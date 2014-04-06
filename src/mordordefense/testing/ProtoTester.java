package mordordefense.testing;

import mordordefense.Controller;

public class ProtoTester {

	public static void mainTestingEnvironment(){
		Controller cont=new Controller("");
		ScriptInterpreter sinterp=new ScriptInterpreter(cont);
		sinterp.interpret("input test1.txt");
	}
}
