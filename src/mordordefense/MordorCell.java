package mordordefense;

import mordordefense.testing.Logging;

public class MordorCell extends RouteCell {
	
	public MordorCell(){
		super();
		Logging.log(">>MordorCell konstruktor hívása.");
		
	}
	
	public String getType() {
		return "MordorCell";
	}
}
