package mordordefense;

/**
 * Az utakon történő mozgásokat figyelő interfész.
 *
 */
public interface RouteCellListener {
	/**
	 * A RouteCell-re Elf belépésekor meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}re léptek be.
	 * @param e
	 *            A belépő {@link Elf}
	 */
	public void onEnter(RouteCell sender, Elf e);

	/**
	 * A RouteCell-re Dwarf belépésekor meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}re léptek be.
	 * @param d
	 *            A belépő {@link Dwarf}
	 */
	public void onEnter(RouteCell sender, Dwarf d);

	/**
	 * A RouteCell-re Hobbit belépésekor meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}re léptek be.
	 * @param e
	 *            A belépő {@link Hobbit}
	 */
	public void onEnter(RouteCell sender, Hobbit h);

	/**
	 * A RouteCell-re Human belépésekor meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}re léptek be.
	 * @param e
	 *            A belépő {@link Human}
	 */
	public void onEnter(RouteCell sender, Human h);

	/**
	 * RouteCellről kilépő Elf eseményre meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}t hagyták el.
	 * @param e
	 *            A kilépő {@link Elf}
	 */
	public void onLeave(RouteCell sender, Elf e);

	/**
	 * RouteCellről kilépő Dwarf eseményre meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}t hagyták el.
	 * @param e
	 *            A kilépő {@link Dwarf}
	 */
	public void onLeave(RouteCell sender, Dwarf d);

	/**
	 * RouteCellről kilépő Hobbit eseményre meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}t hagyták el.
	 * @param e
	 *            A kilépő {@link Hobbit}
	 */
	public void onLeave(RouteCell sender, Hobbit h);

	/**
	 * RouteCellről kilépő Human eseményre meghívódó függvény.
	 * 
	 * @param sender
	 *            Melyik {@link RouteCell}t hagyták el.
	 * @param e
	 *            A kilépő {@link Human}
	 */
	public void onLeave(RouteCell sender, Human h);
}