package eu.pvpwarcraft.warfightapi.listeners;

import org.bukkit.Bukkit;

import eu.pvpwarcraft.warfightapi.WarFightAPI;

public class Listeners {
	
	public static void init(){
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), WarFightAPI.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(), WarFightAPI.getInstance());
	}

}
