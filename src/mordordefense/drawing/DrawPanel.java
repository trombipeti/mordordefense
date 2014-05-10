package mordordefense.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import mordordefense.Cell;
import mordordefense.Controller;
import mordordefense.Tower;
import mordordefense.testing.Logging;

public class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Egy layer az Enemy-k kirajzolására
	 */
	protected BufferedImage enemyLayer;
	/**
	 * Egy layer a Tower-ek és trap-ek kirajzolására kirajzolására
	 */
	protected BufferedImage towerLayer;
	/**
	 * Egy layer a Map kirajzolására kirajzolására
	 */
	protected BufferedImage mapLayer;

	/**
	 * Egy layer a játék állásának kirajzolására
	 */
	protected BufferedImage infoLayer;

	protected int mapHeight;
	protected int mapWidth;
	protected int cellSize;

	protected boolean gameEndDrawn;

	private Drawer drawer;
	private Controller control;

	public DrawPanel(Drawer d, int w, int h) {
		super();
		setBackground(Color.WHITE);
		drawer = d;
		mapWidth = (int) (w * 0.8);
		mapHeight = h;
		enemyLayer = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		towerLayer = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		mapLayer = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		infoLayer = new BufferedImage((int) (w * 0.2), mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		gameEndDrawn = false;
	}

	public void setSize(int width, int height) {
		mapWidth = width;
		mapHeight = height;
	}

	public void setController(Controller c) {
		control = c; // Map méretének beállítása
	}

	public int getCellSize() {
		return cellSize;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public Controller getController() {
		return control;
	}

	public void paintTowers() {
		int transparency[] = new int[mapWidth * mapHeight];
		Arrays.fill(transparency, 0);
		towerLayer.setRGB(0, 0, mapWidth, mapHeight, transparency, 0, 1);
		Graphics2D g = (Graphics2D) towerLayer.getGraphics();
		ArrayList<Tower> towers = (ArrayList<Tower>) control.getTowers();
		for (Tower t : towers) {
			int coords[] = t.getParentCell().getCoords();
			drawer.drawTower(g, coords[0] * cellSize, coords[1] * cellSize, t);
		}
	}

	public void paintMap() {
		int transparency[] = new int[mapWidth * mapHeight];
		Arrays.fill(transparency, 0);
		mapLayer.setRGB(0, 0, mapWidth, mapHeight, transparency, 0, 1);
		Graphics g = mapLayer.getGraphics();
		int size[] = control.getMapSize();
		int w = size[0];
		int h = size[1];
		// if (w == 0 || h == 0) {
		// return;
		// }
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, mapWidth, mapHeight);
		cellSize = (int) Math
				.min((mapHeight / h) * 0.95, (mapWidth / w) * 0.95);
		drawer.setCellSize(cellSize);
		for (int i = 0; i < w; ++i) {
			for (int j = 0; j < h; ++j) {
				Cell c = control.getCell(i, j);
				drawer.drawCell(g, i * cellSize, j * cellSize, c);
			}
		}
		if (control.isGameEnded()) {
			gameEndDrawn = true;
		}

	}

	private void paintInfo() {
		int width = infoLayer.getWidth();
		int height = infoLayer.getHeight();
		int transparency[] = new int[width * height];
		Arrays.fill(transparency, 0);
		mapLayer.setRGB(0, 0, width, height, transparency, 0, 1);

		Graphics g = infoLayer.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		int fontH = mapHeight / 30;
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontH));

		g.setColor(Color.BLACK);

		String enemies = "Enemies: " + control.getEnemyNum();
		String manna = "Manna: " + control.getSarumanManna();

		if (control.getSarumanManna() < 10) {
			g.setColor(Color.RED);
		}
		g.drawString(manna, infoLayer.getWidth() / 10, 2 * fontH);
		g.setColor(Color.BLACK);
		g.drawString(enemies, infoLayer.getWidth() / 10, 4 * fontH);
	}

	/**
	 * Ez a függvény végzi el a tényleges megjelenítést. Kirajzolja a pályát, rá
	 * az enemyket és a tornyokat.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) // kirajzolo fuggveny
	{
		super.paint(g); // helyes kirajzolas erdekeben

		g.clearRect(0, 0, mapWidth, mapHeight);

		paintMap();
		g.drawImage(mapLayer, 0, 0, null);

		paintTowers();
		g.drawImage(towerLayer, 0, 0, null);

		paintInfo();
		g.drawImage(infoLayer, mapWidth, 0, null);

		if (control.isGameEnded() && gameEndDrawn) {
			BufferedImage end = new BufferedImage(mapWidth, mapHeight,
					BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = end.getGraphics();
			g2.setColor(new Color(20, 20, 20, 150));
			g2.fillRect(0, 0, mapWidth, mapHeight);
			String w = control.getWinner().toString();
			if (w == null) {
				Logging.log(0, "Nagy baj van!!! Játék vége, de winner = null..");
			} else {
				g2.setColor(Color.WHITE);
				int fontH = mapHeight / 5;
				g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontH));
				int stringW = g2.getFontMetrics().charsWidth(w.toCharArray(),
						0, w.length());
				String szoveg = "Nyertes:";
				int szovegW = g2.getFontMetrics().charsWidth(
						szoveg.toCharArray(), 0, szoveg.length());
				g2.drawString(szoveg, mapWidth / 2 - szovegW / 2, mapHeight / 4
						- fontH / 2);
				g2.drawString(w, mapWidth / 2 - stringW / 2, mapHeight / 2
						- fontH / 2);
			}
			g.drawImage(end, 0, 0, null);
		}
	}

	/**
	 * Letörli az összes kirajzolt réteget.
	 */
	public void clear() {
		int transparency[] = new int[mapWidth * mapHeight];
		Arrays.fill(transparency, 0);
		towerLayer.setRGB(0, 0, mapWidth, mapHeight, transparency, 0, 1);
		mapLayer.setRGB(0, 0, mapWidth, mapHeight, transparency, 0, 1);
		enemyLayer.setRGB(0, 0, mapWidth, mapHeight, transparency, 0, 1);
		infoLayer.setRGB(0, 0, infoLayer.getWidth(), infoLayer.getHeight(),
				transparency, 0, 1);
	}

	public void setGameEndDrawn(boolean b) {
		gameEndDrawn = b;
	}
}
