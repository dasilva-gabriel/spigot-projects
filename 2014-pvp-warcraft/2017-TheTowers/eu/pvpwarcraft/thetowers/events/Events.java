package eu.pvpwarcraft.thetowers.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import eu.pvpwarcraft.thetowers.TheTowers;

public class Events {

	public static void init() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		pm.registerEvents(new PlayerJoin(), TheTowers.getInstance());
		pm.registerEvents(new BlockedEvents(), TheTowers.getInstance());
		pm.registerEvents(new LobbyEvents(), TheTowers.getInstance());
		pm.registerEvents(new GameEvents(), TheTowers.getInstance());
		pm.registerEvents(new PlayerChat(), TheTowers.getInstance());
		pm.registerEvents(new PlayerQuit(), TheTowers.getInstance());
		pm.registerEvents(new ServerPing(), TheTowers.getInstance());
		
	}
	
	

}
