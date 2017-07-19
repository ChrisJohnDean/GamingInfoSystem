package a01001690.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a01001690.dao.GameDao;
import a01001690.dao.PersonaDao;
import a01001690.dao.PlayerDao;
import a01001690.dao.ScoreDao;
import a01001690.data.ApplicationException;
import a01001690.data.Game;
import a01001690.data.Persona;
import a01001690.data.Player;
import a01001690.data.Score;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	public MainFrame(PlayerDao playerDao, PersonaDao personaDao, ScoreDao scoreDao, GameDao gameDao) throws SQLException, ApplicationException {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		List<String> ids = playerDao.getiDs();
		ArrayList<Player> players = new ArrayList<>();
		for (String id : ids) {
			players.add(playerDao.getPlayer(id));
			System.out.println(playerDao.getPlayer(id).toString());
		}
		ArrayList<Persona> personas = personaDao.getPersonas();
		for (Persona persona : personas) {
			System.out.println(persona.toString());
		}
		ArrayList<Score> scores = scoreDao.getScores();
		ArrayList<Game> games = gameDao.getGames();
		ArrayList<String> reports = new ArrayList<String>();
		LinkedHashMap<String, Integer> reportsMap = new LinkedHashMap<String, Integer>();
		ArrayList<String> gamerTags = new ArrayList<String>();
		for (Persona persona : personas) {
			gamerTags.add(persona.getGamerTag());
		}
		HashMap<String, String> gamesMap = new HashMap<String, String>();
		for (Game game : games) {
			reportsMap.put(game.getName(), 0);
			gamesMap.put(game.getId(), game.getName());
		}
		for (Score score : scores) {
			reportsMap.put(gamesMap.get(score.getGameId()), reportsMap.get(gamesMap.get(score.getGameId())) + 1);
		}
		for (Score score : scores) {
			System.out.println(score.toString());
		}
		Set set = reportsMap.entrySet();
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			Map.Entry mentry = (Map.Entry) itr.next();
			reports.add(mentry.getKey() + " " + Integer.toString((int) mentry.getValue()));
			System.out.println(mentry.getKey() + " " + mentry.getValue());
		}

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		JMenu mnLists = new JMenu("Lists");
		menuBar.add(mnLists);

		JMenuItem mntmPlayers = new JMenuItem("Players");
		mntmPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				try {
					PlayersDialog dialog = new PlayersDialog(players);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnLists.add(mntmPlayers);

		JMenuItem mntmPersonas = new JMenuItem("Personas");
		mntmPersonas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				try {
					PersonasDialog dialog = new PersonasDialog(personas);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnLists.add(mntmPersonas);

		JMenuItem mntmScores = new JMenuItem("Scores");
		mntmScores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				try {
					ScoresDialog dialog = new ScoresDialog(scores);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnLists.add(mntmScores);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		JMenuItem mntmTotal = new JMenuItem("Total");

		mntmTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				try {
					System.out.println("test");
					ReportsDialog dialog = new ReportsDialog(reports);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnReports.add(mntmTotal);

		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Descending");
		chckbxmntmNewCheckItem.setSelected(true);
		mnReports.add(chckbxmntmNewCheckItem);

		JMenuItem mntmByGame = new JMenuItem("By Game");
		mntmByGame.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent ev) {

				try {
					Collections.sort((List<String>) reports, new Comparator<String>() {
						@Override
						public int compare(String s1, String s2) {
							return chckbxmntmNewCheckItem.isSelected() ? s1.compareToIgnoreCase(s2) : s2.compareToIgnoreCase(s1);
						}
					});
					System.out.println("test");
					ReportsDialog dialog = new ReportsDialog(reports);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnReports.add(mntmByGame);

		JMenuItem mntmByCount = new JMenuItem("By Count");
		mntmByCount.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent ev) {

				try {
					reports.clear();
					List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(reportsMap.entrySet());
					Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
						@Override
						public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
							return chckbxmntmNewCheckItem.isSelected() ? a.getValue().compareTo(b.getValue()) : b.getValue().compareTo(a.getValue());
						}
					});
					Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
					for (Map.Entry<String, Integer> entry : entries) {
						sortedMap.put(entry.getKey(), entry.getValue());
					}
					Iterator itr = sortedMap.entrySet().iterator();
					while (itr.hasNext()) {
						Map.Entry mentry = (Map.Entry) itr.next();
						reports.add(mentry.getKey() + " " + Integer.toString((int) mentry.getValue()));
						System.out.println(mentry.getKey() + " " + mentry.getValue());
					}
					System.out.println("test");
					ReportsDialog dialog = new ReportsDialog(reports);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnReports.add(mntmByCount);

		JMenuItem mntmGamertag = new JMenuItem("Gamertag");
		mntmGamertag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				try {

					String str = JOptionPane.showInputDialog("Gamertag: ");
					if (gamerTags.contains(str)) {
						/*
						 * System.out.println("hi");
						 * for (Score score : scores) {
						 * System.out.println("hi2");
						 * System.out.println(gamerTags.get(Integer.parseInt(score.getPersonaId()) - 1));
						 * if (str.equals(gamerTags.get(Integer.parseInt(score.getPersonaId()) - 1))) {
						 * System.out.println("hi4");
						 * reportsMap.put(gamesMap.get(score.getGameId()), reportsMap.get(gamesMap.get(score.getGameId())) - 1);
						 * }
						 * }
						 * System.out.println("hi");
						 * gamerTags.remove(str);
						 */
						reportsMap.clear();
						for (Game game : games) {
							reportsMap.put(game.getName(), 0);
							gamesMap.put(game.getId(), game.getName());
						}
						for (Score score : scores) {
							if (str.equals(gamerTags.get(Integer.parseInt(score.getPersonaId()) - 1))) {
								reportsMap.put(gamesMap.get(score.getGameId()), reportsMap.get(gamesMap.get(score.getGameId())) + 1);
							}
						}
						reports.clear();
						Set set = reportsMap.entrySet();
						Iterator itr = set.iterator();
						while (itr.hasNext()) {
							Map.Entry mentry = (Map.Entry) itr.next();
							reports.add(mentry.getKey() + " " + Integer.toString((int) mentry.getValue()));
							System.out.println(mentry.getKey() + " " + mentry.getValue());
						}

					} else if (str.equals("")) {

						reportsMap.clear();
						for (Game game : games) {
							reportsMap.put(game.getName(), 0);
							gamesMap.put(game.getId(), game.getName());
						}
						for (Score score : scores) {
							reportsMap.put(gamesMap.get(score.getGameId()), reportsMap.get(gamesMap.get(score.getGameId())) + 1);
						}
						reports.clear();
						Set set = reportsMap.entrySet();
						Iterator itr = set.iterator();
						while (itr.hasNext()) {
							Map.Entry mentry = (Map.Entry) itr.next();
							reports.add(mentry.getKey() + " " + Integer.toString((int) mentry.getValue()));
							System.out.println(mentry.getKey() + " " + mentry.getValue());
						}
						gamerTags.clear();
						for (Persona persona : personas) {
							gamerTags.add(persona.getGamerTag());
						}
					} else {
						JOptionPane.showMessageDialog(MainFrame.this, "Invalid Gamertag", "Error!", JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnReports.add(mntmGamertag);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "GIS \n By Christopher Dean A01001690", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

}
