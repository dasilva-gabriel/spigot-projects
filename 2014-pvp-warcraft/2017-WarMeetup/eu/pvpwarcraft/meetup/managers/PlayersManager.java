package eu.pvpwarcraft.meetup.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class PlayersManager {
	
	public static Map<String, Integer> kills = new HashMap<String, Integer>();
	public static Map<String, Integer> damage_percent = new HashMap<String, Integer>();
	private static Map<String, Integer> damage_received = new HashMap<String, Integer>();
	private static Map<String, Integer> damage_given = new HashMap<String, Integer>();
	
	public static void initPlayer(Player player){
		if(!kills.containsKey(player.getName())){
			kills.put(player.getName(), 0);
		}
		if(!damage_received.containsKey(player.getName())){
			damage_received.put(player.getName(), 0);
		}
		if(!damage_given.containsKey(player.getName())){
			damage_given.put(player.getName(), 0);
		}
		if(!damage_received.containsKey(player.getName())){
			damage_received.put(player.getName(), 0);
		}
	}
	
	public static int getKills(Player player){
		initPlayer(player);
		return kills.get(player.getName());
	}
	
	public static void addKill(Player player){
		initPlayer(player);
		kills.put(player.getName(), (getKills(player)+1));
	}
	
	public static int getDR(Player player){
		initPlayer(player);
		return damage_received.get(player.getName());
	}
	
	public static void addDR(Player player, int damage){
		initPlayer(player);
		damage_received.put(player.getName(), (getDR(player)+damage));
	}
	
	public static int getDG(Player player){
		initPlayer(player);
		return damage_given.get(player.getName());
	}
	
	public static void addDG(Player player, int damage){
		initPlayer(player);
		damage_given.put(player.getName(), (getDG(player)+damage));
	}
	
	public static int getPercent(Player player){
		int received = getDR(player);
		int given = getDG(player);
		int percent = 0;
		int max = given + received;
		if(max > 0){
			percent = (given*100)/max;
		}
		return percent;
	}

}
