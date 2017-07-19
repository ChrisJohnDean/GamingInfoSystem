/**
 * Project: a01001690Gis
 * File: ScoreDao.java
 * Date: Mar 25, 2017
 * Time: 1:52:46 PM
 */
package a01001690.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import a01001690.Database;
import a01001690.data.Score;

/**
 * @author chrisdean A01001690
 *
 */
public class ScoreDao extends Dao {

	public static final String TABLE_NAME = "Score";

	public ScoreDao() throws FileNotFoundException, IOException {
		super(Database.getTheInstance(), TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("CREATE TABLE %s(%s VARCHAR(2), %s VARCHAR(10), %s VARCHAR(10))", tableName, Fields.PERSONAID,
				Fields.GAMEID, Fields.WIN);

		super.create(createStatement);
	}

	public void add(Score score) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s')", tableName, score.getPersonaId(), score.getGameId(),
					score.getWin());
			statement.executeUpdate(insertString);
			// System.out.println(insertString);
		} finally {
			close(statement);
		}
	}

	public ArrayList<Score> getScores() throws SQLException {
		Connection connection;
		Statement statement = null;
		ArrayList<Score> scores = new ArrayList<>();

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s", tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				String personaid = resultSet.getString("PERSONAID");
				String gameid = resultSet.getString("GAMEID");
				String win = resultSet.getString("WIN");
				scores.add(new Score.Builder(personaid, gameid, win).build());
			}
		} finally {
			close(statement);
		}

		return scores;
	}

	// public List<String> getGamertags() throws SQLException {
	// Connection connection;
	// Statement statement = null;
	// List<String> gamerTags = new ArrayList<>();
	//
	// try {
	// connection = database.getConnection();
	// statement = connection.createStatement();
	// String sqlString = String.format("SELECT * FROM %s", tableName);
	// ResultSet resultSet = statement.executeQuery(sqlString);
	//
	// while (resultSet.next()) {
	// gamerTags.add(resultSet.getString("GAMERTAG"));
	// }
	// } finally {
	// close(statement);
	// }
	//
	// // LOG.debug(String.format("Loaded %d gamer tags from the database", gamertags.size()));
	//
	// return gamerTags;
	// }

	public Score readByPersonaId(String id) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Score score = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.PERSONAID, id);
			ResultSet resultSet = statement.executeQuery(sqlString);
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					close(statement);
					// throw new Exception(String.format("Expected one result, got %d", count));
				}
				if (count < 1) {
					score = new Score.Builder(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)).build();
				}
			}
		} finally {
			close(statement);
		}
		return score;
	}

	public void update(Score score) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', WHERE %s='%s'", tableName, //
					Fields.PERSONAID, score.getPersonaId(), Fields.GAMEID, score.getGameId(), Fields.WIN, score.getWin());
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Score score) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.PERSONAID, score.getPersonaId());
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public enum Fields {

		PERSONAID(1), GAMEID(2), WIN(3);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
