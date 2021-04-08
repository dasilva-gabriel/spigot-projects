package fr.lowtix.warcore.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.enums.Ranks;

public class PlayersSQL {
	
	private String usersTable = "users";
	private String nameColumn = "username";
	private String ranksColumn = "rank";
	private String levelsColumn = "level";
	private String pointsColumn = "points";
	private String gemmesColumn = "gemmes";
	private String killsColumn = "kills";
	private String deathsColumn = "deaths";
	private String streakColumn = "streak";
	private String infoColumn = "info";
	
	public void createTable() {
		MySQL.prepareStatement("CREATE TABLE IF NOT EXISTS "+usersTable+" ("+nameColumn+" VARCHAR(50), "+ranksColumn+" VARCHAR(50), "+levelsColumn+" VARCHAR(50), "+pointsColumn+" DECIMAL(65), "+gemmesColumn+" DECIMAL(65), "+killsColumn+" DECIMAL(65), "+deathsColumn+" DECIMAL(65), "+streakColumn+" DECIMAL(65), "+infoColumn+" TEXT(4000))");
	}
	
	public void saveUser(WarPlayer player) {
		MySQL.connect();
		try {
			if(MySQL.contains(usersTable, nameColumn, player.getSqlName(), false, false)){
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE "+usersTable+" SET "+ranksColumn+" = ?, "+levelsColumn+" = ?, "+pointsColumn+" = ?, "+gemmesColumn+" = ?, "+killsColumn+" = ?, "+deathsColumn+" = ?, "+streakColumn+" = ?, "+infoColumn+" = ? WHERE "+nameColumn+" = ?");
				ps.setString(1, player.getRank().name());
				ps.setString(2, player.getLevel().name());
				ps.setDouble(3, player.getPoints());
				ps.setDouble(4, player.getGemmes());
				ps.setDouble(5, player.getKills());
				ps.setDouble(6, player.getDeaths());
				ps.setDouble(7, player.getStreak());
				ps.setString(8, player.getPlayerStats().getSerial());
				ps.setString(9, player.getSqlName());
				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}
	
	public void loadUser(WarPlayer player) {
		MySQL.connect();
		try {
			if(!MySQL.contains(usersTable, nameColumn, player.getSqlName(), false, false)){
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO "+usersTable+" values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, player.getSqlName());
				ps.setString(2, player.getRank().name());
				ps.setString(3, player.getLevel().name());
				ps.setDouble(4, player.getPoints());
				ps.setDouble(5, player.getGemmes());
				ps.setDouble(6, player.getKills());
				ps.setDouble(7, player.getDeaths());
				ps.setDouble(8, player.getStreak());
				ps.setString(9, player.getPlayerStats().getSerial());
				ps.execute();
				ps.close();
			}
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+usersTable+" WHERE "+nameColumn+" = ?");
			ps.setString(1, player.getSqlName());
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Ranks rank = Ranks.getRankFromName(result.getString(this.ranksColumn));
				Levels level = Levels.getLevelFromName(result.getString(this.levelsColumn));
				double gemmes = result.getDouble(this.gemmesColumn);
				double points = result.getDouble(this.pointsColumn);
				int kills = result.getInt(this.killsColumn);
				int deaths = result.getInt(this.deathsColumn);
				int streak = result.getInt(this.streakColumn);
				String serial = result.getString(this.infoColumn);
				player.setRank(rank);
				player.setLevel(level);
				player.setGemmes(gemmes);
				player.setPoints(points);
				player.setKills(kills);
				player.setDeaths(deaths);
				player.setStreak(streak);
				player.getPlayerStats().loadByString(serial);
			}
			result.close();
			ps.close();
			WarCore.getInstance().getLogger().info(">>> "+player.getPlayer().getName()+" = ProfLoaded by SQL");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}
	
	public HashMap<String, Integer> getTopKills() {
		MySQL.connect();
		int i = 0;
		HashMap<String, Integer> top = new HashMap<String, Integer>();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+usersTable+" ORDER BY "+killsColumn+" DESC");
			ResultSet result = ps.executeQuery();
			top.clear();
			while (result.next() && i <= 20) {
				String name = result.getString("name");
				top.put(name, i);
				i++;
			}
			result.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySQL.disconnect();
		}
		return top;
	}

}
