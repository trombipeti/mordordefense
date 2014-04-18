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
		Logging.log(2, ">> Saruman konstruktor hívás, paraméter: " + mana);
		manna = mana;
		Logging.log(4, "<< Saruman konstruktor");
	}

	/**
	 * Szarumán varázserejét növelő függvény.
	 * 
	 * @param m
	 *            A hozzáadódó varázserő
	 */
	public void addManna(int m) {
		Logging.log(1, ">> Saruman.addManna() hívás, paraméter: " + m);
		manna += m;
		Logging.log(4, "<< Saruman.addManna");
	}

	/**
	 * Szarumán mannáját lekérdező függvény.
	 * 
	 * @return Szarumán varázsereje.
	 */
	public int getManna() {
		Logging.log(3, ">> Saruman.getManna() hívás.");
		Logging.log(3, "<< " + manna);
		return manna;
	}

	/**
	 * Szarumán varázserejét csökkentő függvény.
	 * 
	 * @param m
	 *            Mennyivel csökkenjen a varázserő.
	 */
	public void rmManna(int m) {
		Logging.log(1, ">> Saruman.rmManna() hívás, paraméter: " + m);
		manna -= m;
		Logging.log(3, "\t Szarumán új varázserej: " + manna);
		Logging.log(4, "<< Saruman.rmManna");
	}

	@Override
	public String toString() {
		return "Saruman, manna: " + manna;
	}
}
