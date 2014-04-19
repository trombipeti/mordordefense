package mordordefense.testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mordordefense.Controller;
import mordordefense.Dwarf;
import mordordefense.Elf;
import mordordefense.Hobbit;
import mordordefense.Human;
import mordordefense.MagicStone;
import mordordefense.Tower;
import mordordefense.Trap;

public class ScriptInterpreter {

	private Controller cont;
	private boolean stepSimulation;
	private boolean simulationStarted;

	public ScriptInterpreter(Controller c) {
		cont = c;
		stepSimulation = false;
		simulationStarted = false;
	}

	public void inputFromFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			try {
				String line;
				while ((line = br.readLine()) != null)
					interpret(line);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				br.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Értelmezi a paraméterként kapott parancsot. Ha a paraméter üres vagy
	 * null, nem csinál semmit.
	 * 
	 * @param s
	 *            Az értelmezendő parancs.
	 */
	public void interpret(String s) {
		if (s == null || s.isEmpty()) {
			return;
		}
		String[] parts = s.split(" ");

		// Input értelmező else-if
		try {
			if (parts[0].equalsIgnoreCase("input")) {
				// Beolvasandó parancsok file-jának beállítása
				if (parts.length == 2) {
					inputFromFile(parts[1]);
				} else {
					throw new Exception(
							"!! Nem megfelelo az argumentumok szama");
				}
			} else if (parts[0].equalsIgnoreCase("output")) {
				// ---------------- Logger file beállítása----------------
				if (parts.length == 1)
					Logging.setLogFileName(null);
				else if (parts.length == 2)
					Logging.setLogFileName(parts[1]);
				else
					throw new Exception(
							"!! Nem megfelelo az argumentumok szama");
			} else if (parts[0].equalsIgnoreCase("random")) {
				// ---------------- Random működés megválasztása----------------
				if (parts.length == 2) {
					if (parts[1] == "on")
						cont.setRandom(true);
					else
						cont.setRandom(false);
				} else {
					throw new Exception(
							"!!Nem megfelelo az argumentumok szama!");
				}

			} else if (parts[0].equalsIgnoreCase("help")) {
				// ---------------- Parancsok kiírása----------------
				// try {
				BufferedReader reader = new BufferedReader(new FileReader(
						"help.txt"));
				try {
					String line;
					while ((line = reader.readLine()) != null)
						System.out.println(line);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					reader.close();
				}
				/*
				 * } catch (Exception e) {
				 * 
				 * e.printStackTrace(); }
				 */

			} else if (parts[0].equalsIgnoreCase("quit")) {
				System.gc();
				System.exit(0);
			} else if (parts[0].equalsIgnoreCase("start")) {
				// ---------------- Program indító ----------------
				// try {
				if (parts.length == 3) {
					simulationStarted = true;
					cont.setMapFileName(parts[2]);
					cont.init();
					if (parts[1].equalsIgnoreCase("1")) {
						stepSimulation = true;
					} else {
						stepSimulation = false;
						cont.startMainLoop();
					}
				} else
					throw new Exception("!!Az argumentumok szama nem megfelelo");
				/*
				 * } catch (Exception e) { e.printStackTrace(); }
				 */

			} else if (parts[0].equalsIgnoreCase("step")) {
				// ---------------- Léptetés ----------------
				if (simulationStarted) {
					if (stepSimulation)
						cont.stepAllEnemies();
					else
						throw new Exception(
								">>!! Nem leptetos modban fut a jatek");
				} else
					throw new Exception(">>!! Nem fut a jatek");

			} else if (parts[0].equalsIgnoreCase("tower")) {
				// ---------------- Tower lerakása X,Y
				// koordinátákra----------------
				// try {
				if (parts.length == 3) {
					int x = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					cont.placeTower(new Tower(), x, y);
				} else
					throw new Exception("Nem valid a bemenet");
				/*
				 * } catch (Exception e) { e.printStackTrace(); }
				 */

			} else if (parts[0].equalsIgnoreCase("trap")) {
				// Trap lerakása X,Y koordinátákra
				// try {
				if (parts.length == 3) {
					int x = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					cont.placeTrap(new Trap(), x, y);
				} else
					throw new Exception("Nem valid a bemenet");
				/*
				 * } catch (Exception e) { e.printStackTrace(); }
				 */

			} else if (parts[0].equalsIgnoreCase("magicstone")) {
				// ---------------- MagicStone hozzáadás----------------
				// try {
				if (parts.length == 10) {
					if (parts[1].equalsIgnoreCase("tower")) {
						cont.getTower(Integer.valueOf(parts[2])).addStone(
								new MagicStone(Float.parseFloat(parts[3]),
										Float.parseFloat(parts[4]), Float
												.parseFloat(parts[5]), Float
												.parseFloat(parts[6]), Float
												.parseFloat(parts[7]), Float
												.parseFloat(parts[8]), Float
												.parseFloat(parts[9])));
					} else if (parts[1].equalsIgnoreCase("trap")) {
						cont.getTrap(Integer.valueOf(parts[2])).addStone(
								new MagicStone(Float.parseFloat(parts[3]),
										Float.parseFloat(parts[4]), Float
												.parseFloat(parts[5]), Float
												.parseFloat(parts[6]), Float
												.parseFloat(parts[7]), Float
												.parseFloat(parts[8]), Float
												.parseFloat(parts[9])));
					}
				}

				/*
				 * } catch (Exception e) { e.printStackTrace(); }
				 */

			} else if (parts[0].equalsIgnoreCase("enemy")) {
				if (parts.length == 4) {
					if (parts[1].equalsIgnoreCase("d"))
						cont.addDwarf(new Dwarf(Integer.parseInt(parts[2]),
								Integer.parseInt(parts[3])));
					else if (parts[1].equalsIgnoreCase("e"))
						cont.addElf(new Elf(Integer.parseInt(parts[2]), Integer
								.parseInt(parts[3])));
					else if (parts[1].equalsIgnoreCase("hu"))
						cont.addHuman(new Human(Integer.parseInt(parts[2]),
								Integer.parseInt(parts[3])));
					else if (parts[1].equalsIgnoreCase("ho"))
						cont.addHobbit(new Hobbit(Integer.parseInt(parts[2]),
								Integer.parseInt(parts[3])));
				}

			} else if (parts[0].equalsIgnoreCase("fog")) {
				cont.getTower(Integer.parseInt(parts[1])).addFog(
						Integer.parseInt(parts[2]));
			} else {
				System.out.println("Nem valid Bementei parancs!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			interpret("quit");

		}
	}
}
