package eu.pvpwarcraft.dayz.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import eu.pvpwarcraft.dayz.users.DayzPlayer;
import eu.pvpwarcraft.dayz.users.InGameRanks;

public class DayzSQL {

	public static void createTableIfNotExist() {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS users (name VARCHAR(20), capacities VARCHAR(255), statistics VARCHAR(255), igranks VARCHAR(255))");
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}

	public static DayzPlayer getUser(String name) {
		DayzPlayer user = null;
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name = ?");
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String capacities = result.getString("capacities");
				String statistics = result.getString("statistics");
				InGameRanks igrank = InGameRanks.valueOf(result.getString("igranks"));
				user = new DayzPlayer(name, 200, new DayzPlayer.PlayerCapacities().deserialize(capacities), new DayzPlayer.Statistics().deserialize(statistics), igrank);
			}
			result.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
		return user;
	}

	public static void createPlayer(String name) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO users values(?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, new DayzPlayer.PlayerCapacities().serialize());
			ps.setString(3, new DayzPlayer.Statistics().serialize());
			ps.setString(4, InGameRanks.RECRUE.toString());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}

	public static boolean containsPlayer(String name) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name = ?");
			ps.setString(1, name);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
		return false;
	}

	public static void registerPlayer(DayzPlayer user) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE users SET capacities = ?, statistics = ?, igranks = ? WHERE name = ?");
			ps.setString(1, user.getCapacities().serialize());
			ps.setString(2, user.getStats().serialize());
			ps.setString(3, user.getIgranks().toString());
			ps.setString(4, user.getName());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}

}
