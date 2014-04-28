package mordordefense.drawing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import mordordefense.Controller;
import mordordefense.FieldCell;
import mordordefense.MagicStone;
import mordordefense.Tower;
import mordordefense.Trap;

public class MordorFrame extends JFrame {

	public static enum State {
		NORMAL, TOWER, TRAP, MAGICSTONE
	}

	// Hogy ne sirjon az eclipse
	private static final long serialVersionUID = 6107185503023298334L;
	/**
	 * 
	 */
	private JPanel contentPane;
	// private Controller control;
	private DrawPanel Board;
	private Drawer drawer = new Drawer();

	private State state;

	/**
	 * Create the frame.
	 */
	public MordorFrame(Controller c) {
		setTitle("soamazing Mordordefense");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(true);

		state = State.NORMAL;

		// control = c;

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mntmStart = new JMenuItem("Start");
		mntmStart.setAccelerator(KeyStroke.getKeyStroke('G',
				KeyEvent.CTRL_DOWN_MASK));
		mntmStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Board.getController().startMainLoop();
				validate();
				repaint();
			}
		});
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
		mntmTower.setAccelerator(KeyStroke.getKeyStroke('T',
				KeyEvent.CTRL_DOWN_MASK));
		mntmTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state = State.TOWER;
			}
		});
		mnAdd.add(mntmTower);

		JMenuItem mntmTrap = new JMenuItem("Trap");
		mntmTrap.setAccelerator(KeyStroke.getKeyStroke('R',
				KeyEvent.CTRL_DOWN_MASK));
		mntmTrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state = State.TRAP;
			}
		});
		mnAdd.add(mntmTrap);

		JMenuItem mntmMagicstone = new JMenuItem("MagicStone");
		mntmMagicstone.setAccelerator(KeyStroke.getKeyStroke('M',
				KeyEvent.CTRL_DOWN_MASK));
		mntmMagicstone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state = State.MAGICSTONE;
			}
		});
		mnAdd.add(mntmMagicstone);

		JMenu mnMap = new JMenu("Map");
		menuBar.add(mnMap);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke('O',
				KeyEvent.CTRL_DOWN_MASK));
		mntmOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				state = State.NORMAL;
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter f = new FileNameExtensionFilter(
						"Pályabeállító fájlok", "p", "txt");
				chooser.setFileFilter(f);
				int retval = chooser.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					String n = "";
					try {
						n = chooser.getSelectedFile().getCanonicalPath();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Board.getController().setMapFileName(n);
					// Board.setController(control);
					Board.clear();
					validate();
					repaint();
				}
			}
		});
		mnMap.add(mntmOpen);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Board = new DrawPanel(drawer, 800, 500);
		Board.setController(c);
		Board.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller control = Board.getController();
				// Ha cellára kattintottunk
				int cellx = e.getX() / Board.getCellSize();
				int cellnumx = control.getMapSize()[0];
				int celly = e.getY() / Board.getCellSize();
				int cellnumy = control.getMapSize()[1];
				if (cellx < cellnumx && celly < cellnumy) {
					System.out.println(cellx + "," + celly);
					switch (state) {
					case TOWER:
						if (control.getCell(cellx, celly).getType()
								.equalsIgnoreCase("FieldCell")) {
							control.placeTower(askUserForTower(), cellx, celly);
							Board.setController(control);
							validate();
							repaint();
							state = State.NORMAL;
						}
						break;
					case TRAP:
						System.out.println("IT'S A TRAP");
						if (!control.getCell(cellx, celly).getType()
								.equalsIgnoreCase("FieldCell")) {
							Board.getController().placeTrap(askUserForTrap(),
									cellx, celly);
							// Board.setController(control);
							validate();
							repaint();
							state = State.NORMAL;
						}
						break;
					case MAGICSTONE:
						Board.getController().placeMagicStone(
								askUserForMagicStone(), cellx, celly);
						validate();
						repaint();
						state = State.NORMAL;
						break;
					default:
						break;
					}
				}
			}
		});

		contentPane.add(Board, BorderLayout.CENTER);
		Board.getController().init();

		JPanel Stats = new JPanel();
		contentPane.add(Stats, BorderLayout.NORTH);

		Board.repaint();
	}

	protected Tower askUserForTower() {
		// TODO Itt valahogy meg kell kérdezni a usertől a torony értékeit
		// (dialog?)
		return new Tower();
	}

	protected Trap askUserForTrap() {
		// TODO Itt valahogy meg kell kérdezni a usertől a csapda értékeit
		// (dialog?)
		return new Trap();
	}

	protected MagicStone askUserForMagicStone() {
		// TODO Itt valahogy meg kell kérdezni a usertől a kő értékeit (dialog?)
		return new MagicStone(1, 1, 1, 1, 1, 1, 1);
	}

	public void setController(Controller c) {
		Board.setController(c);
	}

}
