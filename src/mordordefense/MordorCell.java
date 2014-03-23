package mordordefense;

import mordordefense.testing.Logging;

public class MordorCell extends RouteCell {
	
	public MordorCell(int x, int y){
		super(x,y);
		Logging.log(">>MordorCell konstruktor hívás, x: "+x+" y: "+y);
	}
	
	public String getType() {
		return "MordorCell";
	}
}
