package mordordefense.testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mordordefense.Controller;
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

	public void interpret(String s) {
		String[] parts = s.split(" ");
		// Input értelmező else-if

		if (parts[0] == "input") {
			// Beolvasandó parancsok file-jának beállítása
		} else if (parts[0] == "output") {
			// Logger file-ba való logolásának beállítása
			if (parts[1] == null)
				Logging.setLogFileName(null);
			else
				Logging.setLogFileName(parts[1]);
		} else if (parts[0] == "random") {
			// Determinisztikus működés megválasztása
			if (parts[1] == "on")
				cont.setRandom(true);
			else
				cont.setRandom(false);
		} else if (parts[0] == "help") {
			// Help file-ban található commandok és leírásuk kilistázása
			try {
				BufferedReader reader = new BufferedReader(new FileReader("help.txt"));
				try {
					String line;
					while ((line = reader.readLine()) != null)
						System.out.println(line);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					reader.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}

		} else if (parts[0] == "quit") {
			// TODO Program futásának megállítása
		} else if (parts[0] == "start") {
			simulationStarted = true;
			cont.setMapFileName(parts[2]);
			if (parts[1] == "1") {
				stepSimulation = true;
			} else {
				stepSimulation = false;
				cont.init();
				cont.run();
			}

		} else if (parts[0] == "step") {
			// Lépéses szimuláció esetén ezzel a parancssal léptetünk
			if (simulationStarted) {
				if (stepSimulation)
					cont.stepAllEnemies();
			}
		} else if (parts[0] == "tower") {
			// Tower lerakása X:Y koordinátákra
			int x = Integer.parseInt(parts[1]);
			int y = Integer.parseInt(parts[2]);
			cont.placeTower(new Tower(), x, y);
		} else if (parts[0] == "trap") {
			int x = Integer.parseInt(parts[1]);
			int y = Integer.parseInt(parts[2]);
			cont.placeTrap(new Trap(), x, y);
		} else if (parts[0] == "magicstone") {
			if (parts[1] == "tower") {
				cont.getTower(Integer.valueOf(parts[2])).addStone(
						new MagicStone(Float.valueOf(parts[3]), Float
								.valueOf(parts[4]), Float.valueOf(parts[5]),
								Float.valueOf(parts[6]), Float
										.valueOf(parts[7]), Float
										.valueOf(parts[8]), Float
										.valueOf(parts[9])));
			} else if (parts[1] == "trap") {
				cont.getTrap(Integer.valueOf(parts[2])).addStone(
						new MagicStone(Float.valueOf(parts[3]), Float
								.valueOf(parts[4]), Float.valueOf(parts[5]),
								Float.valueOf(parts[6]), Float
										.valueOf(parts[7]), Float
										.valueOf(parts[8]), Float
										.valueOf(parts[9])));
			}
		} else {
			System.out.println("Nem valid Bementei parancs!");
		}
	}
}
