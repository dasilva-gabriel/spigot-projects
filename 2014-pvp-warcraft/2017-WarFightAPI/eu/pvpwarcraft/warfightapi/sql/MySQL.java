package eu.pvpwarcraft.warfightapi.sql;

import java.sql.Connection;
import java.sql.DriverManager;

import eu.pvpwarcraft.warfightapi.managers.Configuration;

public class MySQL {
	private static String url = "jdbc:mysql://";
	private static String host = Configuration.getSqlConfig().getString("host") + "/";
	private static String base = Configuration.getSqlConfig().getString("base");
	private static String username = Configuration.getSqlConfig().getString("username");
	private static String password = Configuration.getSqlConfig().getString("password");
	private static Connection connection;

	public static Connection getConnection() {
		return connection;
	}

	public static boolean isConnected() {
		try {
			if ((connection == null) || (connection.isClosed()))
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void connect() {
		if (!isConnected())
			try {
				connection = DriverManager.getConnection(url + host + base, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void disconnect() {
		if (isConnected())
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}