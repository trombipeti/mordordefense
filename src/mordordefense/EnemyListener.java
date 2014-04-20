package mordordefense;

/**
 * Az enemykkel történő eseményekről értesítő interfész.
 * 
 */
public interface EnemyListener {
	/**
	 * Kettévágós típusú lövedék esetén létrejött új enemyről értesítő függvény.
	 * 
	 * @param e
	 *            A kettévágás során létrejött új {@link Enemy}
	 */
	public void onSlice(Enemy e);

	/**
	 * Egy enemy meghalásakor meghívódó függvény.
	 * 
	 * @param e
	 *            Az elhunyt hős ellenség.
	 */
	public void onDie(Enemy e);

}
