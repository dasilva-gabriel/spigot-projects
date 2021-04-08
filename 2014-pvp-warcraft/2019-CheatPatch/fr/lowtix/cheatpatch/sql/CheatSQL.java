package fr.lowtix.cheatpatch.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import fr.lowtix.cheatpatch.PlayerWrapper;
import fr.lowtix.cheatpatch.managers.CheatTopResult;

public class CheatSQL {
	
	private static final String TABLE_NAME = "users_kill";
	private static final String COLUMN_NAME = "username";
	private static final String COLUMN_KILLS = "kills";
	private static final String COLUMN_DEATHS = "deaths";
	
	public static void createTableIfNotExist() {
		MySQL.tryConnect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_NAME+" VARCHAR(20), "+COLUMN_KILLS+" INT(255), "+COLUMN_DEATHS+" INT(255))");
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean contains(String name) {
		return MySQL.contains(TABLE_NAME, COLUMN_NAME, name, true);
	}

	public static void setupWrapper(PlayerWrapper wrapper) {
		MySQL.tryConnect();
		try {
			
			if(MySQL.contains(TABLE_NAME, COLUMN_NAME, wrapper.getName(), false)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+" = ?");
				ps.setString(1, wrapper.getName());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					wrapper.setKills(result.getInt(COLUMN_KILLS));
					wrapper.setDeaths(result.getInt(COLUMN_DEATHS));
				}
				result.close();
				ps.close();
			} else {
				
				create(wrapper);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save(PlayerWrapper wrapper) {
		MySQL.tryConnect();
		try {
			
			if(MySQL.contains(TABLE_NAME, COLUMN_NAME, wrapper.getName(), false)) {
				
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE "+TABLE_NAME+" SET "+COLUMN_KILLS+" = ?, "+COLUMN_DEATHS+" = ? WHERE "+COLUMN_NAME+" = ?");
				ps.setInt(1, wrapper.getKills());;
				ps.setInt(2, wrapper.getDeaths());
				ps.setString(3, wrapper.getName());
				ps.execute();
				ps.close();
				
			} else {
				
				create(wrapper);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void create(PlayerWrapper wrapper) {
		MySQL.tryConnect();
		PreparedStatement ps;
		try {
			ps = MySQL.getConnection().prepareStatement("INSERT INTO " + TABLE_NAME + " values(?, ?, ?)");
			ps.setString(1, wrapper.getName());
			ps.setInt(2, 0);
			ps.setInt(3, 0);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static LinkedList<CheatTopResult> getTop() {
		MySQL.tryConnect();
		LinkedList<CheatTopResult> res = new LinkedList<CheatTopResult>();
		int i = 0;
		try {
			
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+TABLE_NAME+" ORDER BY "+COLUMN_KILLS+" DESC");
			ResultSet result = ps.executeQuery();
			
			while (result.next() && i <= 20) {
				String name = result.getString(COLUMN_NAME);
				int kills = result.getInt(COLUMN_KILLS);
				res.addLast(new CheatTopResult(name, kills));
				i++;
			}
			
			result.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
