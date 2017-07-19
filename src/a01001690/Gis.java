/**
 * Project: a01001690Gis
 * File: Gis.java
 * Date: Feb 19, 2017
 * Time: 11:24:42 PM
 */
package a01001690;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a01001690.dao.GameDao;
import a01001690.dao.PersonaDao;
import a01001690.dao.PlayerDao;
import a01001690.dao.ScoreDao;
import a01001690.data.ApplicationException;
import a01001690.data.Game;
import a01001690.data.Persona;
import a01001690.data.Player;
import a01001690.io.Reader;
import a01001690.ui.MainFrame;

/**
 * @author chrisdean A01001690
 *
 */
public class Gis {
	private static final String PLAYERS_DATA_FILENAME = "players.dat";
	private static final String PERSONAS_DATA_FILENAME = "personas.dat";
	private static final String GAME_DATA_FILENAME = "games.dat";
	private static final String SCORE_DATA_FILENAME = "scores.dat";
	private static List<Player> players;
	private static List<Persona> personas;
	private static Map<String, Game> gameMap;
	private File playerDataFile;
	private File personaDataFile;
	private File gameDataFile;
	private File scoreDataFile;
	private static Database database;
	public static final String DB_PROPERTIES_FILENAME = "db.properties";
	private final Properties dbProperties;
	private static PlayerDao playerDao;
	private static ScoreDao scoreDao;
	private static PersonaDao personaDao;
	private static GameDao gameDao;

	private Connection connection;

	public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);

		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger(Gis.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Instant startTime = Instant.now();
		LOG.info(startTime);
		File playerFile = new File(PLAYERS_DATA_FILENAME);
		File personaFile = new File(PERSONAS_DATA_FILENAME);
		File gameFile = new File(GAME_DATA_FILENAME);
		File scoreFile = new File(SCORE_DATA_FILENAME);
		File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);

		if (args.length > 4)
			throw new ApplicationException("Maximum 4 arguments");
		Gis gis = new Gis(playerFile, personaFile, gameFile, scoreFile, dbPropertiesFile);
		gis.run();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame(playerDao, personaDao, scoreDao, gameDao);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		database.shutdown();
		Instant endTime = Instant.now();
		LOG.info("Ended at " + endTime);
		LOG.info(String.format("Duration: %d ms", ChronoUnit.MILLIS.between(startTime, endTime)));
	}

	public Gis(File playerDataFile, File personaDataFile, File gameDataFile, File scoreDataFile, File dbPropertiesFile)
			throws FileNotFoundException, IOException {
		this.playerDataFile = playerDataFile;
		this.personaDataFile = personaDataFile;
		this.gameDataFile = gameDataFile;
		this.scoreDataFile = scoreDataFile;

		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));
		database = Database.getTheInstance();
		database.setProperties(dbProperties);
	}

	private void run() throws Exception {

		connection = database.getConnection();
		try {
			setTables();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void setTables() throws FileNotFoundException, IOException, SQLException {
		this.playerDao = Reader.readPlayer(playerDataFile);
		this.scoreDao = Reader.readScore(scoreDataFile);
		this.personaDao = Reader.readPersona(personaDataFile);
		this.gameDao = Reader.readGame(gameDataFile);
	}

}
