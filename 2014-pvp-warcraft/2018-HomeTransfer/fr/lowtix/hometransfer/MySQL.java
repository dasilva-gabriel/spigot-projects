package fr.lowtix.hometransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MySQL {
	
	private static String url = "jdbc:mysql://";
	private static String host = HomeTransfer.getInstance().getConfig().getString("host") + "/";
	private static String base = HomeTransfer.getInstance().getConfig().getString("database");
	private static String username = HomeTransfer.getInstance().getConfig().getString("username");
	private static String password = HomeTransfer.getInstance().getConfig().getString("password");
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
	
	public static void update(String table, String condition_column, String condition_arg, LinkedHashMap<String, String> args, boolean connect) {
		if(connect) {
			tryConnect();
		}
		try {
			if(contains(table, condition_column, condition_arg, false)){
				
				String req = "UPDATE " + table + " SET ";
				
				int i = args.size();
				for(String s : args.keySet()) {
					i--;
					
					req += s + " = \"" + args.get(s) + "\"";
					
					if(i != 0) {
						req += ",";
					}
					
					req += " ";
				}
				
				req += "WHERE " + condition_column + " = \"" + condition_arg + "\"";
				
				PreparedStatement ps = getConnection().prepareStatement(req);

				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void insert(String table, LinkedList<String> args, boolean connect) {
		if(connect) {
			tryConnect();
		}
		try {
			
			String req = "INSERT INTO " + table + " values(";
			
			int i = args.size();
			for(String s : args) {
				i--;
				
				req += "\"" + s + "\"";
				
				if(i != 0) {
					req += ",";
					req += " ";
				}
				
			}
			
			req += ")";
			
			PreparedStatement ps = getConnection().prepareStatement(req);

			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void createTable(String table, LinkedHashMap<String, String> args, boolean connect) {
		if (connect) {
			tryConnect();
		}
		try {

			String req = "CREATE TABLE IF NOT EXISTS " + table + " (";

			int i = args.size();
			for (String s : args.keySet()) {
				i--;

				req += s + " " + args.get(s);

				if (i != 0) {
					req += ",";
					req += " ";
				}

			}

			req += ");";

			PreparedStatement ps = getConnection().prepareStatement(req);

			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
