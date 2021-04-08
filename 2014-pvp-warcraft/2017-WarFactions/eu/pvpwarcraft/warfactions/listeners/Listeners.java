package eu.pvpwarcraft.warfactions.listeners;

import org.bukkit.plugin.PluginManager;

import eu.pvpwarcraft.warfactions.WarFactions;

public class Listeners {

	public static void init(PluginManager pm) {

		pm.registerEvents(new DisablerListener(), WarFactions.getInstance());
		
	}
	
	

}
