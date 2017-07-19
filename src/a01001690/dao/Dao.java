/**
 * Project: a01001690lab7
 * File: Dao.java
 * Date: Mar 4, 2017
 * Time: 11:03:19 PM
 */
package a01001690.dao;

/**
 * 
 *
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a01001690.Database;
import a01001690.player.util.DbUtil;

public abstract class Dao {

	protected final Database database;
	protected final String tableName;

	protected Dao(Database database, String tableName) {
		this.database = database;
		this.tableName = tableName;
	}

	public abstract void create() throws SQLException;

	protected void create(String createStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(createStatement);
		} finally {
			close(statement);
		}
	}

	protected void add(String updateStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(updateStatement);
		} finally {
			close(statement);
		}
	}

	public void drop() throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			if (DbUtil.tableExists(connection, tableName)) {
				statement.executeUpdate("drop table " + tableName);
			}
		} finally {
			close(statement);
		}
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
