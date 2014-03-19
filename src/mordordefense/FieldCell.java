package mordordefense;

public class FieldCell extends Cell {
	/** Associations */
	private Tower tower;

	public FieldCell() {
		tower = null;
	}
	
	public FieldCell(Tower t) {
		tower = t;
	}
	
	/**
	 * Operation
	 * 
	 * @param t
	 */
	public void addTower(Tower t) {
		if(tower == null) {
			tower = t;
		}
	}

	@Override
	public String getType() {
		return "FieldCell";
	}

}