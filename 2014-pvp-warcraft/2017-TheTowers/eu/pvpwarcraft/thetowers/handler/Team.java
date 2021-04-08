package eu.pvpwarcraft.thetowers.handler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.thetowers.TheTowers;

public class Team {
	
	private String name;
	private int points;
	private Location spawn;
	private ChatColor color;
	private Color leatherColor;

	public Team(String name, int points, Location spawn, ChatColor color, Color leatherColor) {
		this.name = name;
		this.points = points;
		this.spawn = spawn;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public Location getSpawn() {
		return spawn;
	}

	public ChatColor getColor() {
		return color;
	}

	public Color getLeatherColor() {
		return leatherColor;
	}
	
	public List<Player> getPlayers() {
		List<Player> result = new ArrayList<>();
		
		for(Player all : Bukkit.getOnlinePlayers()){
			TowerPlayer alltp = TheTowers.getPlayer(all);
			if(TheTowers.getTeam(alltp.getTeam()).equals(this)){
				result.add(all);
			}
		}
		return result;
	}
	
	public String getPlayersList(ChatColor chatColor) {
		String result = "";
		List<Player> players = getPlayers();
		for(int i = 0; i < getPlayers().size(); i++){
			result += "§f, "+ chatColor + players.get(i).getName();
		}
		return result;
	}
	
	public static enum Teams {
		
		ORANGE("Orange", 0, new Location(Bukkit.getWorld("world"), 84.500, 192, 1152.500, 90, -4), ChatColor.GOLD, Color.ORANGE), 
		CYAN("Cyan", 0, new Location(Bukkit.getWorld("world"), -83.500, 192, 1152.500, -90, -4), ChatColor.DARK_AQUA, Color.AQUA),
		AUCUNE("Aucune", 0, new Location(Bukkit.getWorld("world"), 20.500, 65, 992.500, 180, 0), ChatColor.WHITE, Color.WHITE);

		private String name;
		private int points;
		private Location spawn;
		private ChatColor color;
		private Color leatherColor;
		
		private Teams(String name, int points, Location spawn, ChatColor color, Color leatherColor) {
			this.name = name;
			this.points = points;
			this.spawn = spawn;
			this.color = color;
			this.leatherColor = leatherColor;
		}
		
		public String getName() {
			return name;
		}
		public int getPoints() {
			return points;
		}
		public Location getSpawn() {
			return spawn;
		}
		public ChatColor getColor() {
			return color;
		}
		public Color getLeatherColor() {
			return leatherColor;
		}
		
		
		
	}

}
