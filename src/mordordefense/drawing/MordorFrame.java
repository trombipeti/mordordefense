package mordordefense.drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import mordordefense.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MordorFrame extends JFrame {

	// Hogy ne sirjon az eclipse
	private static final long serialVersionUID = 6107185503023298334L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private Controller control;
	private DrawPanel Board;
	private Drawer drawer = new Drawer();

	/**
	 * Create the frame.
	 */
	public MordorFrame() {
		setTitle("soamazing Mordordefense");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mntmStart = new JMenuItem("Start");
		mnGame.add(mntmStart);

		JMenuItem mntmStop = new JMenuItem("Stop");
		mnGame.add(mntmStop);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnGame.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mnGame.add(mntmLoad);

		JMenu mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);

		JMenuItem mntmTower = new JMenuItem("Tower");
		mntmTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Tower");
			}
		});
		mnAdd.add(mntmTower);

		JMenuItem mntmTrap = new JMenuItem("Trap");
		mnAdd.add(mntmTrap);

		JMenuItem mntmMagicstone = new JMenuItem("MagicStone");
		mnAdd.add(mntmMagicstone);

		JMenu mnMap = new JMenu("Map");
		menuBar.add(mnMap);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnMap.add(mntmOpen);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Board = new DrawPanel(drawer);

		contentPane.add(Board, BorderLayout.CENTER);

		JPanel Stats = new JPanel();
		contentPane.add(Stats, BorderLayout.NORTH);

		Board.repaint();
	}

	public void setController(Controller c) {
		control = c;
	}

}
