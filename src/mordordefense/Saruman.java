package mordordefense;

import mordordefense.testing.Logging;

/**
 * A Mordort hősiesen védő Szarumánt megtestesítő osztály.
 * 
 */
public class Saruman {

	/**
	 * Szarumán varázsereje.
	 */
	private int manna;

	/**
	 * Szarumán konstruktora
	 * 
	 * @param mana
	 */
	public Saruman(int mana) {
		Logging.log("Saruman konstruktor hívás, paraméter: " + mana);
		manna = mana;
	}

	/**
	 * Szarumán varázserejét növelő függvény.
	 * 
	 * @param m
	 *            A hozzáadódó varázserő
	 */
	public void addManna(int m) {
		Logging.log(">> Saruman.addManna() hívás, paraméter: " + m);
		manna += m;
	}

	/**
	 * Szarumán mannáját lekérdező függvény.
	 * 
	 * @return Szarumán varázsereje.
	 */
	public int getManna() {
		Logging.log(">> Saruman.getManna() hívás.");
		Logging.log("<< " + manna);
		return manna;
	}

	/**
	 * Szarumán varázserejét csökkentő függvény.
	 * 
	 * @param m
	 *            Mennyivel csökkenjen a varázserő.
	 */
	public void rmManna(int m) {
		Logging.log(">> Saruman.rmManna() hívás, paraméter: " + m);
		manna -= m;
		Logging.log("\t Szarumán új varázserej: " + manna);
	}
}
