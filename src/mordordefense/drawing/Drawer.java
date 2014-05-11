package mordordefense.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mordordefense.Cell;
import mordordefense.Enemy;
import mordordefense.MagicStone;
import mordordefense.RouteCell;
import mordordefense.Tower;
import mordordefense.Trap;

public class Drawer {

	private int cellSize;

	public void drawTower(Graphics g, int x, int y, Tower t) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(197, 255, 0));
		g2.fillOval(x, y, cellSize, cellSize);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2.0f));
		g2.drawOval(x, y, cellSize, cellSize);

		// Van rajta köd?
		if (t.isHasFog()) {
			g2.setStroke(new BasicStroke(1.0f));
			g2.setColor(new Color(50, 50, 50, 100));
			g2.fillRect(x, y, cellSize, cellSize);
		}
		ArrayList<MagicStone> stones = (ArrayList<MagicStone>) t.getStones();
		int n = stones.size();
		// Bal felső sarokba kiírjuk, hány kő van rajta.
		if (n != 0) {
			int fonth = cellSize / 3;
			g2.setColor(Color.BLACK);
			g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fonth));
			// Ez itt azért valid, mert monospaced!!
			int fontw = g2.getFontMetrics().charWidth(' ');
			g2.drawString(Integer.toString(n), x + fontw, y + fonth * 2);
		}

	}

	public void drawTrap(Graphics g, int x, int y, Trap t) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(129, 129, 129));
		g.fillRect(x, y, cellSize, cellSize);
		ArrayList<MagicStone> stones = (ArrayList<MagicStone>) t.getStones();
		int n = stones.size();
		// Bal felső sarokba kiírjuk, hány kő van rajta.
		if (n != 0) {
			int fonth = cellSize / 3;
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fonth));
			// Ez itt azért valid, mert monospaced!!
			int fontw = g2.getFontMetrics().charWidth(' ') / 2;
			g.drawString(Integer.toString(n), x + fontw, y
					+ (int) (fonth * 1.1));
		}
	}

	public void drawEnemy(Graphics g, int x, int y, int cellSize, Enemy e,
			int numOfEnemy) {
		String t = e.getType();
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, cellSize / 4));
		int X = x;
		int Y = y;
		if (t.equalsIgnoreCase("Human")) {
			g2.setColor(new Color(255, 92, 208));
		} else if (t.equalsIgnoreCase("Hobbit")) {
			g2.setColor(new Color(0, 255, 52));
			X = x + cellSize / 2;
		} else if (t.equalsIgnoreCase("Dwarf")) {
			g2.setColor(new Color(182, 0, 0));
			X = x + cellSize / 2;
			Y = y + cellSize / 2;
		} else if (t.equalsIgnoreCase("Elf")) {
			g2.setColor(new Color(233, 255, 40));
			Y = y + cellSize / 2;
		}
		g2.fillOval(X, Y, cellSize / 2, cellSize / 2);
		g2.setColor(Color.BLACK);
		g2.drawString(Integer.toString(numOfEnemy), X + cellSize / 8, Y
				+ cellSize / 4);
	}

	public void drawCell(Graphics g, int x, int y, Cell c) {
		String type = c.getType();
		if (type.equalsIgnoreCase("FieldCell")) {
			g.setColor(new Color(0, 120, 0));
			g.fillRect(x, y, cellSize, cellSize);
		} else {
			if (((RouteCell) c).getTrap() != null) {
				// g.setColor(new Color(129, 129, 129));
				drawTrap(g, x, y, ((RouteCell) c).getTrap());
			} else {
				if (type.equalsIgnoreCase("RouteCell")) {
					g.setColor(new Color(110, 82, 54));
				} else if (type.equalsIgnoreCase("MordorCell")) {
					g.setColor(new Color(0, 0, 0));
				} else if (type.equalsIgnoreCase("SpawnPointCell")) {
					g.setColor(new Color(112, 0, 255));
				}
				g.fillRect(x, y, cellSize, cellSize);
			}
			List<Enemy> enemies = ((RouteCell) c).getEnemies();
			HashMap<String, Integer> enemyNum = new HashMap<String, Integer>();
			for (Enemy e : enemies) {
				int coords[] = c.getCoords();
				int n = 1;
				if (enemyNum.containsKey(e.getType())) {
					n = enemyNum.get(e.getType());
					enemyNum.put(e.getType(), ++n);
				} else {
					enemyNum.put(e.getType(), n);
				}
				drawEnemy(g, coords[0] * cellSize, coords[1] * cellSize,
						cellSize, e, n);
			}
		}
		// if(c)
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