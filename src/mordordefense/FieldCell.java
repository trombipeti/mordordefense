package mordordefense;

import mordordefense.testing.Logging;

/**
 * A nem-út cellákat megtestesítő Cell leszármazott osztály. Csak ilyen típusú
 * cellára lehet tornyot elhelyezni.
 * 
 */
public class FieldCell extends Cell {

	/**
	 * A mezőn lévő torony. Ha nincs rajta, akkor null.
	 * 
	 * @see Tower
	 */
	private Tower tower;

	/**
	 * @see Cell
	 */
	public FieldCell(int x, int y) {
		super(x, y);
		Logging.log(2, ">> FieldCell konstruktor hívás");
		tower = null;
		Logging.log(4, "<< FieldCell konstruktor");
	}

	/**
	 * A FieldCell-re tornyot elhelyező függvény
	 * 
	 * @see Tower
	 * @param t
	 *            A lerakandó torony.
	 */
	public boolean addTower(Tower t) {
		Logging.log(2, ">> FieldCell.addTower függvényhívás, paraméter: "
				+ t.toString());
		if (tower == null) {
			tower = t;
			t.setParentCell(this);
			Logging.log(2, "<< FieldCell.addTower return: boolean - true");
			return true;
		} else {
			Logging.log(2, "<< FieldCell.addTower return: boolean - false");
			return false;
		}
	}

	@Override
	public String getType() {
		return "FieldCell";
	}

}