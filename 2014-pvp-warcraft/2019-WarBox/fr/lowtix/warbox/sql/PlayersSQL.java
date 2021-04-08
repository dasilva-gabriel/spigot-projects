package fr.lowtix.warbox.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.Bukkit;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.events.UserFirstJoinEvent;
import fr.lowtix.warbox.players.WarPlayer;

public class PlayersSQL {
	
	private final String table_name = "warusers";
	private final String column_name = "username";
	private final String column_exp = "experience";
	private final String column_level = "level";
	private final String column_coins = "coins";
	private final String column_stats = "stats";
	
	public void createTable() {
		MySQL.prepareStatement("CREATE TABLE IF NOT EXISTS "+table_name+" ("+column_name+" VARCHAR(16), "+column_exp+" INT(50), "+column_level+" INT(50), "+column_coins+" INT(50), "+column_stats+" TEXT);");
	}
	
	public void saveUser(WarPlayer player) {
		MySQL.tryConnect();
		try {
			if(MySQL.contains(table_name, column_name, player.getName(), false)){
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE "+table_name+" SET "+column_exp+" = ?, "+column_level+" = ?, "+column_coins+" = ?, "+column_stats+" = ? WHERE "+column_name+" = ?");
				ps.setInt(1, player.getExp());
				ps.setInt(2, player.getLevel());
				ps.setInt(3, player.getCoins());
				ps.setString(4, player.getStats().getSerial());
				ps.setString(5, player.getName());
				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean loadUser(WarPlayer player) {
		MySQL.tryConnect();
		try {
			if(!MySQL.contains(table_name, column_name, player.getName(), false)){
				
				Bukkit.getPluginManager().callEvent(new UserFirstJoinEvent(player.getPlayer()));
				
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO "+table_name+" values(?, ?, ?, ?, ?)");
				ps.setString(1, player.getName());
				ps.setInt(2, player.getExp());
				ps.setInt(3, player.getLevel());
				ps.setInt(4, player.getCoins());
				ps.setString(5, player.getStats().getSerial());
				ps.execute();
				ps.close();
			} else {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+table_name+" WHERE "+column_name+" = ?");
				ps.setString(1, player.getName());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					player.setExp(result.getInt(column_exp));
					player.setCoins(result.getInt(column_coins));
					player.setLevel(result.getInt(column_level));
					player.getStats().loadBySerial(result.getString(column_stats));
				}
				result.close();
				ps.close();
			}
			
			WarBox.getInstance().getLogger().info(">>> "+player.getPlayer().getName()+" = ProfLoaded by SQL");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
