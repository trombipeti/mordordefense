package mordordefense;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import mordordefense.Cell.Dir;
import mordordefense.exceptions.EnemyCannotStepException;
import mordordefense.exceptions.EnemyDeadException;
import mordordefense.testing.Logging;

/**
 * A játékot vezérlő "játékmester" objektum.
 * 
 */
public class Controller implements RouteCellListener, EnemyListener {

	public static int timeStep = 50;

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
	 * Az eddig harcba küldött ellenségek.
	 */
	private int sentEnemies;

	/**
	 * Maximum hány enemy indulhat el.
	 */
	private int maxEnemyNum;

	/**
	 * @return A maximálisan elindítható ellenségek száma
	 */
	public int getMaxEnemyNum() {
		return maxEnemyNum;
	}

	/**
	 * @param maxEnemyNum
	 *            Ennyi ellenség fog tudni maximálisan elindulni.
	 */
	public void setMaxEnemyNum(int maxEnemyNum) {
		this.maxEnemyNum = maxEnemyNum;
	}

	/**
	 * az eddig meghalt ellenségek
	 */
	private int diedEnemies;

	/**
	 * A pályán lévő csapdák
	 */
	private List<Trap> traps = new ArrayList<Trap>();

	/**
	 * A pályán lévő tornyok
	 */
	private List<Tower> towers = new ArrayList<Tower>();

	/**
	 * Szarumán, ő igazából a mannatároló
	 */
	private Saruman saruman;

	/**
	 * A pályán lévő ellenségek
	 */
	private HashSet<Enemy> enemies = new HashSet<Enemy>();

	/**
	 * Az egy körben keletkezett ellenségek (pl slice hatására), akiket a
	 * léptetés után be kell majd rakni az enemies-be. Ez a szeretett
	 * {@link ConcurrentModificationException} elkerüléséhez kell.
	 */
	private HashSet<Enemy> enemiesToAdd = new HashSet<Enemy>();

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
	private volatile String winner = null;

	/**
	 * @return A győztes neve - "Szarumán", ha minden ellenség meghalt,
	 *         "Ellenségek", ha valamelyik ellenség bejutott mordorba.
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @param winner A
	 */
	private void setWinner(String winner) {
		this.winner = winner;
	}

	/**
	 * A pálya adatait tartalmazó fájl neve.
	 */
	private String mapFileName;

	/**
	 * A controller véletlenszerűen indítja-e az enemyket stb.
	 */
	private static boolean random = true;
	/**
	 * A Controller automatikusan rakhat-e le enemy-t, vagy kézzel lesznek
	 * lerakva (alap állapotban automatára van állítva)
	 * 
	 */
	private boolean canSpawn = true;
	/**
	 * a kirajzoláshoz fontos flag, amely jelzi, hogy az ellenségek
	 * változtattak-e a helyzetükön
	 */
	public volatile boolean enemyChanged = false;
	/**
	 * a kirajzoláshoz fontos flag, amely jelzi, hogy a tornyok állapota
	 * változott-e
	 */
	public volatile boolean towerChanged = false;
	/**
	 * a kirajzoláshoz fontos flag, amely jelzi, hogy a csapdák állapota
	 * változott-e
	 */
	public volatile boolean trapChanged = false;

	// /**
	// * Konstruktor
	// *
	// * @param n
	// * hány ellenséget tehet le
	// */
	// public Controller(int n, String fileName) {
	// Logging.log(3, ">> Controller konstruktor hívás, paraméter:" + n);
	// maxEnemyNum = n;
	// sentEnemies = diedEnemies = 0;
	// mapFileName = fileName;
	// Logging.log(4, "<< Controller konstruktor");
	// }

