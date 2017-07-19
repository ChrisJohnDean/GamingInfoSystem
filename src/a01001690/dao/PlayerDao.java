/**
 * Project: a01001690Gis
 * File: PlayerDao.java
 * Date: Mar 4, 2017
 * Time: 11:02:06 PM
 */
package a01001690.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import a01001690.Database;
import a01001690.data.ApplicationException;
import a01001690.data.Player;

public class PlayerDao extends Dao {

	public static final String TABLE_NAME = "Player";

	public PlayerDao() throws FileNotFoundException, IOException {
		super(Database.getTheInstance(), TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format(
				"CREATE TABLE %s(%s VARCHAR(2), %s VARCHAR(10), %s VARCHAR(10), %s VARCHAR(30), %s VARCHAR(9), primary key (%s) )", tableName,
				Fields.ID, Fields.FIRSTNAME, Fields.LASTNAME, Fields.EMAIL, Fields.BIRTHDATE, Fields.ID);
		// System.out.println(createStatement);
		super.create(createStatement);
	}

	public void add(Player player) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s')", tableName, player.getId(),
					player.getFirstName(), player.getLastName(), player.getEmail(), player.getBirthDate());
			statement.executeUpdate(insertString);
			// System.out.println(insertString);
		} finally {
			close(statement);
		}
	}

	public List<String> getiDs() throws SQLException {
		Connection connection;
		Statement statement = null;
		List<String> iDs = new ArrayList<>();

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s", tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				iDs.add(resultSet.getString("ID"));
			}
		} finally {
			close(statement);
		}

		// LOG.debug(String.format("Loaded %d gamer tags from the database", gamertags.size()));

		return iDs;
	}

	public Player getPlayer(String id) throws SQLException, ApplicationException {
		Connection connection;
		Statement statement = null;
		Player player = null;

		try {

			connection = database.getConnection();
			String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.ID, id);
			// System.out.println(sqlString);

			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);

			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new ApplicationException(String.format("Expected one result, got %d", count));
				}

				player = new Player();
				player.setId(resultSet.getString(1));
				player.setFirstName(resultSet.getString(2));
				player.setLastName(resultSet.getString(3));
				player.setEmail(resultSet.getString(4));
				player.setBirthDate(resultSet.getString(5));
				// System.out.println(resultSet.getString(5));

			}
		} finally {
			close(statement);
		}
		return player;
	}

	public Player readById(String id) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Player player = null;
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
				player = new Player();
				player.setId(resultSet.getString(1));
				player.setFirstName(resultSet.getString(2));
				player.setLastName(resultSet.getString(3));
				player.setEmail(resultSet.getString(4));
				player.setBirthDate(resultSet.getString(5));
			}
		} finally {
			close(statement);
		}
		return player;
	}

	public void update(Player player) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'", tableName, //
					Fields.ID, player.getId(), Fields.FIRSTNAME, player.getFirstName(), Fields.LASTNAME, player.getLastName(), //
					Fields.EMAIL, player.getEmail(), Fields.BIRTHDATE, player.getBirthDate()); //
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Player player) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.ID, player.getId());
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public enum Fields {

		ID(1), FIRSTNAME(2), LASTNAME(3), EMAIL(4), BIRTHDATE(5);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}
}
