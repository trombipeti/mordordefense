package mordordefense;

import mordordefense.testing.Logging;

public class FieldCell extends Cell {
	/** Associations */
	private Tower tower;

	public FieldCell(int x,int y){
		super(x,y);
	}
	
	/**
	 * Operation
	 * 
	 * @param t
	 */
	public void addTower(Tower t) {
		Logging.log(">> FieldCell.addTower függvényhívás, paraméter: "+t.toString());
		if(tower == null) {
			tower = t;
			t.setParentCell(this);
		}
		Logging.log("<<FieldCell.addTower return: void");
	}

	@Override
	public String getType() {
		return "FieldCell";
	}

}