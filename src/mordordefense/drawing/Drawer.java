package mordordefense.drawing;

import java.awt.Color;
import java.awt.Graphics;

import mordordefense.Cell;
import mordordefense.Enemy;
import mordordefense.Tower;

public class Drawer {
	public void drawTower(Graphics g, int x, int y, Tower t) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, 40, 40);
	}

	public void drawTrap(Graphics g, int x, int y) {
		g.setColor(Color.gray);
		g.fillRect(x, y, 40, 40);
	}

	public void drawEnemy(Graphics g, int x, int y, Enemy e) {
		// TODO Implementálni
	}

	public void drawCell(Graphics g, int x, int y, Cell c) {
		// TODO Implementálni
	}
}
