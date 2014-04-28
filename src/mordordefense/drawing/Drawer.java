package mordordefense.drawing;

import java.awt.Color;
import java.awt.Graphics;

import mordordefense.Cell;
import mordordefense.Enemy;
import mordordefense.Tower;

public class Drawer {

	private int cellSize;

	public void drawTower(Graphics g, int x, int y, Tower t) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, cellSize, cellSize);
	}

	public void drawTrap(Graphics g, int x, int y) {
		g.setColor(Color.gray);
		g.fillRect(x, y, 40, 40);
	}

	public void drawEnemy(Graphics g, int x, int y, Enemy e) {
		// TODO Implementálni
	}

	public void drawCell(Graphics g, int x, int y, Cell c) {
		String type = c.getType();
		if (type.equalsIgnoreCase("FieldCell")) {
			g.setColor(new Color(0, 120, 0));
		} else if (type.equalsIgnoreCase("RouteCell")) {
			g.setColor(new Color(110, 82, 54));
		} else if (type.equalsIgnoreCase("MordorCell")) {
			g.setColor(new Color(0, 0, 0));
		} else if (type.equalsIgnoreCase("SpawnPointCell")) {
			g.setColor(new Color(112, 0, 255));
		}
		g.fillRect(x, y, cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, cellSize, cellSize);
	}

	public int getCellSize() {
		return cellSize;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}
}
