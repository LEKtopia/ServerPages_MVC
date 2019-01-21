package edu.cvtc.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Project Skeleton
 *
 */
public class DBUtils {
	
	public static final String CONNECTION = "jdbc:sqlite:jsp.db";
	public static final int TIMEOUT = 30;

	private static final String DRIVER_NAME = "org.sqlite.JDBC";
	
	public static Connection createConnection(final String connection) throws ClassNotFoundException, SQLException {
		// register the driver 
		Class.forName(DRIVER_NAME);
		 
		// create a database connection
		return DriverManager.getConnection(connection);
	}
	
	public static void closeConnections(final Connection connection, final Statement statement) {
		try {
			if (null != connection) {
				connection.close();
			}
			if (null != statement) {
				statement.close();
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	
}