	/**
	 * Konstruktor
	 * 
	 * @param mapFile
	 *            A pálya leírását tartalmazó fájl neve.
	 */
	public Controller(String mapFile) {
		Logging.log(3, ">> Controller konstruktor hívás, paraméter:" + mapFile);
		mapFileName = mapFile;
		sentEnemies = diedEnemies = 0;
		// TODO valahonnan fájlból kéne beolvasni a következő értékeket!!!
		Human.defMaxLP = 5;
		Human.defSpeed = 0.8f;

		Hobbit.defMaxLP = 5;
		Hobbit.defSpeed = 0.8f;

		Elf.defMaxLP = 5;
		Elf.defSpeed = 0.8f;

		Dwarf.defMaxLP = 5;
		Dwarf.defSpeed = 0.8f;
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
		cells.clear();
		towers.clear();
		enemies.clear();
		traps.clear();
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
								Logging.log(3, cells.get(sx).get(sy).toString());

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
								Logging.log(3, mc.toString());
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

	private Timer scheduler = null;

	/**
	 * A loop-ot lefuttató, ütemezhető {@link TimerTask}. Alapjáraton null az
	 * értéke, és majd a startMainLoop fogja inicializálni.
	 */
	private TimerTask mainLoop = null;

	/**
	 * Ütemezi a scheduler-ben 10 ms-enkénti futásra a loop TimerTask-ot.
	 */
	public void startMainLoop() {
		mainLoop = new TimerTask() {

			@Override
			public void run() {
				loop();
			}
		};
		if (scheduler == null) {
			scheduler = new Timer();
		}
		scheduler.scheduleAtFixedRate(mainLoop, 0, timeStep);
	}

	/**
	 * Leállítja az ütemezett mainLoop TimerTaskot, de ezután a startMainLoop()
	 * hívással ez újra ütemezhető.
	 */
	public void pauseMainLoop() {
		if (mainLoop != null) {
			mainLoop.cancel();
		}
	}

	/**
	 * Leállítja az összes ütemezett TimerTask-ot és a schedulert is. Ezután a
	 * startMainLoop már nem hívható meg!!!
	 */
	public void stopMainLoop() {
		if (scheduler != null) {
			scheduler.cancel();
			scheduler = null;
		} else {
			Logging.log(0,
					"stopMainLoop-ot hívni startMainLoop nélkül nem szép dolog!");
		}
	}

	/**
	 * Az eseményeket vezérlő függvény
	 * 
	 */
	public void loop() {
		Logging.log(3, ">> Controller.loop() hívás");
		if (sentEnemies < maxEnemyNum) {
			if (canSpawn) {
				addRandomEnemy();
			}
		}
		for (Tower t : towers) {
			if (t.hasFog && t.fogTimeRemaining > 0) {
				t.fogTimeRemaining -= 1;
			}
			if (t.hasFog && t.fogTimeRemaining == 0) {
				t.removeFog();
			}
			// if (t.timeOfLastShoot > 0) {
			// t.timeOfLastShoot -= 1;
			// }
			t.fireAll();
		}
		stepAllEnemies();
		addRandomFog();
		for (Tower t : towers) {
			Logging.log(1, t.toString() + ", index: " + towers.indexOf(t));
		}
		/*
		 * for (Trap t : traps) { Logging.log(1, t.toString()); }
		 */
		Logging.log(1, saruman.toString());
		Logging.log(1, "Enemyk száma: " + enemies.size());
		Logging.log(1, "--Kör vége-- \n\t" + new Date().toString());
		Logging.log(4, "<< Controller.loop()");
	}

	/**
	 * Random ellenséget a pályához adó függvény, ha a randomitás ki van
	 * kapcsolva, akkor alapértelmezetten embert rak le.
	 */
	private void addRandomEnemy() {
		Logging.log(3, ">> Controller.addRandomEnemy() hívás");
		Random randgen = new Random();
		int a = randgen.nextInt(maxEnemyNum * 1000);
		if (a % maxEnemyNum > sentEnemies) {
			Logging.log(1, "<< Controller.addRandomEnemy(), nem adok hozzá.");
			return;
		}
		int n = randgen.nextInt(4);
		if (!random) {
			n = 0;
		}
		switch (n) {
		case 0:
			addHuman(new Human(Human.defMaxLP, Human.defSpeed));
			enemyChanged = true;
			break;
		case 1:
			addElf(new Elf(Elf.defMaxLP, Elf.defSpeed));
			enemyChanged = true;
			break;
		case 2:
			addHobbit(new Hobbit(Hobbit.defMaxLP, Hobbit.defSpeed));
			enemyChanged = true;
			break;
		case 3:
			addDwarf(new Dwarf(Dwarf.defMaxLP, Dwarf.defSpeed));
			enemyChanged = true;
			break;
		default:
			break;
		}
		Logging.log(4, "<< Controller.addRandomEnemy()");
	}

	/**
	 * ködöt random toronyhoz adó függvény
	 */
	private void addRandomFog() {
		Logging.log(2, ">> Controller.addRandomFog");
		Random randgen = new Random(System.currentTimeMillis());
		if (towers.size() <= 0 || randgen.nextInt(1000) > 1) {
			Logging.log(4, "<< Controller.addRandomFog, nem rakok ködöt");
			return;
		}
		int n = randgen.nextInt(towers.size());
		towers.get(n).addFog((randgen.nextInt(10) + 10) * 1000);
		towerChanged = true;
		Logging.log(4, "<< Controller.addRandomFog");
	}

	/**
	 * Embert a páláyhoz adó függvény
	 * 
	 * @param h
	 *            a pályához adandó Human
	 */
	public void addHuman(Human h) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(h);
		h.addEnemyListener(this);
		sentEnemies++;
		sp.enter(h);
		h.setTimeOfLastStep(System.currentTimeMillis());
		Logging.log(1, h.toString());
	}

