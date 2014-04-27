package mordordefense.drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Drawer {
	public void drawTower(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, 40, 40);
	}

	public void drawTrap(Graphics g, int x, int y) {
		g.setColor(Color.gray);
		g.fillRect(x, y, 40, 40);
	}
}