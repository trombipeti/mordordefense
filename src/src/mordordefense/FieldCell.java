package mordordefense;

import mordordefense.testing.Logging;

<<<<<<< HEAD
=======
/**
 * A nem-út cellákat megtestesítő Cell leszármazott osztály. Csak ilyen típusú
 * cellára lehet tornyot elhelyezni.
 * 
 */
>>>>>>> origin/master
public class FieldCell extends Cell {

	/**
	 * A mezőn lévő torony. Ha nincs rajta, akkor null.
	 * 
	 * @see Tower
	 */
	private Tower tower;

<<<<<<< HEAD
	public FieldCell(int x,int y){
		super(x,y);
	}
	
=======
	/**
	 * @see Cell
	 */
	public FieldCell(int x, int y) {
		super(x, y);
		Logging.log(">> FieldCell konstruktor hívás");
		tower = null;
	}

>>>>>>> origin/master
	/**
	 * A FieldCell-re tornyot elhelyező függvény
	 * 
	 * @see Tower
	 * @param t
	 *            A lerakandó torony.
	 */
	public void addTower(Tower t) {
<<<<<<< HEAD
		Logging.log(">> FieldCell.addTower függvényhívás, paraméter: "+t.toString());
		if(tower == null) {
=======
		Logging.log(">> FieldCell.addTower függvényhívás, paraméter: "
				+ t.toString());
		if (tower == null) {
>>>>>>> origin/master
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