	/**
	 * Tündét a páláyhoz adó függvény
	 * 
	 * @param h
	 *            a pályához adandó Elf
	 */
	public void addElf(Elf e) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(e);
		e.addEnemyListener(this);
		sentEnemies++;
		sp.enter(e);
		e.setTimeOfLastStep(System.currentTimeMillis());
		Logging.log(1, e.toString());
	}

	/**
	 * Hobbitot a páláyhoz adó függvény
	 * 
	 * @param h
	 *            a pályához adandó Hobbit
	 */
	public void addHobbit(Hobbit h) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(h);
		h.addEnemyListener(this);
		sentEnemies++;
		sp.enter(h);
		h.setTimeOfLastStep(System.currentTimeMillis());
		Logging.log(1, h.toString());
	}

	/**
	 * Törpöt a páláyhoz adó függvény
	 * 
	 * @param h
	 *            a pályához adandó Dwarf
	 */
	public void addDwarf(Dwarf d) {
		SpawnPointCell sp = (SpawnPointCell) cells.get(spawnCoords[0]).get(
				spawnCoords[1]);
		enemies.add(d);
		d.addEnemyListener(this);
		sentEnemies++;
		sp.enter(d);
		d.setTimeOfLastStep(System.currentTimeMillis());
		Logging.log(1, d.toString());
	}

	/**
	 * Minden pályán lévő ellenséget léptető függvény
	 */
	public void stepAllEnemies() {
		Logging.log(2, ">> Controller.stepAllEnemies() hívás");
		Iterator<Enemy> iter = enemies.iterator();
		while (iter.hasNext() && !gameEnded) {
			Enemy en = iter.next();
			try {
				if (en.leptet()) {
					enemyChanged = true;
				}
				Logging.log(1, en.toString());
			} catch (EnemyDeadException e1) {
				Logging.log(1, "\tAz enemy már meghalt... " + en.toString());
				iter.remove();
			} catch (EnemyCannotStepException e1) {
				Logging.log(0, "Valamiért nem tud lépni az enemy!!!");
				stopMainLoop();
				// e1.printStackTrace();
			}
		}
		enemies.addAll(enemiesToAdd);
		enemiesToAdd.clear();
		Logging.log(4, "<< Controller.stepAllEnemies() hívás");
	}

	/**
	 * Tornyot egy adott helyre lerakó függvény. tornyot
	 * 
	 * @param t
	 *            a hozzáadandó torony
	 * @param x
	 *            a cél cella x koordinátája
	 * @param y
	 *            a cél cella y koordinátája
	 */
	public void placeTower(Tower t, int x, int y) {
		if (cells.get(x).get(y).getType().equalsIgnoreCase("FieldCell")) {
			if (saruman.getManna() >= t.getCost()) {
				FieldCell fc = (FieldCell) cells.get(x).get(y);
				if (fc.addTower(t)) {
					towers.add(t);
					saruman.rmManna(t.getCost());
					towerChanged = true;
				}
			} else {
				Logging.log(1, "Nincs elég manna");
			}
		} else {
			Logging.log(0, "!!! Towert nem FieldCell-re raktuk!");
		}
	}

	/**
	 * Csapdát adott helyre lerakó függvény.
	 * 
	 * @param t
	 *            a hozzáadandó csapda
	 * @param x
	 *            a cél x koordinátája
	 * @param y
	 *            a cél y koordinátája
	 */
	public void placeTrap(Trap t, int x, int y) {
		if (!cells.get(x).get(y).getType().equalsIgnoreCase("FieldCell")) {
			if (saruman.getManna() >= Tower.getBaseCost()) {
				RouteCell rc = (RouteCell) cells.get(x).get(y);
				if (rc.addTrap(t)) {
					saruman.rmManna(Trap.getBaseCost());
					traps.add(t);
					trapChanged = true;
					Logging.log(1, t.toString() + " parentCell: " + x + ", "
							+ y + ", index: " + traps.indexOf(t));
				}
			} else {
				Logging.log(0, "Nincs elég manna");
			}
		} else {
			Logging.log(0, "!!! Trapet nem RouteCell-re raktuk!");
		}

	}

	public void placeMagicStone(MagicStone ms, int x, int y) {
		Cell c = cells.get(x).get(y);
		if (c.getType().equalsIgnoreCase("FieldCell")
				&& ((FieldCell) c).hasTower()) {
			if (saruman.getManna() >= MagicStone.calcCost()) {
				((FieldCell) c).getTower().addStone(ms);
			}
		}
		if (c.getType().equalsIgnoreCase("RouteCell")
				&& ((RouteCell) c).hasTrap()) {
			if (saruman.getManna() >= MagicStone.calcCost()) {
				((RouteCell) c).getTrap().addStone(ms);
			}

		}
	}

	/**
	 * Visszaadja a Trap-ek listáját.
	 * 
	 * @return
	 */
	public List<Trap> getTrap() {
		return traps;
	}

	/**
	 * Visszaadja a Tower-ek listáját.
	 * 
	 * @return
	 */
	public List<Tower> getTowers() {
		return towers;
	}

	/**
	 * Visszaadja az Enemy-k HashSet-jét.
	 * 
	 * @return
	 */
	public HashSet<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * Visszaadja az x,y helyen álló Cellát
	 * 
	 * @param x
	 *            x koordináta
	 * @param y
	 *            y koordináta
	 * @return
	 */

	public Cell getCell(int x, int y) {
		return cells.get(x).get(y);
	}

	/**
	 * Visszaadja az x,y helyen álló Cellát
	 * 
	 * @return
	 */
	public int[] getMapSize() {
		int[] temp = { mapWidth, mapHeight };
		return temp;
	}

	/**
	 * visszaadja az i. tornyot
	 * 
	 * @param i
	 *            sorszám
	 * @return
	 */
	public Tower getTower(int i) {
		return towers.get(i);
	}

	/**
	 * visszaadja az i. csapdát
	 * 
	 * @param i
	 *            sorszám
	 * @return
	 */
	public Trap getTrap(int i) {
		return traps.get(i);
	}

	/**
	 * beállítja a randomitást
	 * 
	 * @param b
	 *            randomitás értéke
	 */
	public static void setRandom(boolean b) {
		random = b;
	}

	public static boolean isRandom() {
		return random;
	}

	/**
	 * manuális vagy automatikus-e az ellenségek pályára helyezése
	 * 
	 * @param b
	 *            auto-true, manual-false
	 */
	public void setCanSpawn(boolean b) {
		canSpawn = b;
	}

	/**
	 * a pálya fájl nevét állító függvény
	 * 
	 * @param mapFileName
	 */
	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
		init();
	}

	/**
	 * a játék végét visszaadó függvény
	 * 
	 * @return
	 */
	public boolean isGameEnded() {
		return gameEnded;
	}

	/**
	 * a játék végét beállító függvény
	 * 
	 * @param gameEnded
	 *            a játék vége
	 */
	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}

	// RouteCellListener

	@Override
	public void onEnter(RouteCell sender, Elf e) {
		Logging.log(
				2,
				">> Controller.onEnter() hívás, paraméterek: "
						+ sender.toString() + ", " + e.toString());
		if (sender.getType().equalsIgnoreCase("MordorCell")) {
			setGameEnded(true);
			setWinner("Ellenségek");
			Logging.log(1, "!!! Enemy nyert: " + e.toString());
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
			setGameEnded(true);
			setWinner("Ellenségek");
			Logging.log(1, "!!! Enemy nyert: " + d.toString());
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
			setGameEnded(true);
			setWinner("Ellenségek");
			Logging.log(1, "!!! Enemy nyert: " + h.toString());
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
			setGameEnded(true);
			setWinner("Ellenségek");
			Logging.log(1, "!!! Enemy nyert: " + h.toString());
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
		enemiesToAdd.add(e);
		sentEnemies++;
		enemyChanged = true;
		Logging.log(1, e.toString());
		Logging.log(4, "<< Controller.onSlice() hívás");
	}

	@Override
	public void onDie(Enemy e) {
		Logging.log(2,
				">> Controller.onDie() hívás, paraméter: " + e.toString());
		saruman.addManna(e.getMaxLifePoint());
		diedEnemies++;
		enemyChanged = true;
		if (diedEnemies >= maxEnemyNum && diedEnemies == sentEnemies) {
			setWinner("Szarumán");
			setGameEnded(true);
			Logging.log(1, "!!! Szarumán nyert !!!");
			stopMainLoop();
		}
		Logging.log(4, "<< Controller.onDie() hívás");
	}

}
