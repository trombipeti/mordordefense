package mordordefense.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import mordordefense.Cell;
import mordordefense.Controller;
import mordordefense.Tower;

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
	 * Egy layer, amit egyből a screenre rajzolunk ki.
	 */
	protected BufferedImage screenImage;

	protected int mapHeight;
	protected int mapWidth;
	protected int cellSize;

	private Drawer drawer;
	private Controller control;

	public DrawPanel(Drawer d) {
		super();
		setBackground(Color.WHITE);
		drawer = d;
		// TODO ezeket konstruktorból
		mapWidth = 800;
		mapHeight = 500;
		enemyLayer = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		towerLayer = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		mapLayer = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_ARGB);
		screenImage = new BufferedImage(mapWidth, mapHeight,
				BufferedImage.TYPE_INT_RGB);
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
	

	public void paintEnemies() {
		// TODO Implementálni
	}

	public void paintTowers() {
		Graphics g = towerLayer.getGraphics();
		ArrayList<Tower> towers = (ArrayList<Tower>) control.getTowers();
		for (Tower t : towers) {
			int coords[] = t.getParentCell().getCoords();
			drawer.drawTower(g, coords[0] * cellSize, coords[1] * cellSize, t);
		}
	}

	public void paintMap() {
		Graphics g = mapLayer.getGraphics();
		int size[] = control.getMapSize();
		int w = size[0];
		int h = size[1];
		// if (w == 0 || h == 0) {
		// return;
		// }
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, mapWidth, mapHeight);
		cellSize = Math.min(mapHeight / h, mapWidth / w);
		drawer.setCellSize(cellSize);
		for (int i = 0; i < w; ++i) {
			for (int j = 0; j < h; ++j) {
				Cell c = control.getCell(i, j);
				drawer.drawCell(g, i * cellSize, j * cellSize, c);
			}
		}

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

		paintMap();
		g.drawImage(mapLayer, 0, 0, null);
		if (control.enemyChanged) {
			paintEnemies();
			g.drawImage(enemyLayer,0,0,null);
		}
		if (control.towerChanged) {
			paintTowers();
			g.drawImage(towerLayer,0,0,null);
		}
		// g2.drawImage(mapLayer, 0, 0, null);
		// gmap.setColor(Color.BLUE);
		// gmap.fillRect(0, 0, 800, 600);
		// g2.drawImage(enemyLayer, 0, 0, null);
		// g.drawImage(screenImage, 0, 0, null);
	}
}
