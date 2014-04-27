package mordordefense.drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Drawer drawer;
	public DrawPanel(Drawer d)                       
	{
		super();
		setBackground(Color.GREEN);
		drawer=d;
	}

	public void paintComponent(Graphics g)  // kirajzolo fuggveny
	{
		super.paintComponent(g);            //helyes kirajzolas erdekeben
		drawer.drawTower(g, 100, 100);
		drawer.drawTrap(g, 0, 0);
	}
}
