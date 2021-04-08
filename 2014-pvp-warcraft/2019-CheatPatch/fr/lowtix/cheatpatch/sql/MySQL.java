package fr.lowtix.cheatpatch.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import fr.lowtix.cheatpatch.CheatPatch;

public class MySQL {
	
	private static String url = "jdbc:mysql://";
	private static String host = CheatPatch.getInstance().getConfig().getString("host") + "/";
	private static String base = CheatPatch.getInstance().getConfig().getString("database");
	private static String username = CheatPatch.getInstance().getConfig().getString("username");
	private static String password = CheatPatch.getInstance().getConfig().getString("password");
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

	public static void tryConnect() {
		if (!isConnected())
			try {
				connection = DriverManager.getConnection(url + host + base, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void prepareStatement(String statement) {
		tryConnect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement(statement);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean contains(String table, String column, String arg, boolean connect) {
		if(connect) {
			tryConnect();
		}
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+table+" WHERE "+column+" = ?");
			ps.setString(1, arg);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}

}
