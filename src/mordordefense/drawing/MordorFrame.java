package mordordefense.drawing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import mordordefense.Cell;
import mordordefense.Controller;
import mordordefense.FieldCell;
import mordordefense.MagicStone;
import mordordefense.RouteCell;
import mordordefense.Tower;
import mordordefense.Trap;

public class MordorFrame extends JFrame {

	public static enum State {
		NORMAL, TOWER, TRAP, MAGICSTONE
	}

	// Hogy ne sirjon az eclipse
	private static final long serialVersionUID = 6107185503023298334L;

	private JPanel contentPane;
	
	// private Controller control;
	private DrawPanel Board;
	private Drawer drawer = new Drawer();

	private Timer paintTimer;
	private TimerTask updateBoard;

	private State state;
	protected boolean gameStarted;

	/**
	 * Create the frame.
	 */
	public MordorFrame(Controller c) {
		setTitle("soamazing Mordordefense");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setPreferredSize(new Dimension(800, 600));
		setResizable(true);

		Board = new DrawPanel(drawer, 780, 500);
		Board.setController(c);

		state = State.NORMAL;
		gameStarted = false;

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
				if(Board.getController().isGameEnded()) {
					Board.getController().reset();
				}
				gameStarted = true;
				Board.getController().startMainLoop();
				Board.validate();
				Board.repaint();
				validate();
				repaint();
			}
		});
		mnGame.add(mntmStart);

		JMenuItem mntmPause = new JMenuItem("Pause");
		mntmPause.setAccelerator(KeyStroke.getKeyStroke('P',
				KeyEvent.CTRL_DOWN_MASK));
		mntmPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Itt Pause legyen vagy Stop? Vagy legyen a pause-ra külön
				// menü?
				Board.getController().pauseMainLoop();
				validate();
				repaint();
			}
		});
		mnGame.add(mntmPause);

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
					Board.getController().pauseMainLoop();
					Board.getController().setMapFileName(n);
					Board.clear();
					Board.setGameEndDrawn(false);
					// Board.calcSize();
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

		// Egy kattintás: state-től függően tower/trap/magicstone
		// Dupla kattintás: simán trap/tower mezőtől függően, ctrl-duplaklikk
		// meg magicstone.
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

					// Duplaklikk
					if (e.getClickCount() == 2) {
						// CTRL+duplaklikk az magicstone
						if (e.isControlDown()) {
							state = State.MAGICSTONE;
						} else {
							// Sima duplaklikk az torony/csapda
							if (control.getCell(cellx, celly).getType()
									.equalsIgnoreCase("FieldCell")) {
								state = State.TOWER;
							} else {
								state = State.TRAP;
							}
						}
					}
					switch (state) {
					case TOWER:
						if (Board.getController().getCell(cellx, celly)
								.getType().equalsIgnoreCase("FieldCell")) {
							if (gameStarted) {
								Board.getController().pauseMainLoop();
							}
							Tower t = askUserForTower();
							if (t != null
									&& Board.getController().placeTower(t,
											cellx, celly) == false) {
								// TODO Itt ne dialog legyen
								JOptionPane.showMessageDialog(null,
										"Nem sikerült tornyot építeni",
										"Toronyépítés sikertelen",
										JOptionPane.ERROR_MESSAGE);
							}
							if (gameStarted) {
								Board.getController().startMainLoop();
							}
							validate();
							repaint();
							state = State.NORMAL;
						}
						break;
					case TRAP:
						if (!control.getCell(cellx, celly).getType()
								.equalsIgnoreCase("FieldCell")) {
							if (gameStarted) {
								Board.getController().pauseMainLoop();
							}
							Trap t = askUserForTrap();
							if (t != null
									&& Board.getController().placeTrap(t,
											cellx, celly) == false) {
								// TODO Itt ne dialog legyen
								JOptionPane.showMessageDialog(null,
										"Nem sikerült csapdát építeni",
										"Csapdaépítés sikertelen",
										JOptionPane.ERROR_MESSAGE);
							}
							if (gameStarted) {
								Board.getController().startMainLoop();
							}
							validate();
							repaint();
							state = State.NORMAL;
						}
						break;
					case MAGICSTONE:
						Cell cell =  Board.getController().getCell(cellx, celly);
						String type = cell.getType();
						if(type.equalsIgnoreCase("FieldCell")) {
							if(((FieldCell)cell).hasTower() == false) {
								state = State.NORMAL;
								break;
							}
						} else {
							if(((RouteCell)cell).hasTrap() == false) {
								state = State.NORMAL;
								break;
							}
						}
						if (gameStarted) {
							Board.getController().pauseMainLoop();
						}
						MagicStone m = askUserForMagicStone();
						Board.getController().placeMagicStone(m, cellx, celly);
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

		JPanel Stats = new JPanel();
		contentPane.add(Stats, BorderLayout.NORTH);

		Board.validate();
		Board.repaint();

		paintTimer = new Timer();
		updateBoard = new TimerTask() {

			@Override
			public void run() {
				if (Board.getController().enemyChanged
						|| Board.getController().towerChanged
						|| Board.getController().trapChanged) {
					Board.validate();
					Board.repaint();
				}
			}
		};

		paintTimer.scheduleAtFixedRate(updateBoard, 0, 50);
		
		add(Board);
		
		pack();

	}

	/**
	 * Egy dialógust jelenít meg, amely bekéri a felhasználótól egy torony
	 * adatait.
	 * 
	 * @return Egy inicializált {@link Tower}, amely utána elhelyezhető a
	 *         pályán.
	 */
	protected Tower askUserForTower() {
		float f = 1;
		float r = 1;
		float d = 1;
		JTextField freqField = new JTextField(10);
		JTextField radField = new JTextField(10);
		JTextField dmgField = new JTextField(10);
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Tüzelési gyakoriság:"), freqField,
				new JLabel("Hatótáv: "), radField, new JLabel("Sebzés: "),
				dmgField };
		boolean ans = false;
		while (!ans) {
			int re = JOptionPane.showConfirmDialog(this, inputs,
					"Torony tulajdonságai", JOptionPane.OK_CANCEL_OPTION);
			if (re == JOptionPane.CANCEL_OPTION) {
				return null;
			}
			try {
				f = Float.parseFloat(freqField.getText());
				r = Float.parseFloat(radField.getText());
				d = Float.parseFloat(dmgField.getText());
			} catch (NumberFormatException e) {
				JLabel l = (JLabel) (inputs[0]);
				l.setText("<html>Helytelen adatok, csak számokat adj meg!<br />"
						+ l.getText() + "</html>");
				continue;
			}
			ans = true;
		}
		return new Tower(f, r, d);
	}

	protected Trap askUserForTrap() {
		float s = 1;
		JTextField sField = new JTextField(10);
		final JComponent[] inputs = new JComponent[] { new JLabel("Erősség:"),
				sField };
		boolean ans = false;
		while (!ans) {
			int r = JOptionPane.showConfirmDialog(this, inputs,
					"Csapda tulajdonságai", JOptionPane.OK_CANCEL_OPTION);
			if (r == JOptionPane.CANCEL_OPTION) {
				return null;
			}
			try {
				s = Float.parseFloat(sField.getText());
			} catch (NumberFormatException e) {
				JLabel l = (JLabel) (inputs[0]);
				l.setText("<html>Helytelen adatok, csak számokat adj meg!<br />"
						+ l.getText() + "</html>");
				continue;
			}
			ans = true;
		}
		return new Trap(s);
	}

	protected MagicStone askUserForMagicStone() {
		float elf = 1, dwarf = 1, hobbit = 1, human = 1;
		float f = 0, r = 0, d = 0;
		JTextField elfField = new JTextField(10);
		JTextField dwarfField = new JTextField(10);
		JTextField hobbitField = new JTextField(10);
		JTextField humanField = new JTextField(10);
		JTextField freqField = new JTextField(10);
		JTextField radField = new JTextField(10);
		JTextField dmgField = new JTextField(10);
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Elf szorzó:"), elfField,
				new JLabel("Dwarf szorzó:"), dwarfField,
				new JLabel("Hobbit szorzó:"), hobbitField,
				new JLabel("Human szorzó:"), humanField,
				new JLabel("Tüzelési gyakoriság:"), freqField,
				new JLabel("Hatótáv: "), radField, new JLabel("Sebzés: "),
				dmgField };
		boolean ans = false;
		while (!ans) {
			int re = JOptionPane.showConfirmDialog(this, inputs,
					"Torony tulajdonságai", JOptionPane.OK_CANCEL_OPTION);
			if (re == JOptionPane.CANCEL_OPTION) {
				return null;
			}
			try {
				f = Float.parseFloat(freqField.getText());
				r = Float.parseFloat(radField.getText());
				d = Float.parseFloat(dmgField.getText());
				elf = Float.parseFloat(elfField.getText());
				dwarf = Float.parseFloat(dwarfField.getText());
				hobbit = Float.parseFloat(hobbitField.getText());
				human = Float.parseFloat(humanField.getText());
			} catch (NumberFormatException e) {
				JLabel l = (JLabel) (inputs[0]);
				l.setText("<html>Helytelen adatok, csak számokat adj meg!<br />"
						+ l.getText() + "</html>");
				continue;
			}
			ans = true;
		}
		return new MagicStone(elf, dwarf, hobbit, human, d, f, r);
	}

	public void setController(Controller c) {
		Board.setController(c);
	}

}
