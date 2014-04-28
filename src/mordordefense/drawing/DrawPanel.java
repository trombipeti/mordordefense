package mordordefense.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import mordordefense.Controller;

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
		setBackground(Color.GREEN);
		drawer = d;
		enemyLayer = new BufferedImage(10,10,/*this.getWidth(), this.getHeight(),*/
				BufferedImage.TYPE_INT_ARGB);
		towerLayer = new BufferedImage(10,10,/*this.getWidth(), this.getHeight(),*/
				BufferedImage.TYPE_INT_ARGB);
		mapLayer = new BufferedImage(10,10,/*this.getWidth(), this.getHeight(),*/
				BufferedImage.TYPE_INT_RGB);
		screenImage = new BufferedImage(10,10,/*this.getWidth(), this.getHeight(),*/
				BufferedImage.TYPE_INT_RGB);
	}

	public void setController(Controller c) {
		control = c; // Map méretének beállítása
	}

	public void paintEnemies() {
		//TODO Implementálni
	}

	public void paintTowers() {
		//TODO Implementálni
	}

	public void paintMap() {
		//TODO Implementálni	
	}

	public void paintComponent(Graphics g) // kirajzolo fuggveny
	{
		super.paintComponent(g); // helyes kirajzolas erdekeben
		Graphics g2 = screenImage.getGraphics();
		/*
		 * if (control.enemyChanged) paintEnemies(); if (control.towerChanged)
		 * paintTowers(); g2.drawImage(mapLayer, 0, 0, null);
		 * g2.drawImage(towerLayer, 0, 0, null); g2.drawImage(enemyLayer, 0, 0,
		 * null); g.drawImage(screenImage, 0, 0, null);
		 */
	}
}
