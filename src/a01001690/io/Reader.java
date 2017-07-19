/**
 * Project: a01001690Gis
 * File: reader.java
 * Date: Feb 24, 2017
 * Time: 1:54:55 AM
 */
package a01001690.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import a01001690.dao.GameDao;
import a01001690.dao.PersonaDao;
import a01001690.dao.PlayerDao;
import a01001690.dao.ScoreDao;
import a01001690.data.Game;
import a01001690.data.Persona;
import a01001690.data.Player;
import a01001690.data.Score;

/**
 * @author chrisdean A01001690
 *
 */
public class Reader {

	/**
	 * prevents compiler from making a default constructor
	 */
	private Reader() {
	};

	public static List<String[]> readData(File dataFile) {
		List<String[]> data = new ArrayList<>();
		BufferedReader br;
		FileReader fr;
		String line;

		try {
			fr = new FileReader(dataFile);
			br = new BufferedReader(fr);
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split("\\|");
				data.add(dataArray);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		return data;
	}

	public static PlayerDao readPlayer(File playerDataFile) throws FileNotFoundException, IOException, SQLException {
		PlayerDao playerDao = new PlayerDao();
		playerDao.drop();
		playerDao.create();

		BufferedReader br;
		FileReader fr;
		String line;

		try {
			fr = new FileReader(playerDataFile);
			br = new BufferedReader(fr);
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split("\\|");
				playerDao.add(new Player(dataArray[0], dataArray[3], dataArray[1], dataArray[2], dataArray[4]));
				// System.out.println(line);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return playerDao;
	}

	public static GameDao readGame(File gameDataFile) throws FileNotFoundException, IOException, SQLException {

		GameDao gameDao = new GameDao();
		gameDao.drop();
		gameDao.create();
		BufferedReader br;
		FileReader fr;
		String line;

		try {
			fr = new FileReader(gameDataFile);
			br = new BufferedReader(fr);
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split("\\|");
				for (String s : dataArray) {
					// System.out.println(s);
				}
				gameDao.add(new Game.Builder(dataArray[0], dataArray[1], dataArray[2]).build());

				// System.out.println("start");
				// playerDao.add(new Player(dataArray[0], dataArray[3], dataArray[1], dataArray[2], dataArray[4]));
				// System.out.println(line);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return gameDao;
	}

	public static PersonaDao readPersona(File personaDataFile) throws FileNotFoundException, IOException, SQLException {

		PersonaDao personaDao = new PersonaDao();
		personaDao.drop();
		personaDao.create();
		BufferedReader br;
		FileReader fr;
		String line;

		try {
			fr = new FileReader(personaDataFile);
			br = new BufferedReader(fr);
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split("\\|");
				for (String s : dataArray) {
					// System.out.println(s);
				}
				personaDao.add(new Persona.Builder(dataArray[0], dataArray[1]).gamerTag(dataArray[2]).platform(dataArray[3]).build());

				// System.out.println("start");
				// playerDao.add(new Player(dataArray[0], dataArray[3], dataArray[1], dataArray[2], dataArray[4]));
				// System.out.println(line);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return personaDao;
	}

	public static ScoreDao readScore(File scoreDataFile) throws FileNotFoundException, IOException, SQLException {

		ScoreDao scoreDao = new ScoreDao();
		scoreDao.drop();
		scoreDao.create();
		BufferedReader br;
		FileReader fr;
		String line;

		try {
			fr = new FileReader(scoreDataFile);
			br = new BufferedReader(fr);
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] dataArray = line.split("\\|");
				scoreDao.add(new Score.Builder(dataArray[0], dataArray[1], dataArray[2]).build());
				// playerDao.add(new Player(dataArray[0], dataArray[3], dataArray[1], dataArray[2], dataArray[4]));
				// System.out.println(line);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return scoreDao;
	}
	// List<Player> players = new ArrayList<>();
	// List<String[]> data = readData(playerDataFile);
	//
	// for (String[] s : data) {
	// try {
	// players.add(new Player.Builder(Integer.parseInt(s[0]), Validator.validateEmail(s[3])).firstName(s[1]).lastName(s[2])
	// .birthDate(Integer.parseInt(s[4])).build());
	// } catch (ApplicationException e) {
	// System.out.println(e.getMessage());
	// System.exit(-1);
	// }
	// }
	// return players;

	//
	// public static void readPersona(List<Player> players, File personaDataFile) {
	// List<String[]> data = readData(personaDataFile);
	//
	// for (String[] s : data) {
	// players.get(Integer.parseInt(s[1]) - 1)
	// .addPersona(new Persona.Builder(Integer.parseInt(s[0]), Integer.parseInt(s[1])).gamerTag(s[2]).platform(s[3]).build());
	// }
	// }
	//
	// public static Map<String, Game> readGame(File gameDataFile) {
	// List<String[]> data = readData(gameDataFile);
	// Map<String, Game> gameMap = new HashMap<>();
	//
	// for (String[] s : data) {
	// gameMap.put(s[0], new Game.Builder(s[0], s[1], s[2]).build());
	// }
	// return gameMap;
	// }
	//
	// public static void readScore(List<Player> players, File scoreDataFile) {
	// List<String[]> data = readData(scoreDataFile);
	//
	// Map<Integer, Player> personaPlayer = new HashMap<>();
	//
	// for (int i = 0; i < players.size(); i++) {
	// for (int j = 0; j < players.get(i).getPersonas().size(); j++) {
	// personaPlayer.put(players.get(i).getPersonas().get(j).getId(), players.get(i));
	// }
	// }
	//
	// for (String[] s : data) {
	// if (s[2].equals("WIN"))
	// personaPlayer.get(Integer.parseInt(s[0])).getPersonaById(Integer.parseInt(s[0]))
	// .addScore(new Score.Builder(s[0], s[1], true).build());
	// else
	// personaPlayer.get(Integer.parseInt(s[0])).getPersonaById(Integer.parseInt(s[0]))
	// .addScore(new Score.Builder(s[0], s[1], false).build());
	// }
	// }

}
