package mordordefense;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import sun.org.mozilla.javascript.ast.Loop;

import mordordefense.Cell.Dir;
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * A játékot vezérlő "játékmester" objektum.
 * 
 */
public class Controller implements RouteCellListener, EnemyListener {

	/**
	 * Mordor koordinátái.
	 * 
	 * @see MordorCell
	 */
	public int[] mordorCoords;

	/**
	 * Az indulópont koordinátái.
	 * 
	 * @see SpawnPointCell
	 */
	public int[] spawnCoords;

	/**
	 * A térkép szélessége. Ennyi mező van y irányban, viszont 0-tól
	 * indexelődnek, tehát a bal szélső cellék x koordinátája 0, a jobb szélsőké
	 * pedig mapWidth - 1.
	 */
	private int mapWidth;

	/**
	 * A térkép magassága. Ennyi mező van y irányban, viszont 0-tól
	 * indexelődnek, tehát a legfölső cellák y koordinátája 0, a legalsóké pedig
	 * mapHeight - 1.
	 */
	private int mapHeight;

	/**
	 * Maximum hány enemy indulhat el.
	 */
	private int maxEnemyNum;

	/**
	 * Az eddig harcba küldött ellenségek.
	 */
	private int sentEnemies;

	/**
	 * A pályán lévő csapdák
	 */
	private List<Trap> traps = new ArrayList<Trap>();

	/**
	 * A pályán lévő tornyok
	 */
	private List<Tower> towers = new ArrayList<Tower>();

	/**
	 * Szarumán
	 */
	private Saruman saruman;

	/**
	 * A pályán lévő ellenségek
	 */
	private HashSet<Enemy> enemies = new HashSet<Enemy>();

	/**
	 * A pályán lévő cellák. A map kulcsaihoz újabb map-ek vannak rendelve (a
	 * sorok), és ezeken belül vannak a cellák. Inicializáláskor N*M-esre lesz
	 * inicializálva, null-okkal feltöltve.
	 * 
	 * Példa:
	 * 
	 * A (3,2) koordinátájú cella elérése: cells.get(3).get(2);
	 * 
	 * A (4,1) koordinára új cella beállítása: cells.get(4).put(1, Cell);
	 */
	private TreeMap<Integer, TreeMap<Integer, Cell>> cells = new TreeMap<Integer, TreeMap<Integer, Cell>>();

	/**
	 * Azt tárolja, hogy véget ért-e a játék.
	 */
	private volatile boolean gameEnded = false;

	/**
	 * A győzteseket tároló StringBuffer. Azért ilyen típusú, hogy szálbiztos
	 * legyen.
	 */
	private volatile StringBuffer winner = null;

	/**
	 * A pálya adatait tartalmazó fájl neve.
	 */
	private String mapFileName;

	/**
	 * A controller véletlenszerűen indítja-e az enemyket stb.
	 */
	private boolean random;

	/**
	 * Konstruktor
	 * 
	 * @param n
	 *            hány ellenséget tehet le
	 */
	public Controller(int n, String fileName) {
		Logging.log(3, ">> Controller konstruktor hívás, paraméter:" + n);
		maxEnemyNum = n;
		sentEnemies = 0;
		mapFileName = fileName;
		Logging.log(4, "<< Controller konstruktor");
	}

	/**
	 * Konstruktor
	 * 
	 * @param fileName
	 *            A pálya leírását tartalmazó fájl neve.
	 */
	public Controller(String fileName) {
		Logging.log(3, ">> Controller konstruktor hívás, paraméter:" + fileName);
		mapFileName = fileName;
		// TODO valahonnan fájlból kéne beolvasni a következő értékeket!!!
		Human.defMaxLP = 10;
		Human.defSpeed = 4;

		Hobbit.defMaxLP = 8;
		Hobbit.defSpeed = 2;

		Elf.defMaxLP = 10;
		Elf.defSpeed = 4;

		Dwarf.defMaxLP = 11;
		Dwarf.defSpeed = 6;
		Logging.log(4, "<< Controller konstruktor");
	}

