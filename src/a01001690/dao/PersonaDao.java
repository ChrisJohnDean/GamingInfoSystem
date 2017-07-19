/**
 * Project: a01001690Gis
 * File: PersonaDao.java
 * Date: Mar 26, 2017
 * Time: 5:14:17 AM
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
import a01001690.data.Persona;

/**
 * @author chrisdean A01001690
 *
 */
public class PersonaDao extends Dao {

	public static final String TABLE_NAME = "Persona";

	public PersonaDao() throws FileNotFoundException, IOException {
		super(Database.getTheInstance(), TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("CREATE TABLE %s(%s VARCHAR(2), %s VARCHAR(1), %s VARCHAR(20), %s VARCHAR(2))", tableName, Fields.ID,
				Fields.PLAYERID, Fields.GAMERTAG, Fields.PLATFORM);
		// System.out.println(createStatement);
		super.create(createStatement);
	}

	public void add(Persona persona) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();

			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s')", tableName, persona.getId(), persona.getPlayerId(),
					persona.getGamerTag(), persona.getPlatform());
			statement.executeUpdate(insertString);

		} finally {
			close(statement);
		}
	}

	public Persona readById(String id) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Persona persona = null;
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
				persona = new Persona();
				persona.setId(resultSet.getString(1));
				persona.setPlayerId(resultSet.getString(2));
				persona.setGamerTag(resultSet.getString(3));
				persona.setPlatform(resultSet.getString(4));
			}
		} finally {
			close(statement);
		}
		return persona;
	}

	public ArrayList<Persona> getPersonas() throws SQLException {
		Connection connection;
		Statement statement = null;
		ArrayList<Persona> personas = new ArrayList<>();

		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s", tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				String id = resultSet.getString("ID");
				String playerid = resultSet.getString("PLAYERID");
				String gamertag = resultSet.getString("GAMERTAG");
				String platform = resultSet.getString("PLATFORM");
				personas.add(new Persona.Builder(id, playerid).gamerTag(gamertag).platform(platform).build());
			}
		} finally {
			close(statement);
		}

		return personas;
	}

	public void update(Persona persona) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', WHERE %s='%s'", tableName, //
					Fields.PLAYERID, persona.getPlayerId(), Fields.GAMERTAG, persona.getGamerTag(), Fields.PLATFORM, persona.getPlatform(), Fields.ID,
					persona.getId());
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Persona persona) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.ID, persona.getId());
			// System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			// System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public enum Fields {

		ID(1), PLAYERID(2), GAMERTAG(3), PLATFORM(4);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
