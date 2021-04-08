package eu.pvpwarcraft.warfactions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import eu.pvpwarcraft.warfactions.listeners.Listeners;

public class WarFactions extends JavaPlugin {
	
	private static WarFactions instance;
	public static WarFactions getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		Bukkit.broadcastMessage("§3Factions §8» §7Démarrage du serveur...");
		
		Listeners.init(getServer().getPluginManager());
	}

}
