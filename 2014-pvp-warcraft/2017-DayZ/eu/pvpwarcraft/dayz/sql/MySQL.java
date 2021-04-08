package eu.pvpwarcraft.dayz.sql;

import java.sql.Connection;
import java.sql.DriverManager;

import eu.pvpwarcraft.dayz.configuration.ConfigManager;

public class MySQL {
	private static String url = "jdbc:mysql://";
	private static String host = ConfigManager.getSqlConfig().getString("host") + "/";
	private static String base = ConfigManager.getSqlConfig().getString("base");
	private static String username = ConfigManager.getSqlConfig().getString("username");
	private static String password = ConfigManager.getSqlConfig().getString("password");
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