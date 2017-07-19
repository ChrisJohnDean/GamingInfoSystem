/**
 * Project: a01001690Gis
 * File: GameDao.java
 * Date: Mar 26, 2017
 * Time: 7:02:47 AM
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
import a01001690.data.Game;

/**
 * @author chrisdean A01001690
 *
 */
public class GameDao extends Dao {
	public static final String TABLE_NAME = "Game";

	public GameDao() throws FileNotFoundException, IOException {
		super(Database.getTheInstance(), TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("CREATE TABLE %s(%s VARCHAR(4), %s VARCHAR(20), %s VARCHAR(20))", tableName, Fields.ID, Fields.NAME,
				Fields.PRODUCER);
		System.out.println(createStatement);
		super.create(createStatement);
	}

	public void add(Game game) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();

			String insertString = String.format("insert into %s values('%s', '%s', '%s')", tableName, game.getId(), game.getName(),
					game.getProducer());
			statement.executeUpdate(insertString);

		} finally {
			close(statement);
		}
	}

	public Game readById(String id) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Game game = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.ID, id);
			ResultSet resultSet = statement.executeQuery(sqlString);
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new Exception(String.format("Expected one result, got %d", count));
				}
				game = new Game();
				game.setId(resultSet.getString(1));
				game.setName(resultSet.getString(2));
				game.setProducer(resultSet.getString(3));
			}
		} finally {
			close(statement);
		}
		return game;
	}

	public ArrayList<Game> getGames() throws SQLException {
		Connection connection;
		Statement statement = null;
		ArrayList<Game> games = new ArrayList<>();

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s", tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				String id = resultSet.getString("ID");
				String name = resultSet.getString("NAME");
				String producer = resultSet.getString("PRODUCER");
				games.add(new Game.Builder(id, name, producer).build());
			}
		} finally {
			close(statement);
		}

		return games;
	}

	public void update(Game game) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', WHERE %s='%s'", tableName, //
					Fields.NAME, game.getName(), Fields.PRODUCER, game.getProducer(), Fields.ID, game.getId(), Fields.ID);
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Game game) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.ID, game.getId());
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public enum Fields {

		ID(1), NAME(2), PRODUCER(3);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
