package fr.lowtix.warcore.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import fr.lowtix.warcore.WarCore;

public class MySQL {
	
	private static String url = "jdbc:mysql://";
	private static String host = WarCore.getInstance().getConfig().getString("host") + "/";
	private static String base = WarCore.getInstance().getConfig().getString("database");
	private static String username = WarCore.getInstance().getConfig().getString("username");
	private static String password = WarCore.getInstance().getConfig().getString("password");
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
	
	public static void prepareStatement(String statement) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement(statement);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}
	
	public static boolean contains(String table, String column, String arg, boolean connect, boolean disconnect) {
		if(connect) {
			MySQL.connect();
		}
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+table+" WHERE "+column+" = ?");
			ps.setString(1, arg);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(disconnect) {
				MySQL.disconnect();
			}
		}
		return false;
	}

}
