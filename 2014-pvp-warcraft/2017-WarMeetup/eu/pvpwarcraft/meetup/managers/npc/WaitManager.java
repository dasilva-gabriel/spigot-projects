package eu.pvpwarcraft.meetup.managers.npc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;

public class WaitManager implements Listener {
	
	public static Map<String, Location> locspawn = new HashMap<String, Location>();
	
	public static void setRandomLocation(Player player){
		int x = 0;
		int z = 0;
		Random rand = new Random();
		x = rand.nextInt(10 * PlayersInGameManager.getPlayersSize());
		Random rand2 = new Random();
		z = rand2.nextInt(10 * PlayersInGameManager.getPlayersSize());
		Location randLoc = new Location(Bukkit.getWorld("world"), x, 0, z);
		locspawn.put(player.getName(), randLoc);
	}
	
	public static Location getRandomLocation(Player player){
		if(!locspawn.containsKey(player.getName())){
			setRandomLocation(player);
		}
		Location loc = locspawn.get(player.getName());
		return loc;
	}
	
	public static void teleportPlayer(Player player){
		Location randLoc = getRandomLocation(player);
		int x = (int) randLoc.getX();
		int z = (int) randLoc.getZ();
		int y = (int) randLoc.getWorld().getHighestBlockYAt(x, z) + 20;
		Location tpLoc = new Location(Bukkit.getWorld("world"), x, y, z);
		if(player.getLocation().getBlockX() != x || player.getLocation().getBlockZ() != z){
			player.teleport(tpLoc);
		}
	}

}
