package eu.pvpwarcraft.warfightapi.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import eu.pvpwarcraft.warfightapi.managers.KitsManager;
import eu.pvpwarcraft.warfightapi.managers.User;

public class WarFightSQL {

	public static void createTableIfNotExist() {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS users (name VARCHAR(20), gemmes INT(25), meetup VARCHAR(255), firstjoin VARCHAR(255), timeplayed INT(255))");
			ps.execute();
			ps.close();
			
			for(KitsManager kits : KitsManager.values()){
				PreparedStatement ps2 = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS "+kits.name()+" (name VARCHAR(20), elo INT(6), wins INT(10), loses INT(10))");
				ps2.execute();
				ps2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySQL.disconnect();
		}
	}
	
	public static void createPlayer(User user) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO users values(?, ?, ?, ?, ?)");
			ps.setString(1, user.getName());
			ps.setInt(2, 0);
			ps.setString(3, new User.MeetupStatistics().serialize());
			ps.setString(4, "--");
			ps.setInt(5, 0);
			ps.execute();
			ps.close();
			for(KitsManager kits : KitsManager.values()){
				PreparedStatement ps2 = MySQL.getConnection().prepareStatement("INSERT INTO "+kits.name()+" values(?, ?, ?, ?)");
				ps2.setString(1, user.getName());
				ps2.setInt(2, 1200);
				ps2.setInt(3, 0);
				ps2.setInt(4, 0);
				ps2.close();
			}
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
	
	public static void initUser(User user){
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE name = ?");
			ps.setString(1, user.getName());
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				int gemmes = result.getInt("gemmes");
				int time_played = result.getInt("gemmes");
				String meetup = result.getString("meetup");
				String first_join = result.getString("firstjoin");
				user.setGemmes(gemmes);
				user.setTime_played(time_played);
				user.setFirst_join(first_join);
				user.getStats().deserialize(meetup);
			}
			for(KitsManager kits : KitsManager.values()){
				PreparedStatement ps2 = MySQL.getConnection().prepareStatement("SELECT * FROM "+kits.name()+" WHERE name = ?");
				ps2.setString(1, user.getName());
				ResultSet result2 = ps2.executeQuery();
				while (result2.next()) {
					int elo = result2.getInt("elo");
					int wins = result2.getInt("wins");
					int loses = result2.getInt("loses");
					user.getElos().regen(kits, elo, wins, loses);
				}
				result2.close();
				ps2.close();
			}
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void savePlayer(User user) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE users SET gemmes = ?, meetup = ?, firstjoin = ?, timeplayed = ? WHERE name = ?");
			ps.setInt(1, user.getGemmes());
			ps.setString(2, user.getStats().serialize());
			ps.setString(3, user.getFirst_join());
			ps.setInt(4, user.getTime_played());
			ps.setString(5, user.getName());
			ps.execute();
			ps.close();
			for(KitsManager kits : KitsManager.values()){
				PreparedStatement ps2 = MySQL.getConnection().prepareStatement("UPDATE "+kits.name()+" SET elo = ?, wins = ?, loses = ? WHERE name = ?");
				ps2.setInt(1, user.getElos().getElo(kits));
				ps2.setInt(2, user.getElos().getWins(kits));
				ps2.setInt(3, user.getElos().getLoses(kits));
				ps2.setString(4, user.getName());
				ps2.execute();
				ps2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}
	
	public static void regenElosFromSQL(User user) {
		MySQL.connect();
		try {
			for(KitsManager kits : KitsManager.values()){
				PreparedStatement ps2 = MySQL.getConnection().prepareStatement("SELECT * FROM "+kits.name()+" WHERE name = ?");
				ps2.setString(1, user.getName());
				ResultSet result2 = ps2.executeQuery();
				while (result2.next()) {
					int elo = result2.getInt("elo");
					int wins = result2.getInt("wins");
					int loses = result2.getInt("loses");
					user.getElos().regen(kits, elo, wins, loses);
				}
				result2.close();
				ps2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}
	
	public static void regenElosToSQL(User user) {
		MySQL.connect();
		try {
			for(KitsManager kits : KitsManager.values()){
				PreparedStatement ps2 = MySQL.getConnection().prepareStatement("UPDATE "+kits.name()+" SET elo = ?, wins = ?, loses = ? WHERE name = ?");
				ps2.setInt(1, user.getElos().getElo(kits));
				ps2.setInt(2, user.getElos().getWins(kits));
				ps2.setInt(3, user.getElos().getLoses(kits));
				ps2.setString(4, user.getName());
				ps2.execute();
				ps2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}

}
