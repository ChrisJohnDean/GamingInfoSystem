
package a01001690;

/**
 * File: Database.java
 * Date: 2012-10-28
 * Time: 12:26:04 PM
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author scirka
 * 
 */
public class Database {

	public static final String DB_DRIVER_KEY = "db.driver";
	public static final String DB_URL_KEY = "db.url";
	public static final String DB_USER_KEY = "db.user";
	public static final String DB_PASSWORD_KEY = "db.password";

	public static Database theInstance;

	private static Logger LOG = LogManager.getLogger(Database.class);

	private static Connection connection;
	private Properties properties;

	public Database() {
		LOG.debug("Loading database properties from db.properties");
	}

	public static Database getTheInstance() throws FileNotFoundException, IOException {
		if (theInstance == null) {
			theInstance = new Database();
		}
		return theInstance;
	}

	public void setProperties(Properties properties) throws FileNotFoundException {
		this.properties = properties;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DB_DRIVER_KEY));
		System.out.println("Driver loaded");
		// System.out.println(properties.getProperty(DB_USER_KEY));
		connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY), properties.getProperty(DB_USER_KEY),
				properties.getProperty(DB_PASSWORD_KEY));
		// connection = DriverManager.getConnection(DB_URL_KEY, DB_USER_KEY, DB_PASSWORD_KEY);
		System.out.println("Database connected");
	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean tableExists(String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = null;
		String rsTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTableName = resultSet.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}

		return false;
	}

}
