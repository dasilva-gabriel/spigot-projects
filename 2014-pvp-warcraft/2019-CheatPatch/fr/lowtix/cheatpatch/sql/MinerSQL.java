package fr.lowtix.cheatpatch.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.managers.miner.PlayerMiner;

public class MinerSQL {
	
	private static final String TABLE_NAME = "users_miner2";
	private static final String COLUMN_NAME = "username";
	private static final String COLUMN_LEVEL = "level";
	private static final String COLUMN_MONEY = "money_earned";
	private static final String COLUMN_EMERALD = "emerald_earned";
	private static final String COLUMN_KEYS = "keys_earned";
	private static final String COLUMN_TIME = "time";
	
	public static void createTableIfNotExist() {
		MySQL.tryConnect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_NAME+" VARCHAR(20), "+COLUMN_LEVEL+" INT(2), "+COLUMN_MONEY+" INT(255), "+COLUMN_EMERALD+" INT(255), "+COLUMN_KEYS+" INT(255), "+COLUMN_TIME+" INT(255))");
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean contains(String name) {
		return MySQL.contains(TABLE_NAME, COLUMN_NAME, name, true);
	}

	public static int[] hasMiner(Player player) {
		int[] res = new int[5];
		MySQL.tryConnect();
		try {
			if(MySQL.contains(TABLE_NAME, COLUMN_NAME, player.getName(), false)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+" = ?");
				ps.setString(1, player.getName());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					res[0] = result.getInt(COLUMN_LEVEL);
					res[1] = result.getInt(COLUMN_MONEY);
					res[2] = result.getInt(COLUMN_EMERALD);
					res[3] = result.getInt(COLUMN_KEYS);
					res[4] = result.getInt(COLUMN_TIME);
				}
				result.close();
				ps.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static void save(PlayerMiner miner) {
		MySQL.tryConnect();
		try {
			
			if(!MySQL.contains(TABLE_NAME, COLUMN_NAME, miner.getName(), false)) {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO "+TABLE_NAME+" values(?, ?, ?, ?, ?, ?)");
				ps.setString(1, miner.getName());
				ps.setInt(2, miner.getLevel());
				ps.setInt(3, miner.getMoney_earned());
				ps.setInt(4, miner.getEmerald_earned());
				ps.setInt(5, miner.getKeys_earned());
				ps.setInt(6, (int) miner.getTimer());
				ps.execute();
				ps.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(String name) {
		MySQL.tryConnect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+" = ?");
			ps.setString(1, name);
			ps.execute();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