	/**
	 * Inicializáló függvény. Beolvassa a pályafájlból a pályát és fölépíti azt.
	 */
	public void init() {
		Logging.log(2, ">> Controller.init() hívás");
		saruman = new Saruman(100);
		sentEnemies = 0;
		spawnCoords = new int[] { 0, 0 }; // Szép is ez a Java nyelv :D
		mordorCoords = new int[] { 0, 0 };
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapFileName));
			try {
				int partno = 0;
				String line = null;
				ReadIn: while (true) {
					line = br.readLine();
					if (line == null) {
						break ReadIn;
					}
					if (line.startsWith("-")) {
						partno++;
						continue ReadIn;
					}
					String[] sp = line.split(";");
					switch (partno) {
					case 0: // A map dimezióit megadó részben vagyunk
						if (sp.length >= 2) {
							// n*m-es lesz a pálya
							mapWidth = Integer.parseInt(sp[0]);
							mapHeight = Integer.parseInt(sp[1]);
							// minden mezőtt FieldCell-re inicializálunk, ha
							// valami nem az, majd felülírjuk
							for (int i = 0; i < mapWidth; ++i) {
								cells.put(i, new TreeMap<Integer, Cell>());
								for (int j = 0; j < mapHeight; ++j) {
									cells.get(i).put(j, new FieldCell(i, j));
								}
							}
						}
						break;
					case 1: // A maxEnemyNum-ot megadó részen vagyunk.
						if (sp.length >= 1) {
							maxEnemyNum = Integer.parseInt(sp[0]);
						}
						break;
					case 2: // A SpawnPoint/Mordor helyét megadó részben vagyunk
						if (sp.length >= 4) {
							if (sp[0].equalsIgnoreCase("S")) {
								// Ha a SpawnPoint helyét adta meg
								int sx = Integer.parseInt(sp[1]);
								int sy = Integer.parseInt(sp[2]);
								int id = Integer.parseInt(sp[3]);
								cells.get(sx).put(sy,
										new SpawnPointCell(sx, sy, id));
								spawnCoords[0] = sx;
								spawnCoords[1] = sy;

							} else if (sp[0].equalsIgnoreCase("M")) {
								// Ha a MordorCell helyét adta meg
								int mx = Integer.parseInt(sp[1]);
								int my = Integer.parseInt(sp[2]);
								int id = Integer.parseInt(sp[3]);
								// Feliratkozunk a MordorCell-re, hogy tudjuk,
								// mikor érnek oda az enemyk
								MordorCell mc = new MordorCell(mx, my, id);
								mc.addRouteCellListener(this);
								cells.get(mx).put(my, mc);
								mordorCoords[0] = mx;
								mordorCoords[1] = my;
							}
						}
						break;
					case 3: // A RouteCell-ek helyét és ID-jét megadó részben
							// vagyunk
						if (sp.length >= 3) {
							int rx = Integer.parseInt(sp[0]);
							int ry = Integer.parseInt(sp[1]);
							int id = Integer.parseInt(sp[2]);
							cells.get(rx).put(ry, new RouteCell(rx, ry, id));
						}
						break;
					default:
						break;
					}
				}
				// Ez rekurzívan beállítja mindenkinek!!!
				calcSzomszedok(cells.get(0).get(0));
			} finally {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logging.log(2, "<< void Controller.init()");
	}

	/**
	 * Beállítja a paraméterül kapott cella szomszédait. Aztán meg rekurzívan
	 * mindenki másét is.
	 * 
	 * @param c
	 *            A cella.
	 */
	private void calcSzomszedok(Cell c) {
		Logging.log(
				3,
				">> Controller.calcSzomszedok() hívás, paraméter: "
						+ c.toString());

		int[] coords = c.getCoords();
		int x = coords[0];
		int y = coords[1];

		if (x == 0) { // Bal széle
			c.setSzomszed(Dir.LEFT, null);
		} else {
			c.setSzomszed(Dir.LEFT, cells.get(x - 1).get(y));
		}
		if (x == mapWidth - 1) { // Jobb széle
			c.setSzomszed(Dir.RIGHT, null);
		} else {
			c.setSzomszed(Dir.RIGHT, cells.get(x + 1).get(y));
		}
		if (y == 0) { // Teteje
			c.setSzomszed(Dir.UP, null);
		} else {
			c.setSzomszed(Dir.UP, cells.get(x).get(y - 1));
		}
		if (y == mapHeight - 1) { // Alja
			c.setSzomszed(Dir.DOWN, null);
		} else {
			c.setSzomszed(Dir.DOWN, cells.get(x).get(y + 1));
		}

		c.setNeighborsKnown(true);
		for (Cell nc : c.getSzomszedok().values()) {
			if (nc != null && !nc.isNeighborsKnown()) {
				calcSzomszedok(nc);
			}
		}

		Logging.log(4, "<< Controller.calcSzomszedok()");
	}

	private Timer scheduler = new Timer();

	/**
	 * A loop-ot lefuttató, ütemezhető {@link TimerTask}
	 */
	private TimerTask mainLoop = new TimerTask() {

		@Override
		public void run() {
			loop();
		}
	};

	/**
	 * Ütemezi a scheduler-ben 10 ms-enkénti futásra a loop TimerTask-ot.
	 */
	public void startMainLoop() {
		scheduler.scheduleAtFixedRate(mainLoop, 0, 10);
	}

	public void stopMainLoop() {
		mainLoop.cancel();
	}

	/**
	 * Az eseményeket vezérlő függvény
	 * 
	 */
	public void loop() {
		Logging.log(3, ">> Controller.loop() hívás");
		if (sentEnemies < maxEnemyNum) {
			addRandomEnemy();
			sentEnemies++;
		}
		stepAllEnemies();
		Logging.log(4, "<< Controller.loop()");
	}

	private void addRandomEnemy() {
		Logging.log(3, ">> Controller.addRandomEnemy() hívás");
		Random randgen = new Random();
		int n = randgen.nextInt(4);
		switch (n) {
		case 0:
			addHuman(new Human(Human.defMaxLP, Human.defSpeed));
			break;
		case 1:
			addElf(new Elf(Elf.defMaxLP, Elf.defSpeed));
			break;
		case 2:
			addHobbit(new Hobbit(Hobbit.defMaxLP, Hobbit.defSpeed));
			break;
		case 3:
			addDwarf(new Dwarf(Dwarf.defMaxLP, Dwarf.defSpeed));
			break;
		default:
			break;
		}
		Logging.log(4, "<< Controller.addRandomEnemy()");
	}

	public void addHuman(Human h) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(h);
		sp.enter(h);
	}

	public void addElf(Elf e) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(e);
		sp.enter(e);
	}

	public void addHobbit(Hobbit h) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(h);
		sp.enter(h);
	}

	public void addDwarf(Dwarf d) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(d);
		sp.enter(d);
	}

	public void stepAllEnemies() {
		Logging.log(2, ">> Controller.stepAllEnemies() hívás");
		Iterator<Enemy> iter = enemies.iterator();
		while (iter.hasNext()) {
			Enemy en = iter.next();
			try {
				en.leptet();
			} catch (EnemyDeadException e1) {
				Logging.log(1, "\tAz enemy meghalt: " + en.toString());
				saruman.addManna(en.getMaxLifePoint());
				iter.remove();
			} catch (EnemyCannotStepException e1) {
				Logging.log(0, "Valamiért nem tud lépni az enemy!!!");
				stopMainLoop();
				// e1.printStackTrace();
			}
		}
		Logging.log(4, "<< Controller.stepAllEnemies() hívás");
	}

	public void placeTower(Tower t, int x, int y) {
		if (cells.get(x).get(y).getType().equalsIgnoreCase("FieldCell")) {
			FieldCell fc = (FieldCell) cells.get(x).get(y);
			if (fc.addTower(t)) {
				towers.add(t);
				saruman.rmManna(Tower.getBaseCost());
			}
		} else {
			Logging.log(0, "!!! Towert nem FieldCell-re raktuk!");
		}
	}

	public void placeTrap(Trap t, int x, int y) {
		if (!cells.get(x).get(y).getType().equalsIgnoreCase("FieldCell")) {
			RouteCell rc = (RouteCell) cells.get(x).get(y);
			if (rc.addTrap(t)) {
				saruman.rmManna(Trap.getBaseCost());
				traps.add(t);
			}
		} else {
			Logging.log(0, "!!! Trapet nem RouteCell-re raktuk!");
		}

	}

	public Tower getTower(int i) {
		return towers.get(i);
	}

	public Trap getTrap(int i) {
		return traps.get(i);
	}

	public void setRandom(boolean b) {
		random = b;
	}

	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	// RouteCellListener

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(
				2,
				">> Controller.onEnter() hívás, paraméterek: "
						+ sender.toString() + ", " + e.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log(1, "Enemy nyert: " + e.toString());
			stopMainLoop();
		}
		Logging.log(4, "<< Controller.onEnter() hívás");
	}

	@Override
	public void onEnter(RouteCell sender, Dwarf d) {
		Logging.log(
				2,
				">> Controller.onEnter() hívás, paraméterek: "
						+ sender.toString() + ", " + d.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log(1, "Enemy nyert: " + d.toString());
			stopMainLoop();
		}
		Logging.log(4, "<< Controller.onEnter() hívás");
	}

	@Override
	public void onEnter(RouteCell sender, Hobbit h) {
		Logging.log(
				2,
				">> Controller.onEnter() hívás, paraméterek: "
						+ sender.toString() + ", " + h.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log(1, "Enemy nyert: " + h.toString());
			stopMainLoop();
		}
		Logging.log(4, "<< Controller.onEnter() hívás");
	}

	@Override
	public void onEnter(RouteCell sender, Human h) {
		Logging.log(
				2,
				">> Controller.onEnter() hívás, paraméterek: "
						+ sender.toString() + ", " + h.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			gameEnded = true;
			winner = new StringBuffer("enemies");
			Logging.log(1, "Enemy nyert: " + h.toString());
			stopMainLoop();
		}
		Logging.log(4, "<< Controller.onEnter() hívás");
	}

	@Override
	public void onLeave(RouteCell sender, Elf e) {
		// Logging.log(
		// 4,
		// ">> Controller.onLeave() hívás, paraméterek: "
		// + sender.toString() + ", " + e.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Dwarf d) {
		// Logging.log(
		// 4,
		// ">> Controller.onLeave() hívás, paraméterek: "
		// + sender.toString() + ", " + d.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Hobbit h) {
		// Logging.log(
		// 4,
		// ">> Controller.onLeave() hívás, paraméterek: "
		// + sender.toString() + ", " + h.toString());

	}

	@Override
	public void onLeave(RouteCell sender, Human h) {
		// Logging.log(
		// 4,
		// ">> Controller.onLeave() hívás, paraméterek: "
		// + sender.toString() + ", " + h.toString());

	}

	// EnemyListener

	@Override
	public void onSlice(Enemy e) {
		Logging.log(3,
				">> Controller.onSlice() hívás, paraméter: " + e.toString());
		e.addEnemyListener(this);
		enemies.add(e);
		Logging.log(4, "<< Controller.onSlice() hívás");
	}

	@Override
	public void onDie(Enemy e) {
		Logging.log(2,
				">> Controller.onDie() hívás, paraméter: " + e.toString());
		enemies.remove(e);
		if (sentEnemies == maxEnemyNum) {
			winner = new StringBuffer("saruman");
			Logging.log(1, "!!! Szarumán nyert !!!");
			stopMainLoop();
		}
		Logging.log(4, "<< Controller.onDie() hívás");
	}

}
