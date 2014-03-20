package mordordefense;

public interface RouteCellListener {
	/**
	 * Operation
	 * 
	 * @param sender
	 * @param e
	 */
	public void onEnter(RouteCell sender, Elf e);

	/**
	 * Operation
	 * 
	 * @param sender
	 * @param d
	 */
	public void onEnter(RouteCell sender, Dwarf d);

	/**
	 * Operation
	 * 
	 * @param sender
	 * @param h
	 */
	public void onEnter(RouteCell sender, Hobbit h);

	/**
	 * Operation
	 * 
	 * @param sender
	 * @param h
	 */
	public void onEnter(RouteCell sender, Human h);

	/**
	 * Operation
	 * 
	 * @param sender
	 * @param e
	 */
	public void onLeave(RouteCell sender, Elf e);

	/**
	 * Operation
	 * 
	 * @param sender
	 * @param d
	 */
	public void onLeave(RouteCell sender, Dwarf d);

	/**
	 * Operation
	 * 
	 * @param sender
	 * @param h
	 */
	public void onLeave(RouteCell sender, Hobbit h);
	
	/**
	 * Operation
	 * 
	 * @param sender
	 * @param h
	 */
	public void onLeave(RouteCell sender, Human h)
}